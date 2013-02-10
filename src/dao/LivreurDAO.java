package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.map;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Livreur;
import beans.User;

public class LivreurDAO implements UserDAO {
	private DAOFactory daoFactory;

	public LivreurDAO(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(User utilisateur) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public User trouver(String email, String mdp) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null; 
		ResultSet resultSet = null;
		Livreur livreur = null;
		String sql="SELECT id_livreur, nom, prenom, mdp, email FROM livreur WHERE email="+email+"and mdp=MD5("+mdp+")";
		try
		{
			/* Recuperation d'une connexion depuis la Factory */ 
			connexion = daoFactory.getConnection();
			resultSet = daoFactory.getConnexion().exec(sql);
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			if ( resultSet.next() ) 
			{ 
				livreur = (Livreur)map( resultSet, this );
			}
		} catch ( SQLException e )
		{
			throw new DAOException( e );
		} finally 
		{
			fermeturesSilencieuses( resultSet,daoFactory.getConnexion().getDbStatement(),  connexion ); 
		}
		return livreur; 
	}
}
