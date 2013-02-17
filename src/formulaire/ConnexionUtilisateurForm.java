package formulaire;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Client;
import beans.Commande_PenseBete;
import beans.Commercant;
import beans.Livreur;
import beans.Message;
import beans.Produit;
import dao.ClientDAO;
import dao.CommandeDAO;
import dao.CommercantDAO;
import dao.DAOException;
import dao.LivreurDAO;
import dao.MessageDAO;
import dao.ProduitDAO;

public class ConnexionUtilisateurForm {
	private ClientDAO clientDAO;
	private CommercantDAO commercantDAO;
	private LivreurDAO livreurDAO;
	private ProduitDAO produitDAO;
	private MessageDAO messageDAO;
	private CommandeDAO commandeDAO;

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
	
	public static final String SESSION_COMMERCANT = "connexionCommercant";
	public static final String SESSION_LIVREUR = "connexionLivreur";
	public static final String SESSION_CLIENT = "connexionClient";

	public ConnexionUtilisateurForm(ProduitDAO produitDao,CommercantDAO commercantDao,ClientDAO clientDao,LivreurDAO livreurDao,MessageDAO messageDao,CommandeDAO commandeDao) {
		super();
		this.produitDAO = produitDao;
		this.commercantDAO=commercantDao;
		this.clientDAO=clientDao;
		this.livreurDAO=livreurDao;
		this.messageDAO=messageDao;
		this.commandeDAO=commandeDao;
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
					session.setAttribute(SESSION_CLIENT, client);
					page = JSP_CLIENT;
					
					List<Commande_PenseBete> listecommandes = new ArrayList<Commande_PenseBete>();
					if((listecommandes =(ArrayList<Commande_PenseBete>) commandeDAO.trouverDestinataire(client.getId()))!=null) {
						request.setAttribute("listecommandes", listecommandes);
					}
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
					session.setAttribute(SESSION_LIVREUR, livreur);
					page = JSP_LIVREUR;
					
					List<Message> listemessages = new ArrayList<Message>();
					if((listemessages =(ArrayList<Message>) messageDAO.trouver())!=null) {
						request.setAttribute("listemessages", listemessages);
					}
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
					session.setAttribute(SESSION_COMMERCANT, commercant);
					page = JSP_COMMERCANT;

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