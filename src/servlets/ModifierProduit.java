package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Commercant;
import beans.Produit;

import dao.DAOFactory;
import dao.ProduitDAO;

/**
 * Servlet implementation class ModifierProduit
 */
public class ModifierProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHAMP_ID = "id";
	private static final String JSP_MODIF = "/WEB-INF/modifierProduit.jsp";
	private ProduitDAO produitDao;
	public static final String CONF_DAO_FACTORY = "daofactory";
	
	public static final String SESSION_COMMERCANT = "connexionCommercant";
	public static final String SESSION_LIVREUR = "connexionLivreur";
	public static final String SESSION_CLIENT = "connexionClient";

	public static final String PAGE_CONNEXION = "/authentificationServlet";

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur
		 */
		this.produitDao = ((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getProduitDAO();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierProduit() {
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

		if(session.getAttribute(SESSION_CLIENT)!=null || session.getAttribute(SESSION_LIVREUR)!=null)
		{
			session.invalidate();
		}
		else if(session.getAttribute(SESSION_COMMERCANT)!=null)
		{
			String id = request.getParameter(CHAMP_ID);
			page = JSP_MODIF;

			Commercant commercant;
			if((commercant = (Commercant) session.getAttribute(SESSION_COMMERCANT))!=null)
			{
				Produit produit;
				if((produit = produitDao.trouver(id))!=null) {
					request.setAttribute("produit", produit);
				}
			}
			//this.getServletContext().getRequestDispatcher(page).forward(request, response);
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
