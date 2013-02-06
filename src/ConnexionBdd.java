import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnexionBdd {
	private String dbURL = "";
	private String user = "";
	private String password = "";
	private java.sql.Connection dbConnect = null;
	private java.sql.Statement dbStatement = null;

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
	 * Connecter � la base de donn�e
	 * @return false en cas d'�chec
	 */
	public Boolean connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.dbConnect = DriverManager.getConnection("jdbc:mysql:" + this.dbURL, this.user, this.password);
			this.dbStatement = this.dbConnect.createStatement();
			return true;
		} catch (SQLException ex) {
			System.out.println("SQL Exception Connexion");
		} catch (ClassNotFoundException ex) {
			System.out.println("SQL Exception Connexion");
		} catch (InstantiationException ex) {
			System.out.println("SQL Exception Connexion");
		} catch (IllegalAccessException ex) {
			System.out.println("SQL Exception Connexion");
		}
		return false;
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
			System.out.println("SQL Exception Requ�te");
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnexionBdd ConnexionBdd = new ConnexionBdd("//localhost/java", "Java", "Java");
		if (ConnexionBdd.connect()) {
			try {
				ResultSet rs = ConnexionBdd.exec("SELECT * FROM client");
				if (rs != null) {
					while (rs.next()) {
						System.out.println("Valeur: " + rs.getString(1));
					}
				}
			} catch (SQLException ex) {
				System.out.println("SQL Exception");
			}
		} else {
			System.out.println("Mysql connection failed !!!");
		}
		ConnexionBdd.close();
	}
}