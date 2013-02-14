package formulaire;

import javax.servlet.http.HttpServletRequest;

import beans.Commercant;
import beans.User;
import dao.DAOException;
import dao.UserDAO;

public class InscriptionCommercantForm {
	private UserDAO commercantDAO;
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS="mdp";
	private static final String CHAMP_CONF="confirmation";
	private static final String CHAMP_NOM="nom";
	private static final String CHAMP_PRENOM="prenom";
	private static final String CHAMP_COORDONNEE="coordonnee";
	private String resultat;

	public InscriptionCommercantForm(UserDAO clientDao) {
		super();
		this.commercantDAO = clientDao;
	}

	public User inscrireCommercant(HttpServletRequest request) {
		// TODO Auto-generated method stub

		String email = getValeurChamp( request, CHAMP_EMAIL ); 
		String motDePasse = getValeurChamp( request, CHAMP_PASS );
		String confirmation = getValeurChamp( request, CHAMP_CONF ); 
		String nom = getValeurChamp( request, CHAMP_NOM );
		String prenom = getValeurChamp(request,CHAMP_PRENOM);
		String coordonnee = getValeurChamp(request,CHAMP_COORDONNEE);

		User commercant = new Commercant(); 
		commercant.setCoordonnee(coordonnee);
		commercant.setEmail(email);
		commercant.setMdp(motDePasse);
		commercant.setNom(nom);
		commercant.setPrenom(prenom);
		try 
		{

			commercantDAO.creer( commercant); 
			resultat = "SucceÌ€s de l'inscription.";

			{
				resultat = "EÌ�chec de l'inscription."; }
		} 
		catch ( DAOException e ) 
		{
			resultat = "EÌ�chec de l'inscription : une erreur impreÌ�vue est survenue, merci de reÌ�essayer dans quelques instants.";
			e.printStackTrace(); 
		}
		return commercant;
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


	public UserDAO getCommercantDAO() {
		return commercantDAO;
	}

	public void setCommercantDAO(UserDAO commercantDAO) {
		this.commercantDAO = commercantDAO;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
}