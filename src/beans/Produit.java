package beans;

public class Produit {
	private Commer�ant commer�ant;
	private String nom;
	private int quantite;
	private String description;
	public Commer�ant getCommer�ant() {
		return commer�ant;
	}
	public void setCommer�ant(Commer�ant commer�ant) {
		this.commer�ant = commer�ant;
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
