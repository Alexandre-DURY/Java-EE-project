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
import com.octest.dao.VoitureDao;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;
	private VoitureDao voitureDao;
    public Accueil() {
        super();
    }
    
    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
        this.voitureDao = daoFactory.getVoitureDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Base tableUsers = new Base();
        request.setAttribute("utilisateurs", tableUsers.recupererUtilisateurs());*/
		if(request.getParameterMap().containsKey("logout")) {
			HttpSession session = request.getSession();  
			session.removeAttribute("pseudo");
		}
		 request.setAttribute("utilisateurs", utilisateurDao.lister());
		 request.setAttribute("voitures", voitureDao.lister());
		this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
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
	            this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	            }
	        else{
	            PrintWriter out = response.getWriter() ;
	            out.println("<p>Connexion impossible ! le couple mot de passe/ identifiant est incorrecte.<p>");
	            request.setAttribute("erreur", "oui");
	            this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	        };
	        request.setAttribute("voitures", voitureDao.lister());
	        request.setAttribute("utilisateurs", utilisateurDao.lister());



	    }
	

}
