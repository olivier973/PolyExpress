package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static dao.DAOUtilitaire.*;
import beans.Client;
import beans.User;


public class ClientDAO implements UserDAO {
	private DAOFactory daoFactory;
	
	public ClientDAO(DAOFactory daoFactory) {
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
			Client client = null;
			String sql="SELECT nom, email, mdp FROM Client WHERE email="+email+"and mdp=MD5("+mdp+")";
			try
			{
				/* Récupération d'une connexion depuis la Factory */ 
				connexion = daoFactory.getConnection();
				resultSet = daoFactory.getConnexion().exec(sql);
				/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
				if ( resultSet.next() ) 
				{ 
					client = map( resultSet );
				}
			} catch ( SQLException e )
			{
				throw new DAOException( e );
			} finally 
			{
			fermeturesSilencieuses( resultSet,daoFactory.getConnexion().getDbStatement(),  connexion ); 
			}
			return client; 
	}
	
}


