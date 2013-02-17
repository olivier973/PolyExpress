package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAOFactory;
import dao.MessageDAO;
import formulaire.AjoutAlerteForm;

/**
 * Servlet implementation class DeclarerIncidentFormServlet
 */
public class DeclarerIncidentFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_PRODUIT = "message_alerte";
	public static final String ATT_FORM  = "form";
	public static final String VUE_MESS= "/WEB-INF/ajoutAlerte.jsp";
	public static final String JSP_LIVREUR = "/authentificationServlet";
	
	public static final String SESSION_COMMERCANT = "connexionCommercant";
	public static final String SESSION_LIVREUR = "connexionLivreur";
	public static final String SESSION_CLIENT = "connexionClient";

	public static final String PAGE_CONNEXION = "/authentificationServlet";

	private MessageDAO messageDao;

	public void init() throws ServletException
	{
		this.messageDao=((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY)).getMessageDAO();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeclarerIncidentFormServlet() {
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
		String page = PAGE_CONNEXION;
		HttpSession session = request.getSession();

		if(session.getAttribute(SESSION_CLIENT)!=null || session.getAttribute(SESSION_COMMERCANT)!=null)
		{
			session.invalidate();
		}
		else if(session.getAttribute(SESSION_LIVREUR)!=null)
		{
			AjoutAlerteForm form = new AjoutAlerteForm( messageDao );

			/* traitement de la requeÌ‚te et reÌ�cupeÌ�ration du bean en reÌ�sultant */

			boolean res;
			res = form.declarerIncident(request);
			if(res==false)
			{
				request.setAttribute(ATT_PRODUIT, form.getMessage());
				page = VUE_MESS;
			}
			else
			{
				page = JSP_LIVREUR;
			}
			//this.getServletContext().getRequestDispatcher(page).forward(request, response);
		}
		this.getServletContext().getRequestDispatcher(page).forward(request, response);
	}
}
