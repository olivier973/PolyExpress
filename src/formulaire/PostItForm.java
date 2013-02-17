package formulaire;

import javax.servlet.http.HttpServletRequest;

import beans.Commande;
import beans.PenseBete;
import dao.DAOException;
import dao.PenseBeteDAO;

public class PostItForm {
	private Commande commande;

	private PenseBeteDAO penseBeteDao;
	String resultat;

	public PostItForm(Commande commande, PenseBeteDAO penseBeteDao) {
		super();
		this.commande = commande;
		this.penseBeteDao = penseBeteDao;
	}

	public PenseBete ajouterPenseBete(HttpServletRequest request) {
		// TODO Auto-generated method stub
		PenseBete penseBete= new PenseBete();
		penseBete.setId_commande(this.commande.getId());
		
		try 
		{
			penseBeteDao.creer( penseBete); 
		}
		catch ( DAOException e ) 
		{
			resultat = "EÌ�chec de l'inscription : une erreur impreÌ�vue est survenue, merci de reÌ�essayer dans quelques instants.";
			e.printStackTrace(); 
			penseBete=null;
		}
		return penseBete;
	}
}