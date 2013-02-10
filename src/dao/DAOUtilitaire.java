package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Client;
import beans.Commercant;
import beans.Livreur;
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
		return null;
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
