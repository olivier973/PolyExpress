package connexion;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnexionBdd {
	private String dbURL = "";
	private String user = "";
	private String password = "";
	private java.sql.Connection dbConnect = null;
	private java.sql.Statement dbStatement = null;

	public java.sql.Connection getDbConnect() {
		return dbConnect;
	}

	public void setDbConnect(java.sql.Connection dbConnect) {
		this.dbConnect = dbConnect;
	}

	/**
	 * Constructeur
	 * @param url
	 * @param user
	 * @param password
	 */
	public ConnexionBdd(String url, String user, String password) {
		this.dbURL = url;
		this.user = user;
		this.password = password;
	}

	/**
	 * Connecter à la base de donnée
	 * @return false en cas d'échec
	 */
	public Boolean connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.dbConnect = DriverManager.getConnection("jdbc:mysql://" + this.dbURL, this.user, this.password);
			this.dbStatement = this.dbConnect.createStatement();
			return true;
		} catch (SQLException ex) {
			System.out.println("SQL Exception Connexion");
		} catch (ClassNotFoundException ex) {
			System.out.println("Exception Connexion classe");
		} catch (InstantiationException ex) {
			System.out.println("Exception Connexion instanciation");
		} catch (IllegalAccessException ex) {
			System.out.println("Exception Connexion accès");
		}
		return false;
	}

	/**
	 * Se connecter, executer une requete SQL, fermer la connexion
	 * @param sql
	 * @return resultat de la requete
	 */
	public ResultSet requete(String sql) {
		if(this.connect()) {
			ResultSet rs = this.exec(sql);
			return rs;
		} else {
			System.out.println("Mysql connection failed !!!");
		}
		this.close();
		return null;
	}

	/**
	 * Executer une requete SQL
	 * @param sql
	 * @return resultat de la requete
	 */
	public ResultSet exec(String sql) {
		try {
			ResultSet rs = this.dbStatement.executeQuery(sql);
			return rs;
		} catch (SQLException ex) {
			System.out.println("SQL Exception Requête");
		}
		return null;
	}

	/**
	 * Fermer la connexion au serveur de DB
	 */
	public void close() {
		try {
			this.dbStatement.close();
			this.dbConnect.close();
		} catch (SQLException ex) {
			System.out.println("SQL Exception Close");
		}
	}

	public java.sql.Statement getDbStatement() {
		return dbStatement;
	}

	public void setDbStatement(java.sql.Statement dbStatement) {
		this.dbStatement = dbStatement;
	}

	public boolean identifiantsValides(String login, String motDePasse, String type)
	{
		boolean presents = true;
		ResultSet rs;

		rs = requete("SELECT count(*) FROM " + type + " WHERE email='"+ login +"' and mdp='" + motDePasse + "';");

		if(rs != null)
		{
			try {
				rs.next();
				if(rs.getString(1).equals("0"))
					presents = false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return presents;
	}
}