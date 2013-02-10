package formulaire;

import javax.servlet.http.HttpServletRequest;

import beans.Client;
import beans.User;

import dao.ClientDAO;
import dao.DAOException;
import dao.UserDAO;

public class InscriptionClientForm {
	private UserDAO clientDAO;
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS="mdp";
	private static final String CHAMP_CONF="confirmation";
	private static final String CHAMP_NOM="nom";
	private static final String CHAMP_PRENOM="prenom";
	private static final String CHAMP_COORDONNEE="coordonnee";
	private String resultat;

	public InscriptionClientForm(UserDAO clientDao) {
		super();
		this.clientDAO = clientDao;
	}

	public User inscrireClient(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String email = getValeurChamp( request, CHAMP_EMAIL ); 
		String motDePasse = getValeurChamp( request, CHAMP_PASS );
		String confirmation = getValeurChamp( request, CHAMP_CONF ); 
		String nom = getValeurChamp( request, CHAMP_NOM );
		String prenom = getValeurChamp(request,CHAMP_PRENOM);
		String coordonnee = getValeurChamp(request,CHAMP_COORDONNEE);
	
		User client = new Client(); 
		client.setCoordonnee(coordonnee);
		client.setEmail(email);
		client.setMdp(motDePasse);
		client.setNom(nom);
		client.setPrenom(prenom);
		try 
		{
			 
				clientDAO.creer( client); 
				resultat = "Succès de l'inscription.";
			
			{
				resultat = "Échec de l'inscription."; }
		} 
		catch ( DAOException e ) 
		{
			resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace(); 
		}
			return client;
	}
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
		String valeur = request.getParameter( nomChamp );
		if ( valeur == null || valeur.trim().length() == 0 )
		{
			return null; 
		} else {
			return valeur.trim();
		}
			
	}

	public UserDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(UserDAO client) {
		this.clientDAO = client;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
}
