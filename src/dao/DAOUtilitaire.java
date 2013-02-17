package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Client;
import beans.Commande_PenseBete;
import beans.Commercant;
import beans.Livreur;
import beans.Message;
import beans.Produit;

public class DAOUtilitaire {
	public static <T, Object> Object map(ResultSet resultSet, T dao) throws SQLException 
	{
		if(dao instanceof ClientDAO)
		{
			Client utilisateur = new Client();
			utilisateur.setEmail( resultSet.getString( "email" ) ); 
			utilisateur.setMdp( resultSet.getString( "mdp" ));
			utilisateur.setNom(resultSet.getString("nom"));
			utilisateur.setCoordonnee( resultSet.getString( "coordonnee" ) ); 
			utilisateur.setId( resultSet.getInt( "id_client" ));
			utilisateur.setPrenom(resultSet.getString("prenom"));
			return (Object) utilisateur;
		}
		if(dao instanceof CommercantDAO)
		{
			Commercant utilisateur = new Commercant();
			utilisateur.setEmail( resultSet.getString( "email" ) ); 
			utilisateur.setMdp( resultSet.getString( "mdp" ));
			utilisateur.setNom(resultSet.getString("nom"));
			utilisateur.setCoordonnee( resultSet.getString( "coordonnee" ) ); 
			utilisateur.setId( resultSet.getInt( "id_commercant" ));
			utilisateur.setPrenom(resultSet.getString("prenom"));
			return (Object) utilisateur;
		}
		if(dao instanceof LivreurDAO)
		{
			Livreur utilisateur = new Livreur();
			utilisateur.setEmail( resultSet.getString( "email" ) ); 
			utilisateur.setMdp( resultSet.getString( "mdp" ));
			utilisateur.setNom(resultSet.getString("nom"));
			utilisateur.setId( resultSet.getInt( "id_livreur" ));
			utilisateur.setPrenom(resultSet.getString("prenom"));
			return (Object) utilisateur;
		}
		if(dao instanceof ProduitDAO)
		{
			Produit produit = new Produit();
			produit.setCommercant( resultSet.getInt( "id_commercant" ) ); 
			produit.setNom( resultSet.getString( "nom" ));
			produit.setQuantite(resultSet.getInt("quantite"));
			produit.setId( resultSet.getInt( "reference" ));
			produit.setDescription(resultSet.getString("description"));
			produit.setPrix(resultSet.getFloat("prix"));
			return (Object) produit;
		}
		if(dao instanceof MessageDAO)
		{
			Message message = new Message();
			message.setId( resultSet.getInt( "id_msg" ) ); 
			message.setId_livreur( resultSet.getInt( "id_livreur" ));
			message.setMsg(resultSet.getString("texte_traffic"));
			return (Object) message;
		}
		return null;
	}

	public static Commande_PenseBete mapCb(ResultSet resultSet) throws SQLException
	{
		Commande_PenseBete commande = new Commande_PenseBete();
		commande.setAdresse_livraison( resultSet.getString( "adresse_livraison" ) ); 
		commande.setDestinataire( resultSet.getInt( "destinataire" ));
		commande.setEtat(resultSet.getString("etat"));
		commande.setId( resultSet.getInt( "numero_commande" ) );
		/*if(resultSet.getInt("id_livreur")==0)
		{}
		else*/
		commande.setId_livreur( resultSet.getInt( "id_livreur" ));
		commande.setMontant(resultSet.getFloat("montant"));
		commande.setId_pensebete(resultSet.getInt("id_pense_bete"));
		return commande;
	}

	public static void fermetureSilencieuse( ResultSet resultSet ) 
	{ 	
		if ( resultSet != null ) 
		{
			try 
			{ 
				resultSet.close();
			} catch ( SQLException e ) 
			{
				System.out.println( "Echec de la fermeture du ResultSet: " + e.getMessage() );
			} 
		}
	}
	/* Fermeture silencieuse du statement */

	public static void fermetureSilencieuse( Statement statement ) 
	{ 
		if ( statement != null ) 
		{
			try
			{ 	
				statement.close();
			}catch ( SQLException e ) 
			{
				System.out.println( "Echec de la fermeture du Statement: " + e.getMessage() );
			}
		}
	}
	/* Fermeture silencieuse de la connexion */
	public static void fermetureSilencieuse( Connection connexion ) 
	{ 
		if ( connexion != null ) 
		{
			try 
			{ 
				connexion.close();
			} catch ( SQLException e ) 
			{
				System.out.println( "Echec de la fermeture de la connexion : " + e.getMessage() );
			} 
		}
	}
	/* Fermetures silencieuses du statement et de la connexion */
	public static void fermeturesSilencieuses( Statement statement, Connection connexion )
	{
		fermetureSilencieuse( statement );
		fermetureSilencieuse( connexion ); 
	}
	/* Fermetures silencieuses du resultset, du statement et de la connexion */
	public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion )
	{
		fermetureSilencieuse( resultSet ); 
		fermetureSilencieuse( statement ); 
		fermetureSilencieuse( connexion );
	}
}
