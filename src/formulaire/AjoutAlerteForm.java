package formulaire;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Livreur;
import beans.Message;
import dao.DAOException;
import dao.MessageDAO;

public class AjoutAlerteForm {
	private static final String CHAMP_MSG = "texte";
	public static final String SESSION_COMMERCANT = "connexionCommercant";
	public static final String SESSION_LIVREUR = "connexionLivreur";
	public static final String SESSION_CLIENT = "connexionClient";
	
	private MessageDAO messageDAO;
	
	private Message message;
	private Livreur livreur;
	
	public AjoutAlerteForm(MessageDAO messageDao) {
		super();
		this.messageDAO = messageDao;
	}

	public boolean declarerIncident(HttpServletRequest request) {
		// TODO Auto-generated method stub

		boolean resultat = true;

		String msg = getValeurChamp( request, CHAMP_MSG );
		
		HttpSession session = request.getSession();
		livreur = (Livreur) session.getAttribute(SESSION_LIVREUR);

		message = new Message();
		message.setId_livreur(livreur.getId());
		message.setMsg(msg);

		if(msg==null)
		{
			return false;
		}
		else
		{
			if(resultat!=false)
			{
				try 
				{
					messageDAO.creer(message);
					return true;
				}
				catch ( DAOException e ) 
				{
					e.printStackTrace();
					return false;
				}
			}
			else
			{
				return resultat;
			}
		}		
	}

	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
		String valeur = request.getParameter( nomChamp );
		if ( valeur == null || valeur.trim().length() == 0 )
		{
			return null; 
		} else {
			return valeur.trim();
		}
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
}
