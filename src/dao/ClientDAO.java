package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.map;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Client;
import beans.User;

public class ClientDAO implements UserDAO {
	private DAOFactory daoFactory;

	public ClientDAO(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(User client) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null; 
		ResultSet resultat=null;
		/*requete sql pour inserer un utilisateur dans la base*/
		String sql="INSERT INTO client VALUES (null,'"+client.getNom()+"','"+client.getPrenom()+"',sha1('"+client.getMdp()+"'),'"+client.getEmail()+"','"+client.getCoordonnee()+"');";
		/*requete sql pour recuperer l'id du client nouvellement creer*/
		String id="SELECT id_client FROM client WHERE email='"+client.getEmail()+"' and mdp=sha1('"+client.getMdp()+"');";
		try 
		{
			/* Récupération d'une connexion depuis la Factory */ 
			connexion = daoFactory.getConnection();
			int statut= daoFactory.getConnexion().getDbStatement().executeUpdate(sql);
			/* Analyse du statut retourné par la requête d'insertion */ 
			if ( statut==0) 
			{
				throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
			}
			/* Récupération de l'id auto-généré par la requête d'insertion */
			resultat = daoFactory.getConnexion().exec(id);
			if ( resultat.next() ) 
			{
				/* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
				client.setId( resultat.getInt("id_client") ); 
			} else 
			{
				throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
			}
		} catch ( SQLException e )
		{
			throw new DAOException( e ); 
		} finally 
		{
			fermeturesSilencieuses( resultat,daoFactory.getConnexion().getDbStatement(),  connexion );
		}
	}

	@Override
	public Client trouver(String email, String mdp) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null; 
		ResultSet resultSet = null;
		Client client = null;
		String sql="SELECT id_client, nom, prenom, mdp, email, coordonnee FROM client WHERE email='"+email+"' and mdp=sha1('"+mdp+"');";
		try
		{
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			resultSet = daoFactory.getConnexion().exec(sql);
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			if ( resultSet.next() ) 
			{
				client = (Client)map( resultSet, this );
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