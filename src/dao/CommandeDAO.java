package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Commande;
import beans.User;

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
		String id="SELECT numero_commande FROM client WHERE destinataire='"+commande.getDestinataire()+"' and adresse_livraison='"+commande.getAdresse_livraison()+"' and montant='"+commande.getMontant()+ "' and etat='"+commande.getEtat()+"';";
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
				commande.setId( resultat.getInt("numero_commande") ); 
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
