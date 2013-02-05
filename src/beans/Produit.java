package beans;

public class Produit {
	private Commerçant commerçant;
	private String nom;
	private int quantite;
	private String description;
	public Commerçant getCommerçant() {
		return commerçant;
	}
	public void setCommerçant(Commerçant commerçant) {
		this.commerçant = commerçant;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
