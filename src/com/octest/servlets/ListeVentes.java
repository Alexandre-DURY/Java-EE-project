package com.octest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octest.dao.DaoFactory;
import com.octest.dao.UtilisateurDao;
import com.octest.dao.VoitureDao;

/**
 * Servlet implementation class ListeVentes
 */
@WebServlet("/ListeVentes")
public class ListeVentes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;  
	private VoitureDao voitureDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
	 public void init() throws ServletException {
	        DaoFactory daoFactory = DaoFactory.getInstance();
	        this.utilisateurDao = daoFactory.getUtilisateurDao();
	        this.voitureDao = daoFactory.getVoitureDao();
	 } 
    public ListeVentes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pseudo = session.getAttribute("pseudo").toString();
		System.out.println(pseudo);
		request.setAttribute("mesArticles", voitureDao.ListerOffres(pseudo));
		this.getServletContext().getRequestDispatcher("/WEB-INF/mesVentes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
