package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Produit;
import dao.DAOFactory;
import dao.ProduitDAO;

/**
 * Servlet implementation class Catalogue
 */
public class Catalogue extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String PAGE_CONNEXION = "/WEB-INF/authentification.jsp";
	public static final String SESSION_COMMERCANT = "connexionCommercant";
	public static final String SESSION_LIVREUR = "connexionLivreur";
	public static final String SESSION_CLIENT = "connexionClient";
	private static final String MESS_BON = "Bienvenue sur votre espace";
	private static final String PAGE_CATA = "/WEB-INF/catalogue.jsp";
	private ProduitDAO produitDao;
	public static final String CONF_DAO_FACTORY = "daofactory";

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur
		 */
		this.produitDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getProduitDAO();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Catalogue() {
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

		if(session.getAttribute(SESSION_COMMERCANT)!=null || session.getAttribute(SESSION_LIVREUR)!=null)
		{
			session.invalidate();
		}
		else if(session.getAttribute(SESSION_CLIENT)!=null)
		{
			page = PAGE_CATA;
			request.setAttribute("message", MESS_BON);

			List<Produit> listeproduits = new ArrayList<Produit>();
			if((listeproduits =(ArrayList<Produit>) produitDao.trouver())!=null) {
				request.setAttribute("listeproduits", listeproduits);
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
