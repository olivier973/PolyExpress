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
		String sql="SELECT * FROM Produit where reference='" + id + "';";
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
		String sql="SELECT * FROM Produit where quantite>0;";
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
		String sql="SELECT * FROM Produit where id_commercant='" + id_commercant + "';";
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