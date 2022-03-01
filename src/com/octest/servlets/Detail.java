package com.octest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.octest.dao.DaoFactory;
import com.octest.dao.UtilisateurDao;
import com.octest.dao.VoitureDao;

/**
 * Servlet implementation class Detail
 */
@WebServlet("/Detail")
public class Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao; 
	private VoitureDao voitureDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public Detail() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
        this.voitureDao = daoFactory.getVoitureDao();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int clickedVendeurId = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("vendeur", utilisateurDao.listerUtilisateur(clickedVendeurId));
		request.setAttribute("offres", voitureDao.listerOffresUtil(clickedVendeurId));
		response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher("/WEB-INF/detail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
