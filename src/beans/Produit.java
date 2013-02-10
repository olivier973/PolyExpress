package beans;


import java.io.Serializable;

public class Produit implements Serializable{
	/**
	 *
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int commercant;
	private String nom;
	private int quantite;
	private String description;
	private float prix;
	public int getCommercant() {
		return commercant;
	}
	public void setCommercant(int commercant) {
		this.commercant = commercant;
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
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
