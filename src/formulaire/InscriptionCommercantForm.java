package formulaire;

import javax.servlet.http.HttpServletRequest;

import beans.Commercant;
import dao.DAOException;
import dao.UserDAO;

public class InscriptionCommercantForm {
	private UserDAO commercantDAO;
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS="mdp";
	private static final String CHAMP_NOM="nom";
	private static final String CHAMP_PRENOM="prenom";
	private static final String CHAMP_COORDONNEE="coordonnee";
	private Commercant commercant;

	public InscriptionCommercantForm(UserDAO clientDao) {
		super();
		this.commercantDAO = clientDao;
	}

	public boolean inscrireCommercant(HttpServletRequest request) {
		// TODO Auto-generated method stub

		boolean resultat = true;

		String email = getValeurChamp( request, CHAMP_EMAIL ); 
		String motDePasse = getValeurChamp( request, CHAMP_PASS );
		String nom = getValeurChamp( request, CHAMP_NOM );
		String prenom = getValeurChamp(request,CHAMP_PRENOM);
		String coordonnee = getValeurChamp(request,CHAMP_COORDONNEE);

		commercant = new Commercant(); 
		commercant.setCoordonnee(coordonnee);
		commercant.setEmail(email);
		commercant.setMdp(motDePasse);
		commercant.setNom(nom);
		commercant.setPrenom(prenom);

		if(coordonnee==null || nom==null || email==null || prenom==null || motDePasse==null)
		{
			return false;
		}
		else
		{
			if(resultat!=false)
			{
				try 
				{
					commercantDAO.creer(commercant);
					return true;
				}
				catch ( DAOException e ) 
				{
					e.printStackTrace();
					return false;
				}
			}
			else
			{
				return resultat;
			}
		}
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

	public Commercant getCommercant() {
		return commercant;
	}

	public void setCommercant(Commercant commercant) {
		this.commercant = commercant;
	}
}