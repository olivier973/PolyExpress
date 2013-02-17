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
	
		
		/*requete sql pour inserer une liste d'objet dans la base*/
		String sql="INSERT INTO listeobjets VALUES (null,'"+listeObjets.getId_penseBete()+"','"+listeObjets.getId_objet()+"');";
		/*requete sql pour recuperer l'id de listeObjets nouvellement creer*/
		//String id="SELECT id_list FROM listeobjets WHERE id_pense_bete='"+listeObjets.getId_penseBete()+"'and id_objet='"+listeObjets.getId_objet()+"';";
		String id="SELECT LAST_INSERT_ID() as id_list";
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
			resultat = daoFactory.getConnexion().exec(id);
			if ( resultat.next() ) 
			{
				/* Puis initialisation de la proprieÌ�teÌ� id du bean ListeObjet avec sa valeur */
				listeObjets.setId( resultat.getInt("id_list") ); 
			} else 
			{
				throw new DAOException( "EÌ�chec de la creÌ�ation de l'utilisateur en base, aucun ID auto-geÌ�neÌ�reÌ� retourneÌ�." );
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
