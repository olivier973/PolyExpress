package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Commercant;
import dao.DAOFactory;
import dao.ProduitDAO;

/**
 * Servlet implementation class SupprimerProduit
 */
public class SupprimerProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHAMP_ID = "id";
	private static final String MESS_BON = "Produit supprimé.";
	private static final String MESS_ERREUR = "Erreur, produit non supprimé.";
	private static final String JSP_SUPP = "/WEB-INF/affichageMessage.jsp";
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
	public SupprimerProduit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter(CHAMP_ID);
		String message;
		String page = JSP_SUPP;
		message = MESS_ERREUR;

		HttpSession session = request.getSession();
		Commercant commercant;
		if((commercant = (Commercant) session.getAttribute("connexionCommercant"))!=null)
		{
			if(produitDao.supprimer(id,commercant)) {
				message = MESS_BON;
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
	}

}
