package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Client;
import beans.Commercant;
import connexion.ConnexionBdd;

/**
 * Servlet implementation class Connexion
 */
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_TYPE = "type";
	private static final String CHAMP_MDP ="motdepasse";
	private static final String MESS_CHPS_VIDES ="Erreur - Vous n'avez pas rempli tous les	champs obligatoires. <br/> <a href=\"authentificationServlet\">Cliquez ici</a> pour accéder au formulaire de connexion.";
	private static final String MESS_MAUVAIS_IDENT = "Erreur - Identifiants incorrectes. <br/> <a href=\"authentificationServlet\">Cliquez ici</a> pour accéder au formulaire de connexion.";
	private static final String MESS_BON = "Bienvenue sur votre espace";
	private static final String JSP_CLIENT = "/WEB-INF/client.jsp";
	private static final String JSP_COMMERCANT = "/WEB-INF/commercant.jsp";
	private static final String JSP_ERREUR = "/WEB-INF/erreur.jsp";

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
		String message;
		String page = JSP_ERREUR;

		ConnexionBdd ConnexionBdd = new ConnexionBdd("127.0.0.1:3306/java", "root", "");

		if (login.trim().isEmpty() || motDePasse.trim().isEmpty()) {
			message = MESS_CHPS_VIDES;
		}
		else if(ConnexionBdd.identifiantsValides(login,motDePasse,type)==false) {
			message = MESS_MAUVAIS_IDENT;
		}
		else {
			message = MESS_BON;

			ResultSet rs;

			if((rs = ConnexionBdd.requete("SELECT * FROM " + type + " WHERE email='"+ request.getParameter(CHAMP_LOGIN) +"'")) != null)
			{
				try {
					while (rs.next()) {
						if(type.toString().equals("client"))
						{
							System.out.println("Valeur: " + rs.getString(2) + " " + rs.getString(3));
							Client client = new Client();
							client.setNom(rs.getString(2));
							client.setPrenom(rs.getString(3));
							request.setAttribute("clientConnexion", client);
							page = JSP_CLIENT;
						}
						else if(type.toString().equals("commercant"))
						{
							System.out.println("Valeur: " + rs.getString(2) + " " + rs.getString(3));
							Commercant commercant = new Commercant();
							commercant.setNom(rs.getString(2));
							commercant.setPrenom(rs.getString(3));
							request.setAttribute("commercantConnexion", commercant);
							page = JSP_COMMERCANT;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//HttpSession session = request.getSession();

			//variable de session
			//String exemple = "abc";
			//session.setAttribute("chaine", exemple);
			//Récupération de l'objet depuis la session
			//String chaine = (String)session.getAttribute("chaine");
		}
		request.setAttribute("message", message);
		this.getServletContext().getRequestDispatcher(page).forward(request, response);
	}
}