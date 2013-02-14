package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Produit;

/**
 * Servlet implementation class ValidationPanier
 */
public class ValidationPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE = "/WEB-INF/affichageMessage.jsp";
	private static final String MESS_BON = "Commande validée.";
	public static final String PAGE_CONNEXION = "/WEB-INF/authentification.jsp";
	public static final String SESSION_COMMERCANT = "connexionCommercant";
	public static final String SESSION_LIVREUR = "livreurConnexion";
	public static final String SESSION_CLIENT = "clientConnexion";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidationPanier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = PAGE_CONNEXION;
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute(SESSION_COMMERCANT)!=null || session.getAttribute(SESSION_LIVREUR)!=null)
		{
			session.invalidate();
		}
		else if(session.getAttribute(SESSION_CLIENT)!=null)
		{
			request.setAttribute("message", MESS_BON);
			page = PAGE;
			
			List<Produit> listepanier;
			listepanier = (List<Produit>) session.getAttribute("listepanier");
			if(listepanier != null)
			{
				listepanier = new ArrayList<Produit>();
			}
			float montant = 0;
			request.setAttribute("montant", montant);
			
			session.setAttribute("listepanier", listepanier);
			System.out.println(montant);
			System.out.println(listepanier);
		}
		this.getServletContext().getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}