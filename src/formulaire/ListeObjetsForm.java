package formulaire;

import javax.servlet.http.HttpServletRequest;

import beans.ListeObjets;
import beans.PenseBete;
import beans.Produit;
import dao.DAOException;
import dao.ListeObjetsDAO;

public class ListeObjetsForm {

	private PenseBete penseBete;
	private ListeObjetsDAO listeObjetsDao;
	private Produit produit;
	String resultat;

	public ListeObjetsForm(PenseBete penseBete, ListeObjetsDAO listeObjetsDao, Produit produit) {
		super();
		this.penseBete = penseBete;
		this.listeObjetsDao = listeObjetsDao;
		this.produit=produit;
	}

	public ListeObjets ajouterListeObjets(HttpServletRequest request) {
		// TODO Auto-generated method stub
		ListeObjets listeObjets= new ListeObjets();
		listeObjets.setId_penseBete(penseBete.getId());
		listeObjets.setId_objet(produit.getId());

		try 
		{
			listeObjetsDao.creer( listeObjets); 
		}
		catch ( DAOException e ) 
		{
			resultat = "EÌ�chec de l'inscription : une erreur impreÌ�vue est survenue, merci de reÌ�essayer dans quelques instants.";
			e.printStackTrace(); 
			listeObjets=null;
		}
		return listeObjets;
	}
}