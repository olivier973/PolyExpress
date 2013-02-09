package dao;

import java.sql.Connection;

import connexion.ConnexionBdd;

public class DAOFactory {
	private String url;
	private String mdp;
	private String user;
	private ConnexionBdd connexion;
	

	public DAOFactory(String url, String mdp, String user) {
		super();
		this.url = url;
		this.mdp = mdp;
		this.user = user;
	}
	
	public DAOFactory getInstance()
	{
		this.url = "localhost:8889/java";
		this.mdp = "root";
		this.user ="root";
		return new DAOFactory(url, mdp, user);
	}
	
	public Connection getConnection()
	{
		connexion=new ConnexionBdd(url, user, mdp);
		connexion.connect();
		return connexion.getDbConnect();
	}
	public ConnexionBdd getConnexion() {
		return connexion;
	}

	public void setConnexion(ConnexionBdd connexion) {
		this.connexion = connexion;
	}
}
