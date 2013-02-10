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

	public static DAOFactory getInstance()
	{
		String url = "127.0.0.1:3306/java";//"127.0.0.1:3306/java" ou "localhost:8889/java"
		String mdp = "";//"" ou "root"
		String user = "root";//"root"
		return new DAOFactory(url, mdp, user);
	}

	public Connection getConnection()
	{
		this.connexion=new ConnexionBdd(url, user, mdp);
		this.connexion.connect();
		return connexion.getDbConnect();
	}
	public ConnexionBdd getConnexion() {
		return connexion;
	}

	public void setConnexion(ConnexionBdd connexion) {
		this.connexion = connexion;
	}
}
