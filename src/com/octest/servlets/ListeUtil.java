package com.octest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.dao.DaoFactory;
import com.octest.dao.UtilisateurDao;

/**
 * Servlet implementation class ListeUtil
 */
@WebServlet("/ListeUtil")
public class ListeUtil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;   
    /**
     * @see HttpServlet#HttpServlet()
     */

    public ListeUtil() {
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
		 request.setAttribute("utilisateurs", utilisateurDao.lister());
		 this.getServletContext().getRequestDispatcher("/WEB-INF/ListeUtilisateurs.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
