package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.ListeObjets;;

public class ListeObjetsDAO {
	private DAOFactory daoFactory;

	public ListeObjetsDAO(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	public void creer(ListeObjets listeObjets) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null; 
		ResultSet resultat=null;
	
		
		/*requete sql pour inserer un utilisateur dans la base*/
		String sql="INSERT INTO commande VALUES (null,'"+listeObjets.getId_penseBete()+"','"+listeObjets.getId_objet()+"');";
		/*requete sql pour recuperer l'id de listeObjets nouvellement creer*/
		String id="SELECT id_list FROM listeobjets WHERE id_pense_bete='"+listeObjets.getId_penseBete()+"'and id_objet='"+listeObjets.getId_objet()+"';";
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
				listeObjets.setId( resultat.getInt("id_list") ); 
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
