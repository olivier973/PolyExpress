package beans;

public class Commande_PenseBete extends Commande {
	private int id_livreur;
	private int id_pensebete;
	
	public int getId_livreur() {
		return id_livreur;
	}
	public void setId_livreur(int id_livreur) {
		this.id_livreur = id_livreur;
	}
	public int getId_pensebete() {
		return id_pensebete;
	}
	public void setId_pensebete(int id_pensebete) {
		this.id_pensebete = id_pensebete;
	}
}
