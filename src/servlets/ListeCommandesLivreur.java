package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Commande_PenseBete;
import dao.CommandeDAO;
import dao.DAOFactory;

/**
 * Servlet implementation class ListeCommandesLivreur
 */
public class ListeCommandesLivreur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String PAGE_CONNEXION = "/authentificationServlet";
	
	public static final String SESSION_COMMERCANT = "connexionCommercant";
	public static final String SESSION_LIVREUR = "connexionLivreur";
	public static final String SESSION_CLIENT = "connexionClient";
	private static final String MESS_BON = "Bienvenue sur votre espace";
	private static final String PAGE_LISTE = "/WEB-INF/listeCommandesLivreur.jsp";

	private CommandeDAO commandeDao;

	public static final String CONF_DAO_FACTORY = "daofactory";

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur
		 */
		this.commandeDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getCommandeDAO();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListeCommandesLivreur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = PAGE_CONNEXION;

		HttpSession session = request.getSession();

		if(session.getAttribute(SESSION_COMMERCANT)!=null || session.getAttribute(SESSION_CLIENT)!=null)
		{
			session.invalidate();
		}
		else if(session.getAttribute(SESSION_LIVREUR)!=null)
		{
			page = PAGE_LISTE;
			request.setAttribute("message", MESS_BON);

			List<Commande_PenseBete> listecommandes = new ArrayList<Commande_PenseBete>();
			if((listecommandes =(ArrayList<Commande_PenseBete>) commandeDao.trouverSansLivreur())!=null) {
				request.setAttribute("listecommandes", listecommandes);
			}
		}
		this.getServletContext().getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
