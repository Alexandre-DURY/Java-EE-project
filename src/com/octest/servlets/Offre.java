package com.octest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.dao.DaoFactory;
import com.octest.dao.VoitureDao;

/**
 * Servlet implementation class Offre
 */
@WebServlet("/Offre")
public class Offre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VoitureDao voitureDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Offre() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.voitureDao = daoFactory.getVoitureDao();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("voiture", voitureDao.getVoiture(request.getParameter("id")));
		this.getServletContext().getRequestDispatcher("/WEB-INF/offre.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
