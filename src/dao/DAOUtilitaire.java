package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Client;

public class DAOUtilitaire {
	public static Client map( ResultSet resultSet ) throws SQLException 
	{
		Client utilisateur = new Client();

		utilisateur.setEmail( resultSet.getString( "email" ) ); 
		utilisateur.setMdp( resultSet.getString( "mdp" ));
		utilisateur.setNom(resultSet.getString("nom"));
		utilisateur.setCoordonnee( resultSet.getString( "coordonnee" ) ); 
		utilisateur.setId( resultSet.getInt( "id_client" ));
		utilisateur.setPrenom(resultSet.getString("prenom"));

		return utilisateur; 
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
