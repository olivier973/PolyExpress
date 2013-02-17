package dao;

import static dao.DAOUtilitaire.fermeturesSilencieuses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Commande;
import beans.Commande_PenseBete;

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

		/*requete sql pour inserer une commande dans la base*/
		String sql="INSERT INTO commande VALUES (null,'"+commande.getDestinataire()+"','"+commande.getAdresse_livraison()+"','"+commande.getMontant()+"','"+commande.getEtat()+"');";
		/*requete sql pour recuperer l'id d une commande nouvellement creer*/
		//String id="SELECT numero_commande FROM commande WHERE destinataire='"+commande.getDestinataire()+"' and adresse_livraison='"+commande.getAdresse_livraison()+"' and montant='"+commande.getMontant()+ "' and etat='"+commande.getEtat()+"';";
		String id="SELECT LAST_INSERT_ID() as numero_commande";
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

	public List<Commande_PenseBete> trouverSansLivreur() throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null; 
		ResultSet resultSet = null;
		Commande_PenseBete commande = null;
		List<Commande_PenseBete> listecommandes = new ArrayList<Commande_PenseBete>();
		String sql="SELECT * FROM commande join pensebete on numero_commande=id_commande;";
		try
		{
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			resultSet = daoFactory.getConnexion().exec(sql);
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while ( resultSet.next() )
			{
				commande = DAOUtilitaire.mapCb(resultSet);
				listecommandes.add(commande);
			}
		} catch ( SQLException e )
		{
			throw new DAOException( e );
		} finally 
		{
			fermeturesSilencieuses( resultSet,daoFactory.getConnexion().getDbStatement(),  connexion );
		}
		return listecommandes;
	}

	public List<Commande_PenseBete> trouverLivreur(int id_livreur) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null; 
		ResultSet resultSet = null;
		Commande_PenseBete commande = null;
		List<Commande_PenseBete> listecommandes = new ArrayList<Commande_PenseBete>();
		String sql="SELECT * FROM commande join pensebete on numero_commande=id_commande where id_livreur='"+id_livreur+"';";
		try
		{
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			resultSet = daoFactory.getConnexion().exec(sql);
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while ( resultSet.next() )
			{
				commande = DAOUtilitaire.mapCb(resultSet);
				listecommandes.add(commande);
			}
		} catch ( SQLException e )
		{
			throw new DAOException( e );
		} finally 
		{
			fermeturesSilencieuses( resultSet,daoFactory.getConnexion().getDbStatement(),  connexion );
		}
		return listecommandes;
	}
	
	public List<Commande_PenseBete> trouverDestinataire(int destinataire) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null; 
		ResultSet resultSet = null;
		Commande_PenseBete commande = null;
		List<Commande_PenseBete> listecommandes = new ArrayList<Commande_PenseBete>();
		String sql="SELECT * FROM commande join pensebete on numero_commande=id_commande where destinataire='"+destinataire+"';";
		try
		{
			/* Recuperation d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			resultSet = daoFactory.getConnexion().exec(sql);
			/* Parcours de la ligne de donnees de l'eventuel ResulSet retourne */
			while ( resultSet.next() )
			{
				commande = DAOUtilitaire.mapCb(resultSet);
				listecommandes.add(commande);
			}
		} catch ( SQLException e )
		{
			throw new DAOException( e );
		} finally 
		{
			fermeturesSilencieuses( resultSet,daoFactory.getConnexion().getDbStatement(),  connexion );
		}
		return listecommandes;
	}

	public void modifierEtat(String etat, String id) throws DAOException {
		// TODO Auto-generated method stub
		Connection connexion = null;
		int valide;
		ResultSet resultat=null;
		/*requete sql pour inserer un produit dans la base*/
		String sql="update commande set etat='"+etat+"' where numero_commande='"+id+"';";
		System.out.println(sql);
		try 
		{
			/* ReÌ�cupeÌ�ration d'une connexion depuis la Factory */ 
			connexion = daoFactory.getConnection();
			valide= daoFactory.getConnexion().getDbStatement().executeUpdate(sql);
			/* Analyse du statut retourneÌ� par la requeÌ‚te d'insertion */ 
			if ( valide==0)
			{
				throw new DAOException( "echec de la modification du prouit, aucune ligne modifiée dans la table." );
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