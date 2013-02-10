package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Deconnexion
 */
public class Deconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String PAGE_DECONNEXION = "/acceuil";
	public static final String SESSION_COMMERCANT = "connexionCommercant";
	public static final String SESSION_LIVREUR = "livreurConnexion";
	public static final String SESSION_CLIENT = "clientConnexion";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Deconnexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		if(session.getAttribute(SESSION_COMMERCANT)!=null || session.getAttribute(SESSION_CLIENT)!=null || session.getAttribute(SESSION_LIVREUR)!=null)
		{
			session.invalidate();
		}

		this.getServletContext().getRequestDispatcher(PAGE_DECONNEXION).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
