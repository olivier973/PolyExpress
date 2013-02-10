package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.DAOFactory;
import dao.UserDAO;
import formulaire.InscriptionClientForm;
import formulaire.InscriptionCommercantForm;

/**
 * Servlet implementation class InscriptionCommercantFormServlet
 */

public class InscriptionCommercantFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_CLIENT = "client";
	public static final String ATT_FORM  = "form";
	public static final String VUE= "/WEB-INF/menuConnection.jsp";
	
	private UserDAO commercantDao;
	

	
	public void init() throws ServletException
	{
		this.commercantDao=((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getCommercantDAO();
	}
    public InscriptionCommercantFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stu
		/* affichage de la page d'inscription */
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/* Préparation de l'objet formulaire */
		
		InscriptionCommercantForm form = new InscriptionCommercantForm( commercantDao );
		
		/* traitement de la requête et récupération du bean en résultant */
		User user = form.inscrireCommercant( request);
		/* Stockage du formulaire et du bean dans l'objet request*/
		
		request.setAttribute( ATT_FORM, form ); 
		request.setAttribute( ATT_CLIENT, user );
		this.doGet(request, response);
	}

}
