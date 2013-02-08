package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Client;

/**
 * Servlet implementation class Connexion
 */
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SERVLET_CLIENT = "/clientServlet";
	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_TYPE = "type";
	private static final String CHAMP_MDP ="motdepasse";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Connexion() {
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
		String login = request.getParameter(CHAMP_LOGIN);
		String motDePasse = request.getParameter(CHAMP_MDP);
		String type = request.getParameter(CHAMP_TYPE);

		if(type.toString().equals("client"))
		{
			Client client = new Client();
			//client.setNom();
			//request.setAttribute("nomConnexion", client);

			//HttpSession session = request.getSession();

			//variable de session
			//String exemple = "abc";
			//session.setAttribute("chaine", exemple);
			//Récupération de l'objet depuis la session
			//String chaine = (String)session.getAttribute("chaine");

			this.getServletContext().getRequestDispatcher(SERVLET_CLIENT).forward(request, response);
		}
	}
}