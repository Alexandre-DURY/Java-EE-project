package com.octest.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octest.beans.Utilisateur;
import com.octest.dao.DaoFactory;
import com.octest.dao.UtilisateurDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;
    public Login() {
        super();
    }
    
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Base tableUsers = new Base();
        request.setAttribute("utilisateurs", tableUsers.recupererUtilisateurs());*/
		 request.setAttribute("utilisateurs", utilisateurDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();  
		Utilisateur utilisateur = new Utilisateur();
	        String pseudo = request.getParameter("login");
	        String mdp = request.getParameter("pass");
	        utilisateurDao.ajouter(utilisateur);
	        if(utilisateurDao.testLogin(pseudo,mdp))
	            {
	        	session.setAttribute("pseudo", pseudo);
	        	session.setAttribute("mdp", mdp);
	        	/*utilisateur.setPseudo(pseudo);
	        	utilisateur.setMdp(mdp);*/
	        	request.setAttribute("erreur", "non");
	        	request.setAttribute("utilisateurs", utilisateurDao.lister());
	        	request.setAttribute("utilisateur", utilisateur);
	            }
	        else{
	            PrintWriter out = response.getWriter() ;
	            out.println("<p>Connexion impossible ! le couple mot de passe/ identifiant est incorrecte.<p>");
	            request.setAttribute("erreur", "oui");
	            
	        };
	        this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	        request.setAttribute("utilisateurs", utilisateurDao.lister());



	    }
	

}
