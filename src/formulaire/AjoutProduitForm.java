package formulaire;

import javax.servlet.http.HttpServletRequest;

import beans.Client;
import beans.Produit;
import beans.User;
import dao.CommercantDAO;
import dao.DAOException;
import dao.ProduitDAO;

public class AjoutProduitForm {
	private ProduitDAO produitDAO;
	private CommercantDAO commercantDAO;
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_PASS="mdp";
	private static final String CHAMP_CONF="confirmation";
	private static final String CHAMP_NOM="nom";
	private static final String CHAMP_DESCRIPTION="description";
	private static final String CHAMP_PRIX="prix";
	private static final String CHAMP_QUANTITE="quantite";
	
	private String resultat;

	public AjoutProduitForm(ProduitDAO produitDao,CommercantDAO commercantDao) {
		super();
		this.produitDAO = produitDao;
		this.commercantDAO=commercantDao;
	}

	public Produit AjouterProduit(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		String email = getValeurChamp( request, CHAMP_EMAIL ); 
		String motDePasse = getValeurChamp( request, CHAMP_PASS );
		String confirmation = getValeurChamp( request, CHAMP_CONF ); 
		String nom = getValeurChamp( request, CHAMP_NOM );
		String description = getValeurChamp(request,CHAMP_DESCRIPTION);
		String prix = getValeurChamp(request,CHAMP_PRIX);
		String quantite = getValeurChamp(request,CHAMP_QUANTITE);
		User commercant= this.commercantDAO.trouver(email, motDePasse);
		Produit produit=new Produit();
		produit.setCommercant(commercant.getId());
		produit.setDescription(description);
		produit.setNom(nom);
		produit.setPrix(new Float(prix));
		produit.setQuantite(new Integer(quantite));
		try 
		{
			 
				produitDAO.creer( produit); 
				resultat = "Succès de l'inscription.";
			
			{
				resultat = "Échec de l'inscription."; }
		} 
		catch ( DAOException e ) 
		{
			resultat = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace(); 
		}
			return produit;
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

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
}
