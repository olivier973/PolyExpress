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
 * Servlet implementation class panierServlet
 */
public class PanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String PAGE_CONNEXION = "/authentificationServlet";
	
	public static final String SESSION_COMMERCANT = "connexionCommercant";
	public static final String SESSION_LIVREUR = "connexionLivreur";
	public static final String SESSION_CLIENT = "connexionClient";
	
	private static final String MESS_BON = "Bienvenue sur votre espace";
	private static final String PAGE_CATA = "/WEB-INF/panier.jsp";
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
	public PanierServlet() {
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
			String choix = request.getParameter("ch");
			String id = request.getParameter("id");
			Produit produit;
			float montant = 0;
			
			page = PAGE_CATA;
			request.setAttribute("message", MESS_BON);

			List<Produit> listepanier;
			listepanier = (List<Produit>) session.getAttribute("listepanier");
			if(listepanier == null)
			{
				listepanier = new ArrayList<Produit>();
			}
			if(id!=null)
			{
				if(choix.equals("ajouter"))
				{
					int rep;
					produit = produitDao.trouver(id);
					listepanier.add(produit);
					rep = produitDao.decrementer(id);
				}
				else if(choix.equals("supprimer"))
				{
					int rep;
					produit = produitDao.trouver(id);
					for(int i=0;i<listepanier.size();i++)
					{
						if(listepanier.get(i).getId()==produit.getId())
						{
							listepanier.remove(i);
						}
					}
					rep = produitDao.incrementer(id);
				}
			}
			for(Produit p : listepanier)
			{
				montant=montant+p.getPrix();
			}
			
			request.setAttribute("montant", montant);
			request.setAttribute("message", MESS_BON);
			session.setAttribute("listepanier", listepanier);
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
