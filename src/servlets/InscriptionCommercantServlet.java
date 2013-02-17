package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class InscriptionCommercantServlet
 */
@WebServlet("/InscriptionCommercantServlet")public class InscriptionCommercantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_COMMERCANT = "connexionCommercant";
	public static final String SESSION_LIVREUR = "connexionLivreur";
	public static final String SESSION_CLIENT = "connexionClient";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionCommercantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	HttpSession session = request.getSession();
    	if(session.getAttribute(SESSION_CLIENT)!=null || session.getAttribute(SESSION_LIVREUR)!=null || session.getAttribute(SESSION_COMMERCANT)!=null)
		{
			session.invalidate();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/inscriptionCommercant.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
