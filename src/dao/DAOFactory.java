package dao;

import java.sql.Connection;

import connexion.ConnexionBdd;

public class DAOFactory {

	private static final String URL_B = "127.0.0.1:3306/java";
	private static final String URL_O = "localhost:8889/java";
	private static final String MDP_B = "";
	private static final String IDENT = "root";

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
		String url = URL_O; // URL_B ou URL_O
		String mdp = IDENT; // MDP_B ou IDENT
		String user = IDENT;
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

	public ClientDAO getClientDAO()
	{
		return new ClientDAO(this);
	}
	public CommercantDAO getCommercantDAO()
	{
		return new CommercantDAO(this);
	}
	public ProduitDAO getProduitDAO()
	{
		return new ProduitDAO(this);
	}
	public LivreurDAO getLivreurDAO()
	{
		return new LivreurDAO(this);
	}
	public CommandeDAO getCommandeDAO()
	{
		return new CommandeDAO(this);
	}
	public PenseBeteDAO getPenseBeteDAO()
	{
		return new PenseBeteDAO(this);
	}
	public ListeObjetsDAO getListeObjetsDAO()
	{
		return new ListeObjetsDAO(this);
	}
	public MessageDAO getMessageDAO()
	{
		return new MessageDAO(this);
	}
}