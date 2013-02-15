package formulaire;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Client;
import beans.Commercant;
import beans.Livreur;
import beans.Produit;
import dao.ClientDAO;
import dao.CommercantDAO;
import dao.DAOException;
import dao.LivreurDAO;
import dao.ProduitDAO;

public class ConnexionUtilisateurForm {
	private ClientDAO clientDAO;
	private CommercantDAO commercantDAO;
	private LivreurDAO livreurDAO;
	private ProduitDAO produitDAO;

	private String message = null;
	private String page = JSP_ERREUR;
	private HttpServletRequest requete;

	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_TYPE = "type";
	private static final String CHAMP_MDP ="motdepasse";

	private static final String MESS_CHPS_VIDES ="Erreur - Vous n'avez pas rempli tous les champs obligatoires.";
	private static final String MESS_MAUVAIS_IDENT = "Erreur - Identifiants incorrectes.";
	private static final String MESS_BON = "Bienvenue sur votre espace";

	private static final String JSP_CLIENT = "/WEB-INF/client.jsp";
	private static final String JSP_COMMERCANT = "/WEB-INF/commercant.jsp";
	private static final String JSP_LIVREUR = "/WEB-INF/livreur.jsp";
	private static final String JSP_ERREUR = "/WEB-INF/authentification.jsp";

	public ConnexionUtilisateurForm(ProduitDAO produitDao,CommercantDAO commercantDao,ClientDAO clientDao,LivreurDAO livreurDao) {
		super();
		this.produitDAO = produitDao;
		this.commercantDAO=commercantDao;
		this.clientDAO=clientDao;
		this.livreurDAO=livreurDao;
	}

	public boolean ConnexionUtilisateur(HttpServletRequest request) {
		// TODO Auto-generated method stub

		String login = getValeurChamp(request,CHAMP_LOGIN);
		String motDePasse = getValeurChamp(request,CHAMP_MDP);
		String type = getValeurChamp(request,CHAMP_TYPE);

		requete = request;

		HttpSession session = requete.getSession();

		if(login==null || motDePasse==null)
		{
			message = MESS_CHPS_VIDES;
		}
		else if(type.toString().equals("client"))
		{
			Client client;
			try 
			{
				if((client = (Client)clientDAO.trouver(login, motDePasse))==null) {
					message = MESS_MAUVAIS_IDENT;
					requete.setAttribute(CHAMP_LOGIN, login);
				}
				else
				{
					message = MESS_BON;
					session.setAttribute("clientConnexion", client);
					page = JSP_CLIENT;
				}
			}
			catch ( DAOException e ) 
			{
				e.printStackTrace();
				return false;
			}
		}
		else if(type.toString().equals("livreur"))
		{
			Livreur livreur;
			try 
			{
				if((livreur =(Livreur) livreurDAO.trouver(login, motDePasse))==null) {
					message = MESS_MAUVAIS_IDENT;
					requete.setAttribute(CHAMP_LOGIN, login);
				}
				else
				{
					message = MESS_BON;
					session.setAttribute("livreurConnexion", livreur);
					page = JSP_LIVREUR;
				}
			}
			catch ( DAOException e ) 
			{
				e.printStackTrace();
				return false;
			}
		}
		else if(type.toString().equals("commercant"))
		{
			Commercant commercant;
			try 
			{
				if((commercant =(Commercant) commercantDAO.trouver(login, motDePasse))==null) {
					message = MESS_MAUVAIS_IDENT;
					requete.setAttribute(CHAMP_LOGIN, login);
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
					if((listeproduits =(ArrayList<Produit>) produitDAO.trouver(commercant.getId()))!=null) {
						requete.setAttribute("listeproduits", listeproduits);
					}
				}
			}
			catch ( DAOException e ) 
			{
				e.printStackTrace();
				return false;
			}
		}
		request.setAttribute("message", message);
		return true;
	}

	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter( nomChamp );
		if ( valeur == null || valeur.trim().length() == 0 )
		{
			return null; 
		} else {
			return valeur.trim();
		}
	}

	public ProduitDAO getProduitDAO() {
		return produitDAO;
	}

	public void setProduitDAO(ProduitDAO produitDAO) {
		this.produitDAO = produitDAO;
	}

	public CommercantDAO getCommercantDAO() {
		return commercantDAO;
	}

	public void setCommercantDAO(CommercantDAO commercantDAO) {
		this.commercantDAO = commercantDAO;
	}

	public ClientDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public LivreurDAO getLivreurDAO() {
		return livreurDAO;
	}

	public void setLivreurDAO(LivreurDAO livreurDAO) {
		this.livreurDAO = livreurDAO;
	}

	public HttpServletRequest getRequete() {
		return requete;
	}

	public void setRequete(HttpServletRequest requete) {
		this.requete = requete;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
}