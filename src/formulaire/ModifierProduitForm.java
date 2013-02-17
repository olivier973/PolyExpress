package formulaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Commercant;
import beans.Produit;
import dao.CommercantDAO;
import dao.DAOException;
import dao.ProduitDAO;

public class ModifierProduitForm {
	private ProduitDAO produitDAO;
	private CommercantDAO commercantDAO;

	private Commercant commercant;
	private Produit produit;

	private static final String CHAMP_NOM="nom";
	private static final String CHAMP_ID="reference";
	private static final String CHAMP_DESCRIPTION="description";
	private static final String CHAMP_PRIX="prix";
	private static final String CHAMP_QUANTITE="quantite";
	public static final String SESSION_COMMERCANT = "connexionCommercant";
	public static final String SESSION_LIVREUR = "connexionLivreur";
	public static final String SESSION_CLIENT = "connexionClient";

	public ModifierProduitForm(ProduitDAO produitDao,CommercantDAO commercantDao) {
		super();
		this.produitDAO = produitDao;
		this.commercantDAO=commercantDao;
	}

	public boolean ModifierProduit(HttpServletRequest request) {
		// TODO Auto-generated method stub
		float prixf;
		int quantitei;
		int referencei;
		boolean resultat = true;

		String nom = getValeurChamp(request, CHAMP_NOM);
		String reference = getValeurChamp(request, CHAMP_ID);
		String description = getValeurChamp(request,CHAMP_DESCRIPTION);
		String prix = getValeurChamp(request,CHAMP_PRIX);
		String quantite = getValeurChamp(request,CHAMP_QUANTITE);
		HttpSession session = request.getSession();

		commercant = (Commercant) session.getAttribute(SESSION_COMMERCANT);

		produit=new Produit();
		produit.setCommercant(commercant.getId());
		produit.setDescription(description);
		produit.setNom(nom);

		if(description==null || nom==null || prix==null || quantite==null)
		{
			return false;
		}
		else
		{
			try {
				referencei = Integer.parseInt(reference);
				produit.setId(referencei);

			} catch ( NumberFormatException e ) {
				return false;
			}

			try {
				prixf = Float.parseFloat(prix);
				if(prixf<1 || prixf>1000)
				{
					resultat = false;
				}
				else
				{
					produit.setPrix(prixf);
				}
			} catch ( NumberFormatException e ) {
				return false;
			}

			try {
				quantitei = Integer.parseInt(quantite);
				if(quantitei<1 || quantitei>1000)
				{
					resultat = false;
				}
				else
				{
					produit.setQuantite(quantitei);
				}
			} catch ( NumberFormatException e ) {
				return false;
			}

			if(resultat!=false)
			{
				try 
				{
					produitDAO.modifier(produit);
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
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
}
