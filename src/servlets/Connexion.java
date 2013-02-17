package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClientDAO;
import dao.CommandeDAO;
import dao.CommercantDAO;
import dao.DAOFactory;
import dao.LivreurDAO;
import dao.MessageDAO;
import dao.ProduitDAO;
import formulaire.ConnexionUtilisateurForm;

/**
 * Servlet implementation class Connexion
 */
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClientDAO clientDao;
	private CommercantDAO commercantDao;
	private LivreurDAO livreurDao;
	private ProduitDAO produitDao;
	private MessageDAO messageDao;
	private CommandeDAO commandeDao;

	public static final String MESS_DAO = "Erreur DAO.";
	public static final String PAGE_MESS = "/WEB-INF/affichageMessage.jsp";
	public static final String CONF_DAO_FACTORY = "daofactory";

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur
		 */
		this.clientDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getClientDAO();
		this.livreurDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getLivreurDAO();
		this.commercantDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getCommercantDAO();
		this.produitDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getProduitDAO();
		this.messageDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getMessageDAO();
		this.commandeDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getCommandeDAO();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Connexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ConnexionUtilisateurForm form = new ConnexionUtilisateurForm(this.produitDao, this.commercantDao, this.clientDao, this.livreurDao, this.messageDao, this.commandeDao);

		String page = null;
		boolean res;

		res = form.ConnexionUtilisateur(request);
		
		if(res == false)
		{
			request.setAttribute("message", MESS_DAO);
			page = PAGE_MESS;
		}
		else
		{
			page = form.getPage();
			request = form.getRequete();
		}

		this.getServletContext().getRequestDispatcher(page).forward(request, response);
	}
}