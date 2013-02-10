package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.map;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Commercant;
import beans.User;

public class CommercantDAO implements UserDAO {
	private DAOFactory daoFactory;

	public CommercantDAO(DAOFactory daoFactory) {
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
		Commercant commercant = null;
		String sql="SELECT id_commercant, nom, prenom, mdp, email, coordonnee FROM commercant WHERE email='"+email+"' and mdp=sha1('"+mdp+"');";
		try
		{
			/* Recuperation d'une connexion depuis la Factory */ 
			connexion = daoFactory.getConnection();
			resultSet = daoFactory.getConnexion().exec(sql);
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			if ( resultSet.next() ) 
			{ 
				commercant = (Commercant)map(resultSet, this);
			}
		} catch ( SQLException e )
		{
			throw new DAOException( e );
		} finally
		{
			fermeturesSilencieuses( resultSet,daoFactory.getConnexion().getDbStatement(),  connexion ); 
		}
		return commercant; 
	}
}
