package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Client;
import beans.Commande_PenseBete;
import beans.Commercant;
import beans.Message;
import beans.Produit;
import dao.CommandeDAO;
import dao.DAOFactory;
import dao.MessageDAO;
import dao.ProduitDAO;

/**
 * Servlet implementation class Authentification
 */
public class Authentification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String JSP_CLIENT = "/WEB-INF/client.jsp";
	private static final String JSP_COMMERCANT = "/WEB-INF/commercant.jsp";
	private static final String JSP_LIVREUR = "/WEB-INF/livreur.jsp";
	private static final String MESS_BON = "Bienvenue sur votre espace";
	
	private ProduitDAO produitDao;
	private MessageDAO messageDao;
	private CommandeDAO commandeDao;
	
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String PAGE_CONNEXION = "/WEB-INF/authentification.jsp";
	public static final String SESSION_COMMERCANT = "connexionCommercant";
	public static final String SESSION_LIVREUR = "connexionLivreur";
	public static final String SESSION_CLIENT = "connexionClient";

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur
		 */
		this.produitDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getProduitDAO();
		this.messageDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getMessageDAO();
		this.commandeDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getCommandeDAO();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Authentification() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = PAGE_CONNEXION;
		String message = null;

		HttpSession session = request.getSession();

		Commercant commercant;
		Client client;
		if((commercant = (Commercant) session.getAttribute(SESSION_COMMERCANT))!=null)
		{
			message = MESS_BON;
			page = JSP_COMMERCANT;

			List<Produit> listeproduits = new ArrayList<Produit>();
			if((listeproduits =(ArrayList<Produit>) produitDao.trouver(commercant.getId()))!=null) {
				request.setAttribute("listeproduits", listeproduits);
			}
		}
		else if((client = (Client) session.getAttribute(SESSION_CLIENT))!=null)
		{
			message = MESS_BON;
			page = JSP_CLIENT;
			
			List<Commande_PenseBete> listecommandes = new ArrayList<Commande_PenseBete>();
			if((listecommandes =(ArrayList<Commande_PenseBete>) commandeDao.trouverDestinataire(client.getId()))!=null) {
				request.setAttribute("listecommandes", listecommandes);
			}
		}
		else if(session.getAttribute(SESSION_LIVREUR)!=null)
		{
			message = MESS_BON;
			page = JSP_LIVREUR;
			
			List<Message> listemessages = new ArrayList<Message>();
			if((listemessages =(ArrayList<Message>) messageDao.trouver())!=null) {
				request.setAttribute("listemessages", listemessages);
			}
		}

		request.setAttribute("message", message);
		this.getServletContext().getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}