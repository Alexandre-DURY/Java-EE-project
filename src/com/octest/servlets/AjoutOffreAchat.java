package com.octest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.octest.beans.Negociation;
import com.octest.beans.Voiture;
import com.octest.dao.DaoFactory;
import com.octest.dao.NegociationDao;
import com.octest.dao.VoitureDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class AjoutOffreAchat
 */
@WebServlet("/AjoutOffreAchat")
public class AjoutOffreAchat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NegociationDao negociationDao;
	private VoitureDao voitureDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
	public AjoutOffreAchat() {
        super();
        // TODO Auto-generated constructor stub
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.negociationDao = daoFactory.getNegociateurDao();
        this.voitureDao = daoFactory.getVoitureDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("categories", voitureDao.getCategories());
		request.setAttribute("voitures", voitureDao.lister());
		request.setAttribute("etats", voitureDao.getEtats());
		request.setAttribute("modeles", voitureDao.getModeles());
		request.setAttribute("couleurs", voitureDao.getCouleurs());
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutOffreAchat.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recupération du nom de l'utilisateur connecté
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("pseudo"); // Récupération du nom de l'utilisateur
		List<Integer> id_offres = negociationDao.getListeOffre(request.getParameter("modele"),user);//On récupére les id des offres correspondant au modèle recherché qui ne sont pas vendus par user
		if(id_offres.size() != 0) {
			Voiture v = new Voiture();//Voiture qui contient les critères de l'acheteur
			
			//Récupération des champs du formulaire
			String cat = request.getParameter("categorie");
			String nom = request.getParameter("modele");
			String etat = request.getParameter("etat");
			String couleur = request.getParameter("couleur");
			int prix = Integer.parseInt(request.getParameter("prix"));
			
			//Attribution des champs du formulaire dans la voiture v
			v.setCategorie(cat);
			v.setModele(nom);
			v.setCouleur(couleur);
			v.setPrix(prix);
			v.setEtat(etat);
	
			//Initialisation et envoie des négociations
			List<Negociation> n = new ArrayList<Negociation>();
			n = negociationDao.initNegociation(id_offres, v, voitureDao);
			request.setAttribute("negociation", n);
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/negociation.jsp").forward(request, response);
	}

}
