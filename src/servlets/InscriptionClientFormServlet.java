package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.UserDAO;
import formulaire.InscriptionClientForm;

/**
 * Servlet implementation class InscriptionServlet
 */

public class InscriptionClientFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_CLIENT = "client";
	public static final String ATT_FORM  = "form";
	public static final String VUE_CLIENT= "/WEB-INF/inscriptionClient.jsp";
	public static final String VUE= "/authentificationServlet";

	public static final String SESSION_COMMERCANT = "connexionCommercant";
	public static final String SESSION_LIVREUR = "connexionLivreur";
	public static final String SESSION_CLIENT = "connexionClient";

	private UserDAO clientDao;

	public void init() throws ServletException
	{
		this.clientDao=((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getClientDAO();
	}
	public InscriptionClientFormServlet() {
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
		
		HttpSession session = request.getSession();
		if(session.getAttribute(SESSION_CLIENT)!=null || session.getAttribute(SESSION_LIVREUR)!=null || session.getAttribute(SESSION_COMMERCANT)!=null)
		{
			session.invalidate();
		}
		/* PreÌ�paration de l'objet formulaire */
		InscriptionClientForm form = new InscriptionClientForm( clientDao );

		/* traitement de la requeÌ‚te et reÌ�cupeÌ�ration du bean en reÌ�sultant */

		String page = null;
		boolean res;
		res = form.inscrireClient(request);
		if(res==false)
		{
			request.setAttribute(ATT_CLIENT, form.getClient());
			page = VUE_CLIENT;
		}
		else
		{
			page = VUE;
		}
		this.getServletContext().getRequestDispatcher(page).forward(request, response);
	}
}
