package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import formulaire.AjoutCommandeForm;
import formulaire.ListeObjetsForm;
import formulaire.PostItForm;


import beans.*;


/**
 * Servlet implementation class ValidationPanier
 */
public class ValidationPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE = "/WEB-INF/affichageMessage.jsp";
	private static final String MESS_BON = "Commande validée.";
	private static final String MESS_ERR_COMM = "Commande invalide.";
	private static final String MESS_ERR_POST = "PostIt invalide.";
	private static final String MESS_ERR_LO = "ListeObjets invalide.";
	public static final String PAGE_CONNEXION = "/WEB-INF/authentification.jsp";
	public static final String SESSION_COMMERCANT = "connexionCommercant";
	public static final String SESSION_LIVREUR = "livreurConnexion";
	public static final String SESSION_CLIENT = "clientConnexion";
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String PAGE_ERR = "/WEB-INF/affichageMessage.jsp";
	private CommandeDAO commandeDao;
	private ListeObjetsDAO listeObjetsDao;
	private PenseBeteDAO penseBeteDao;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidationPanier() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void init() throws ServletException
	{
		this.commandeDao=((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getCommandeDAO();
		this.listeObjetsDao=((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getListeObjetsDAO();
		this.penseBeteDao=((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getPenseBeteDAO();
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
			
			
			
			Client client = (Client) session.getAttribute("clientConnexion");

			List<Produit> listepanier;
			listepanier = (List<Produit>) session.getAttribute("listepanier");
			AjoutCommandeForm commandeForm=new AjoutCommandeForm(client, listepanier, commandeDao);
			Commande commande=commandeForm.AjouterCommande(request);
			if(commande==null)
			{
				request.setAttribute("message", MESS_ERR_COMM);
				page=PAGE_ERR;
			}
			else
			{
				PostItForm postitForm= new PostItForm(commande, penseBeteDao);
				PenseBete penseBete=postitForm.ajouterPenseBete(request);
				if(penseBete==null)
				{
					request.setAttribute("message", MESS_ERR_POST);
					page=PAGE_ERR;
				}
				else 
				{
					for(Produit p : listepanier)
					{
						ListeObjetsForm listForm=new ListeObjetsForm(penseBete, listeObjetsDao, p);
						ListeObjets liste =listForm.ajouterListeObjets(request);
						if(liste !=null && page!=PAGE_ERR)
						{
							request.setAttribute("message", MESS_BON);
							page = PAGE;
							
						}
						else if (page!=PAGE_ERR)
						{
							request.setAttribute("message", MESS_ERR_LO);
							page=PAGE_ERR;
						}
							
					}
					
				}
			}
			/*if(listepanier != null)
			{
				listepanier = null;
			}
			session.setAttribute("listepanier", listepanier);*/
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