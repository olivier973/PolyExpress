package beans;

public class Commande {
	private int id;
	private int destinataire;
	private String adresse_livraison;
	private float montant;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(int destinataire) {
		this.destinataire = destinataire;
	}
	public String getAdresse_livraison() {
		return adresse_livraison;
	}
	public void setAdresse_livraison(String adresse_livraison) {
		this.adresse_livraison = adresse_livraison;
	}
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	String etat;
	
}
