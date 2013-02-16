package formulaire;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.CommandeDAO;
import dao.DAOException;

import beans.*;

public class AjoutCommandeForm {

	private Client client;
	private List<Produit> listepanier;
	private CommandeDAO commandeDao;
	String resultat;
	
	public AjoutCommandeForm(Client client, List<Produit> listpanier,
			CommandeDAO commandeDao) {
		super();
		this.client = client;
		this.listepanier = listpanier;
		this.commandeDao = commandeDao;
	}
	public Commande AjouterCommande(HttpServletRequest request) {
		// TODO Auto-generated method stub
		float montant=0;
		for(Produit p : this.listepanier)
		{
			montant=montant+p.getPrix();
		}
		Commande commande= new Commande();
		commande.setAdresse_livraison(client.getCoordonnee());
		commande.setDestinataire(client.getId());
		commande.setMontant(montant);
		
		try 
		{

			commandeDao.creer( commande); 
		}
		catch ( DAOException e ) 
		{
			resultat = "EÌ�chec de l'inscription : une erreur impreÌ�vue est survenue, merci de reÌ�essayer dans quelques instants.";
			e.printStackTrace(); 
			commande=null;
		}
		return commande;
	}
	
	
}
