package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommercantDAO;
import dao.DAOFactory;
import dao.ProduitDAO;
import formulaire.AjoutProduitForm;

/**
 * Servlet implementation class AjoutProduitFormServlet
 */

public class AjouterProduitFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_PRODUIT = "produit";
	public static final String ATT_FORM  = "form";
	public static final String VUE_PROD= "/WEB-INF/ajoutProduit.jsp";
	public static final String JSP_COMMERCANT = "/authentificationServlet";

	private CommercantDAO commercantDao;
	private ProduitDAO produitDao;

	public void init() throws ServletException
	{
		this.commercantDao=((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getCommercantDAO();
		this.produitDao=((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getProduitDAO();
	}
	public AjouterProduitFormServlet() {
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
		/* PreÌ�paration de l'objet formulaire */

		AjoutProduitForm form = new AjoutProduitForm(this.produitDao,this.commercantDao);

		/* traitement de la requeÌ‚te et reÌ�cupeÌ�ration du bean en reÌ�sultant */
		String page = null;
		boolean res;
		res = form.AjouterProduit(request);
		if(res==false)
		{
			request.setAttribute(ATT_PRODUIT, form.getProduit());
			page = VUE_PROD;
		}
		else
		{
			page = JSP_COMMERCANT;
		}
		this.getServletContext().getRequestDispatcher(page).forward(request, response);
	}
}
