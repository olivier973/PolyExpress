package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Livreur;
import dao.DAOException;
import dao.DAOFactory;
import dao.PenseBeteDAO;

/**
 * Servlet implementation class AttributionLivraison
 */
public class AttributionLivraison extends HttpServlet {
	private static final long serialVersionUID = 1L;	

	private static final String CHAMP_ID = "id";

	public static final String CONF_DAO_FACTORY = "daofactory";
	private static final String MESS_ERREUR = "Erreur, attribution non effectu�e.";
	private static final String PAGE_MESS = "/WEB-INF/affichageMessage.jsp";
	private static final String PAGE_LISTE = "/listeMesCommandesLivreurServlet";

	public static final String SESSION_COMMERCANT = "connexionCommercant";
	public static final String SESSION_LIVREUR = "connexionLivreur";
	public static final String SESSION_CLIENT = "connexionClient";

	public static final String PAGE_CONNEXION = "/authentificationServlet";

	private PenseBeteDAO penseBeteDao;

	public void init() throws ServletException {
		/* R�cup�ration d'une instance de notre DAO Utilisateur
		 */
		this.penseBeteDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getPenseBeteDAO();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AttributionLivraison() {
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

		if(session.getAttribute(SESSION_CLIENT)!=null || session.getAttribute(SESSION_COMMERCANT)!=null)
		{
			session.invalidate();
		}
		else if(session.getAttribute(SESSION_LIVREUR)!=null)
		{
			String id = request.getParameter(CHAMP_ID);
			page = PAGE_MESS;

			Livreur livreur;

			if((livreur = (Livreur) session.getAttribute(SESSION_LIVREUR))!=null)
			{
				try 
				{
					penseBeteDao.modifierLivreur(livreur.getId(),id);
					page = PAGE_LISTE;
					//this.getServletContext().getRequestDispatcher(page).forward(request, response);
				}
				catch ( DAOException e )
				{
					e.printStackTrace();

					request.setAttribute("message", MESS_ERREUR);
					//this.getServletContext().getRequestDispatcher(page).forward(request, response);
				}
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
