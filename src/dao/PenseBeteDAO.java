package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		System.out.println("commande:"+penseBete.getId_commande());

		/*requete sql pour inserer un pense bete dans la base*/
		String sql="INSERT INTO pensebete VALUES (null,'"+penseBete.getId_commande()+"',null);";
		/*requete sql pour recuperer l'id du pense bete nouvellement creer*/
		//String id="SELECT id_pense_bete FROM pensebete WHERE id_commande='"+penseBete.getId_commande()+"';";
		String id="SELECT LAST_INSERT_ID() as id_pense_bete";
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
				/* Puis initialisation de la proprieÌ�teÌ� id du bean PenseBete avec sa valeur */
				penseBete.setId( resultat.getInt("id_pense_bete") ); 
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
	
	public void modifierLivreur(int i, String id) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		int valide;
		ResultSet resultat=null;
		/*requete sql pour inserer un produit dans la base*/
		String sql="update pensebete set id_livreur='"+i+"' where id_pense_bete='"+id+"';";
		System.out.println(sql);
		try 
		{
			/* ReÌ�cupeÌ�ration d'une connexion depuis la Factory */ 
			connexion = daoFactory.getConnection();
			valide= daoFactory.getConnexion().getDbStatement().executeUpdate(sql);
			/* Analyse du statut retourneÌ� par la requeÌ‚te d'insertion */ 
			if ( valide==0)
			{
				throw new DAOException( "echec de la modification du produit, aucune ligne modifiée dans la table." );
			}
			/* ReÌ�cupeÌ�ration de l'id auto-geÌ�neÌ�reÌ� par la requeÌ‚te d'insertion */
		} catch ( SQLException e )
		{
			throw new DAOException( e ); 
		} finally
		{
			fermeturesSilencieuses(resultat,daoFactory.getConnexion().getDbStatement(), connexion);
		}
	}
}
