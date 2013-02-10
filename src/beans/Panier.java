package beans;

import java.io.Serializable;
import java.util.List;

public class Panier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_panier;
	private int id_client;
	private List<Produit> reference;
	private int numero_commande;
	
	public int getId_panier() {
		return id_panier;
	}
	public void setId_panier(int id_panier) {
		this.id_panier = id_panier;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public List<Produit> getReference() {
		return reference;
	}
	public void setReference(List<Produit> reference) {
		this.reference = reference;
	}
	public int getNumero_commande() {
		return numero_commande;
	}
	public void setNumero_commande(int numero_commande) {
		this.numero_commande = numero_commande;
	}
}