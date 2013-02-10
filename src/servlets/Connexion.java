package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Client;
import beans.Commercant;
import beans.Livreur;
import beans.Produit;
import dao.ClientDAO;
import dao.CommercantDAO;
import dao.DAOFactory;
import dao.LivreurDAO;
import dao.ProduitDAO;

/**
 * Servlet implementation class Connexion
 */
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_TYPE = "type";
	private static final String CHAMP_MDP ="motdepasse";
	private static final String MESS_CHPS_VIDES ="Erreur - Vous n'avez pas rempli tous les	champs obligatoires. <br/> <a href=\"authentificationServlet\">Cliquez ici</a> pour acc�der au formulaire de connexion.";
	private static final String MESS_MAUVAIS_IDENT = "Erreur - Identifiants incorrectes. <br/> <a href=\"authentificationServlet\">Cliquez ici</a> pour acc�der au formulaire de connexion.";
	private static final String MESS_BON = "Bienvenue sur votre espace";
	private static final String JSP_CLIENT = "/WEB-INF/client.jsp";
	private static final String JSP_COMMERCANT = "/WEB-INF/commercant.jsp";
	private static final String JSP_LIVREUR = "/WEB-INF/livreur.jsp";
	private static final String JSP_ERREUR = "/WEB-INF/affichageMessage.jsp";
	private static final String SESSION_COMMERCANT = "commercants";
	private static final String SESSION_CLIENT = "clients";
	private static final String SESSION_LIVREUR = "livreurs";
	private ClientDAO clientDao;
	private CommercantDAO commercantDao;
	private LivreurDAO livreurDao;
	private ProduitDAO produitDao;
	public static final String CONF_DAO_FACTORY = "daofactory";

	public void init() throws ServletException {
		/* R�cup�ration d'une instance de notre DAO Utilisateur
		 */
		this.clientDao = new ClientDAO((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY));
		this.livreurDao = new LivreurDAO((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY));
		this.commercantDao = new CommercantDAO((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY));
		this.produitDao = new ProduitDAO((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY));
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
		String login = request.getParameter(CHAMP_LOGIN);
		String motDePasse = request.getParameter(CHAMP_MDP);
		String type = request.getParameter(CHAMP_TYPE);
		String message = null;
		String page = JSP_ERREUR;

		HttpSession session = request.getSession();

		if (login.trim().isEmpty() || motDePasse.trim().isEmpty()) {
			message = MESS_CHPS_VIDES;
		}
		else if(type.toString().equals("client"))
		{
			Client client;
			if((client = (Client) clientDao.trouver(login, motDePasse))==null) {
				message = MESS_MAUVAIS_IDENT;
			}
			else
			{
				message = MESS_BON;
				session.setAttribute("clientConnexion", client);
				page = JSP_CLIENT;
			}
		}
		else if(type.toString().equals("livreur"))
		{
			Livreur livreur;
			if((livreur =(Livreur) livreurDao.trouver(login, motDePasse))==null) {
				message = MESS_MAUVAIS_IDENT;
			}
			else
			{
				message = MESS_BON;
				session.setAttribute("livreurConnexion", livreur);
				page = JSP_LIVREUR;
			}
		}
		else if(type.toString().equals("commercant"))
		{
			Commercant commercant;
			if((commercant =(Commercant) commercantDao.trouver(login, motDePasse))==null) {
				message = MESS_MAUVAIS_IDENT;
			}
			else
			{
				message = MESS_BON;
				session.setAttribute("connexionCommercant", commercant);
				page = JSP_COMMERCANT;

				/* Map<Integer, Commercant> commercants = (HashMap<Integer, Commercant>) session.getAttribute(SESSION_COMMERCANT);
				if(commercants == null)
				{
					commercants = new HashMap<Integer, Commercant>();
				}
				commercants.put(commercant.getId(), commercant);
				session.setAttribute(SESSION_COMMERCANT, commercants); */

				/*	<c:forEach items="${sessionScope.commercants}" var="mapEntry">
						<tr>
								<td>${mapEntry.value.nom}</td>
								<td>${mapEntry.value.prenom}</td>
						</tr>
					</c:forEach>	*/

				List<Produit> listeproduits = new ArrayList<Produit>();
				if((listeproduits =(ArrayList<Produit>) produitDao.trouver(commercant.getId()))!=null) {
					request.setAttribute("listeproduits", listeproduits);
				}
			}
		}
		request.setAttribute("message", message);
		this.getServletContext().getRequestDispatcher(page).forward(request, response);
	}
}