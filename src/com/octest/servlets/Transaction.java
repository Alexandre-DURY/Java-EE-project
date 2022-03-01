package com.octest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.octest.beans.Voiture;
import com.octest.dao.DaoFactory;
import com.octest.dao.VoitureDao;

/**
 * Servlet implementation class Transaction
 */
@WebServlet("/Transaction")
public class Transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VoitureDao voitureDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transaction() {
        super();
        // TODO Auto-generated constructor stub
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.voitureDao = daoFactory.getVoitureDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("pseudo"); // Récupération du nom de l'utilisateur
		request.setAttribute("transactions", voitureDao.listerTransactions(user,true));
		this.getServletContext().getRequestDispatcher("/WEB-INF/transaction.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("pseudo"); // Récupération du nom de l'utilisateur
		Voiture voiture = new Voiture();
		String id = request.getParameter("id");
		String prix = request.getParameter("prix");
		voiture = voitureDao.getVoiture(id);
		request.setAttribute("id", id);
		request.setAttribute("prix", prix);
		request.setAttribute("voiture", voiture);
		voitureDao.addtransaction(id,prix,user);
		voitureDao.supprimerVoiture(id);
		doGet(request, response);
	}

}
