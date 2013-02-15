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
	public void creer(User commercant) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null; 
		ResultSet resultat=null;
		/*requete sql pour inserer un utilisateur dans la base*/
		String sql="INSERT INTO commercant VALUES (null,'"+commercant.getNom()+"','"+commercant.getPrenom()+"',sha1('"+commercant.getMdp()+"'),'"+commercant.getEmail()+"','"+commercant.getCoordonnee()+"');";
		/*requete sql pour recuperer l'id du client nouvellement creer*/
		//String id="SELECT id_commercant FROM commercant WHERE email='"+commercant.getEmail()+"' and mdp=sha1('"+commercant.getMdp()+"');";
		try 
		{
			/* ReÌ�cupeÌ�ration d'une connexion depuis la Factory */ 
			connexion = daoFactory.getConnection();
			int statut= daoFactory.getConnexion().getDbStatement().executeUpdate(sql);
			/* Analyse du statut retourneÌ� par la requeÌ‚te d'insertion */ 
			if ( statut==0) 
			{
				throw new DAOException( "EÌ�chec de la creÌ�ation de l'utilisateur, aucune ligne ajouteÌ�e dans la table." );
			}
			/* ReÌ�cupeÌ�ration de l'id auto-geÌ�neÌ�reÌ� par la requeÌ‚te d'insertion */
			/*resultat = daoFactory.getConnexion().exec(id);
			if ( resultat.next() ) 
			{*/
				/* Puis initialisation de la proprieÌ�teÌ� id du bean Utilisateur avec sa valeur */
				/*commercant.setId( resultat.getInt("id_commercant") ); 
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
