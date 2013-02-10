package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Client;
import beans.Commercant;
import beans.Livreur;
import beans.Produit;
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
	private static final String JSP_LIVREUR = "/WEB-INF/livreur.jsp";
	private static final String JSP_ERREUR = "/WEB-INF/erreur.jsp";
	private static final String SESSION_COMMERCANT = "listeCommercantsConnectes";

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
							System.out.println("Valeur: " + rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
							Commercant commercant = new Commercant();
							commercant.setId(rs.getInt(1));
							commercant.setNom(rs.getString(2));
							commercant.setPrenom(rs.getString(3));
							
							HttpSession session = request.getSession();
							Map<Integer, Commercant> commercants = (HashMap<Integer, Commercant>)	session.getAttribute(SESSION_COMMERCANT);
							/* Si aucune map n'existe, alors initialisation d'une nouvelle map */
							if ( commercants == null ) {
							commercants = new HashMap<Integer, Commercant>();
							}
							/* Puis ajout du commercant courant dans la map */
							commercants.put( commercant.getId(), commercant ); //id de session courante a la place de commercant.getId()
							/* Et enfin (ré)enregistrement de la map en session
							*/
							session.setAttribute( SESSION_COMMERCANT , commercants );
							
							session.setAttribute("connexionCommercant", commercant);
							page = JSP_COMMERCANT;

							List<Produit> listeproduits = new ArrayList<Produit>();
							ResultSet rsc;
							if((rsc = ConnexionBdd.requete("select nom, quantite, prix, description, reference from produit where id_commercant=" + commercant.getId() + ";")) != null)
							{
								while (rsc.next()) {
									Produit produit = new Produit();
									produit.setId(rsc.getInt(5));
									produit.setNom(rsc.getString(1));
									produit.setPrix(rsc.getInt(3));
									produit.setQuantite(rsc.getInt(2));
									produit.setDescription(rsc.getString(4));
									listeproduits.add(produit);
								}
								request.setAttribute("listeproduits", listeproduits);
							}
						}
						else if(type.toString().equals("livreur"))
						{
							System.out.println("Valeur: " + rs.getString(2) + " " + rs.getString(3));
							Livreur livreur = new Livreur();
							livreur.setNom(rs.getString(2));
							livreur.setPrenom(rs.getString(3));
							request.setAttribute("livreurConnexion", livreur);
							page = JSP_LIVREUR;
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		request.setAttribute("message", message);
		this.getServletContext().getRequestDispatcher(page).forward(request, response);
	}
}