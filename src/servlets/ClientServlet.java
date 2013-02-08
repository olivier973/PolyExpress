package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connexion.ConnexionBdd;

/**
 * Servlet implementation class Client
 */
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ConnexionBdd ConnexionBdd = new ConnexionBdd("127.0.0.1:3306/java", "root", "");

		ResultSet rs;
		if((rs = ConnexionBdd.requete("SELECT * FROM client")) != null)
		{
			try {
				while (rs.next()) {
					System.out.println("Valeur: " + rs.getString(1));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//transmettre une requete garde les paramètres précèdents dont les posts
		this.getServletContext().getRequestDispatcher("/WEB-INF/client.jsp").forward(request, response);
	}

}
