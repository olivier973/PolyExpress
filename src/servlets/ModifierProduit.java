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
		String id = request.getParameter(CHAMP_ID);
		String page = JSP_MODIF;

		HttpSession session = request.getSession();
		Commercant commercant;
		if((commercant = (Commercant) session.getAttribute("connexionCommercant"))!=null)
		{
			Produit produit;
			if((produit = produitDao.trouver(id))!=null) {
				request.setAttribute("produit", produit);
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
