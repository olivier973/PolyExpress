package servlets;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import beans.Produit;

import dao.DAOFactory;
import dao.ProduitDAO;

public class menuConnection extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProduitDAO produitDao;
	public static final String CONF_DAO_FACTORY = "daofactory";
	private static final String PAGE = "/WEB-INF/menuConnection.jsp";

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur
		 */
		this.produitDao = new ProduitDAO((DAOFactory)getServletContext().getAttribute(CONF_DAO_FACTORY));
	}

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

		List<Produit> listeproduits = new ArrayList<Produit>();
		if((listeproduits =(ArrayList<Produit>) produitDao.trouver())!=null) {
			request.setAttribute("listeproduits", listeproduits);
		}
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		this.doGet(request, response);
	}
}