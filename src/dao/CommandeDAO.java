package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Commande;

public class CommandeDAO {
	private DAOFactory daoFactory;

	public CommandeDAO(DAOFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	public void creer(Commande commande) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null; 
		ResultSet resultat=null;
		commande.setEtat("Prise en charge par nos livreurs");

		/*requete sql pour inserer un utilisateur dans la base*/
		String sql="INSERT INTO commande VALUES (null,'"+commande.getDestinataire()+"','"+commande.getAdresse_livraison()+"','"+commande.getMontant()+"','"+commande.getEtat()+"');";
		/*requete sql pour recuperer l'id du client nouvellement creer*/
		String id="SELECT numero_commande FROM commande WHERE destinataire='"+commande.getDestinataire()+"' and adresse_livraison='"+commande.getAdresse_livraison()+"' and montant='"+commande.getMontant()+ "' and etat='"+commande.getEtat()+"';";
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
				/* Puis initialisation de la proprieÌ�teÌ� id du bean Utilisateur avec sa valeur */
				commande.setId( resultat.getInt("numero_commande") ); 
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
