package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.map;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Commercant;
import beans.Produit;

public class ProduitDAO {
	private DAOFactory daoFactory;

	public ProduitDAO(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	public void creer(Produit produit) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		ResultSet resultat=null;
		/*requete sql pour inserer un produit dans la base*/
		String sql="INSERT INTO produit VALUES (null,'"+produit.getCommercant()+"','"+produit.getNom()+"','"+produit.getQuantite()+"','"+produit.getPrix()+"','"+produit.getDescription()+"');";
		/*requete sql pour recuperer l'id du produit*/
		//String id="SELECT reference FROM produit WHERE nom='"+produit.getNom()+"' and description='"+produit.getDescription()+"';";
		try 
		{
			/* ReÌ�cupeÌ�ration d'une connexion depuis la Factory */ 
			connexion = daoFactory.getConnection();
			int statut= daoFactory.getConnexion().getDbStatement().executeUpdate(sql);
			/* Analyse du statut retourneÌ� par la requeÌ‚te d'insertion */ 
			if ( statut==0)
			{
				throw new DAOException( "echec à la creation du produit, aucune ligne ajouter dans la table." );
			}
			/* ReÌ�cupeÌ�ration de l'id auto-geÌ�neÌ�reÌ� par la requeÌ‚te d'insertion */
			/*resultat = daoFactory.getConnexion().exec(id);
			if ( resultat.next() ) 
			{*/
			/* Puis initialisation de la proprieÌ�teÌ� id du bean Utilisateur avec sa valeur */
			/*produit.setId(resultat.getInt("reference") );
			} else 
			{
				throw new DAOException( "EÌ�chec de la creÌ�ation de l'utilisateur en base, aucun ID auto-geÌ�neÌ�reÌ� retourneÌ�." );
			}*/
		} catch ( SQLException e )
		{
			throw new DAOException( e ); 
		} finally 
		{
			fermeturesSilencieuses( resultat,daoFactory.getConnexion().getDbStatement(),  connexion );
		}
	}

	public void modifier(Produit produit) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		int valide;
		ResultSet resultat=null;
		/*requete sql pour inserer un produit dans la base*/
		String sql="update produit set nom='"+produit.getNom()+"', quantite='"+produit.getQuantite()+"', prix='"+produit.getPrix()+"', description='"+produit.getDescription()+"' where reference='"+produit.getId()+"';";
		System.out.println(sql);
		try 
		{
			/* ReÌ�cupeÌ�ration d'une connexion depuis la Factory */ 
			connexion = daoFactory.getConnection();
			valide= daoFactory.getConnexion().getDbStatement().executeUpdate(sql);
			/* Analyse du statut retourneÌ� par la requeÌ‚te d'insertion */ 
			if ( valide==0)
			{
				throw new DAOException( "echec de la modification du prouit, aucune ligne modifiée dans la table." );
			}
			/* ReÌ�cupeÌ�ration de l'id auto-geÌ�neÌ�reÌ� par la requeÌ‚te d'insertion */
		} catch ( SQLException e )
		{
			throw new DAOException( e ); 
		} finally 
		{
			fermeturesSilencieuses(resultat,daoFactory.getConnexion().getDbStatement(), connexion);
		}
	}

	public int decrementer(String id) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		int valide;
		ResultSet resultSet = null;
		String sql="UPDATE produit set quantite=quantite-1 where reference='" + id + "' and quantite>0;";
		try
		{
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			valide = daoFactory.getConnexion().getDbStatement().executeUpdate(sql);
		} catch ( SQLException e )
		{
			throw new DAOException( e );
		} finally 
		{
			fermeturesSilencieuses( resultSet,daoFactory.getConnexion().getDbStatement(),  connexion );
		}
		return valide;
	}

	public int incrementer(String id) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		int valide;
		ResultSet resultSet = null;
		String sql="UPDATE produit set quantite=quantite+1 WHERE reference='" + id + "';";
		try
		{
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			valide = daoFactory.getConnexion().getDbStatement().executeUpdate(sql);
		} catch ( SQLException e )
		{
			throw new DAOException( e );
		} finally 
		{
			fermeturesSilencieuses( resultSet,daoFactory.getConnexion().getDbStatement(),  connexion );
		}
		return valide;
	}

	public boolean supprimer(String id, Commercant c) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		ResultSet resultSet = null;
		boolean valide = false;
		String sql="SELECT * FROM produit WHERE reference='" + id + "' and id_commercant='" + c.getId() + "';";
		try
		{
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			resultSet = daoFactory.getConnexion().exec(sql);
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while ( resultSet.next() )
			{
				resultSet.deleteRow();
				valide = true;
			}
		} catch ( SQLException e )
		{
			throw new DAOException( e );
		} finally 
		{
			fermeturesSilencieuses( resultSet,daoFactory.getConnexion().getDbStatement(),  connexion );
		}
		return valide;
	}

	public Produit trouver(String id) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null; 
		ResultSet resultSet = null;
		Produit produit = null;
		String sql="SELECT * FROM produit where reference='" + id + "';";
		try
		{
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			resultSet = daoFactory.getConnexion().exec(sql);
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while ( resultSet.next() )
			{
				produit = (Produit)map( resultSet, this );
			}
		} catch ( SQLException e )
		{
			throw new DAOException( e );
		} finally 
		{
			fermeturesSilencieuses( resultSet,daoFactory.getConnexion().getDbStatement(),  connexion );
		}
		return produit;
	}

	public List<Produit> trouver() throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null; 
		ResultSet resultSet = null;
		Produit produit = null;
		List<Produit> listeproduits = new ArrayList<Produit>();
		String sql="SELECT * FROM produit where quantite>0;";
		try
		{
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			resultSet = daoFactory.getConnexion().exec(sql);
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while ( resultSet.next() )
			{
				produit = (Produit)map( resultSet, this );
				listeproduits.add(produit);
			}
		} catch ( SQLException e )
		{
			throw new DAOException( e );
		} finally 
		{
			fermeturesSilencieuses( resultSet,daoFactory.getConnexion().getDbStatement(),  connexion );
		}
		return listeproduits;
	}

	public List<Produit> trouver(int id_commercant) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null; 
		ResultSet resultSet = null;
		Produit produit = null;
		List<Produit> listeproduits = new ArrayList<Produit>();
		String sql="SELECT * FROM produit where id_commercant='" + id_commercant + "';";
		try
		{
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			resultSet = daoFactory.getConnexion().exec(sql);
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while ( resultSet.next() )
			{
				produit = (Produit)map( resultSet, this );
				listeproduits.add(produit);
			}
		} catch ( SQLException e )
		{
			throw new DAOException( e );
		} finally 
		{
			fermeturesSilencieuses( resultSet,daoFactory.getConnexion().getDbStatement(),  connexion );
		}
		return listeproduits;
	}
}