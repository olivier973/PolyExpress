package formulaire;

import javax.servlet.http.HttpServletRequest;

import beans.Client;
import dao.DAOException;
import dao.UserDAO;

public class InscriptionClientForm {
	private UserDAO clientDAO;
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS="mdp";
	private static final String CHAMP_NOM="nom";
	private static final String CHAMP_PRENOM="prenom";
	private static final String CHAMP_COORDONNEE="coordonnee";
	private Client client;

	public InscriptionClientForm(UserDAO clientDao) {
		super();
		this.clientDAO = clientDao;
	}

	public boolean inscrireClient(HttpServletRequest request) {
		// TODO Auto-generated method stub

		boolean resultat = true;

		String email = getValeurChamp( request, CHAMP_EMAIL ); 
		String motDePasse = getValeurChamp( request, CHAMP_PASS );
		String nom = getValeurChamp( request, CHAMP_NOM );
		String prenom = getValeurChamp(request,CHAMP_PRENOM);
		String coordonnee = getValeurChamp(request,CHAMP_COORDONNEE);

		client = new Client(); 
		client.setCoordonnee(coordonnee);
		client.setEmail(email);
		client.setMdp(motDePasse);
		client.setNom(nom);
		client.setPrenom(prenom);

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
					clientDAO.creer(client);
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

	public UserDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(UserDAO client) {
		this.clientDAO = client;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
