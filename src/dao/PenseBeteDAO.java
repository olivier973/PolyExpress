package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Commande;
import beans.PenseBete;

public class PenseBeteDAO {
	private DAOFactory daoFactory;

	public PenseBeteDAO(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	public void creer(PenseBete penseBete) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null; 
		ResultSet resultat=null;
	
		
		/*requete sql pour inserer un utilisateur dans la base*/
		String sql="INSERT INTO commande VALUES (null,'"+penseBete.getId_commande()+"',null);";
		/*requete sql pour recuperer l'id du client nouvellement creer*/
		String id="SELECT id_pense_bete FROM pensebete WHERE id_commande='"+penseBete.getId_commande()+"';";
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
				penseBete.setId( resultat.getInt("id_pense_bete") ); 
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
}
