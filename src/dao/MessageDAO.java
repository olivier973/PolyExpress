package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;
import static dao.DAOUtilitaire.map;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Message;

public class MessageDAO {
	private DAOFactory daoFactory;

	public MessageDAO(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	public void creer(Message msg) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null; 
		ResultSet resultat=null;

		/*requete sql pour inserer un Message dans la base*/
		String sql="INSERT INTO message VALUES (null,'"+msg.getId_livreur()+"','"+msg.getMsg()+"');";
		/*requete sql pour recuperer l'id du message nouvellement creer*/
		//String id="SELECT id_msg FROM message WHERE id_livreur='"+msg.getId_livreur()+"' and texte_traffic='"+msg.getMsg()+"';";
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
			/* Puis initialisation de la proprieÌ�teÌ� id du bean Lessage avec sa valeur */
			/*msg.setId( resultat.getInt("id_msg") ); 
			} else 
			{
				throw new DAOException( "EÌ�chec de la creÌ�ation d un meessage en base, aucun ID auto-geÌ�neÌ�reÌ� retourneÌ�." );
			}*/
		} catch ( SQLException e )
		{
			throw new DAOException( e ); 
		} finally 
		{
			fermeturesSilencieuses( resultat,daoFactory.getConnexion().getDbStatement(),  connexion );
		}
	}

	public List<Message> trouver() throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null; 
		ResultSet resultSet = null;
		Message message = null;
		List<Message> listemessages = new ArrayList<Message>();
		String sql="SELECT * FROM message order by id_msg desc;";
		try
		{
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			resultSet = daoFactory.getConnexion().exec(sql);
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while ( resultSet.next() )
			{
				message = (Message)map( resultSet, this );
				listemessages.add(message);
			}
		} catch ( SQLException e )
		{
			throw new DAOException( e );
		} finally 
		{
			fermeturesSilencieuses( resultSet,daoFactory.getConnexion().getDbStatement(),  connexion );
		}
		return listemessages;
	}
}