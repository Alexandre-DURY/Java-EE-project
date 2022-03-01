package com.octest.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octest.dao.DaoFactory;
import com.octest.dao.UtilisateurDao;

/**
 * Servlet implementation class Options
 */
@WebServlet("/Options")
public class Options extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;
	      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Options() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

		this.getServletContext().getRequestDispatcher("/WEB-INF/options.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
	        String pseudo = request.getParameter("pseudo");
	        String mdp = request.getParameter("mdp");
	        String nom = request.getParameter("nom");
	        String prenom = request.getParameter("prenom");
	        String email = request.getParameter("email");
	        String description = request.getParameter("description");
        	System.out.println(description);
        	String ancienPseudo = session.getAttribute("pseudo").toString();
	        utilisateurDao.modifier(pseudo,mdp ,email, nom, prenom, description, ancienPseudo);
	        session.setAttribute("pseudo", pseudo);
        	session.setAttribute("mdp", mdp);
	        PrintWriter out = response.getWriter() ;
	        out.println("<p>Modification réussi !<p>");
	        this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

}
