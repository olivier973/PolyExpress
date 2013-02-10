package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Commercant;
import connexion.ConnexionBdd;

/**
 * Servlet implementation class SupprimerProduit
 */
public class SupprimerProduit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHAMP_ID = "id";
	private static final String MESS_BON = "Produit supprimé.";
	private static final String JSP_SUPP = "/WEB-INF/produitsupprime.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupprimerProduit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter(CHAMP_ID);
		String message;
		String page = JSP_SUPP;
		message = MESS_BON;

		ConnexionBdd ConnexionBdd = new ConnexionBdd("127.0.0.1:3306/java", "root", "");

		ResultSet rs;
		HttpSession session = request.getSession();
		Commercant commercant = (Commercant) session.getAttribute("connexionCommercant");

		if(ConnexionBdd.connect())
		{
			if((rs = ConnexionBdd.exec("SELECT * FROM produit WHERE reference='" + id + "' and id_commercant='" + commercant.getId() + "';")) != null)
			{
				try {
					while (rs.next()) {
						rs.deleteRow();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			System.out.println("Mysql connection failed !!!");
		}
		ConnexionBdd.close();
		//request.setAttribute("message", message);
		//this.getServletContext().getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
