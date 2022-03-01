package com.octest.forms;

import java.io.IOException;
import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.octest.beans.Voiture;
import com.octest.dao.DaoFactory;
import com.octest.dao.VoitureDao;


/**
 * Servlet implementation class AjoutOffreForm
 */

@WebServlet("/AjoutOffreForm")
@MultipartConfig( fileSizeThreshold = 1024 * 1024, 
maxFileSize = 1024 * 1024 * 5,
maxRequestSize = 1024 * 1024 * 5 * 5 )

public class AjoutOffreForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VoitureDao voitureDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutOffreForm() {
        super();
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.voitureDao = daoFactory.getVoitureDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutOffreForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		Part visuel = request.getPart("visuel");
		
		String dossierUpload;
		dossierUpload = getServletContext().getRealPath("assets/photos/offres");
		
        File uploadDir = new File( dossierUpload );
        if ( ! uploadDir.exists() ) uploadDir.mkdir();
		
		Voiture voiture = new Voiture();
		
		String nomFichier = getFileName(visuel);
		voiture.setVisuel(nomFichier);
        String cheminComplet = dossierUpload + File.separator + nomFichier;
        visuel.write(cheminComplet);
        System.out.println(cheminComplet);
        
		voiture.setModele(request.getParameter("modele"));
		voiture.setCategorie(request.getParameter("categorie"));
		voiture.setEtat(request.getParameter("etat"));
		int km1 = Integer.parseInt(request.getParameter("km"));
        voiture.setKm(km1);
        int ct = Integer.parseInt(request.getParameter("ct"));
        Boolean ct1 = (ct == 1);
        voiture.setControle_technique(ct1);
		voiture.setCouleur(request.getParameter("couleur"));
		int annee1 = Integer.parseInt(request.getParameter("annee"));
        voiture.setAnnee(annee1);
		voiture.setDescription(request.getParameter("description"));
		int prix1 = Integer.parseInt(request.getParameter("prix"));
        voiture.setPrix(prix1);
		
		voitureDao.afficherConsole(voiture);
		String nomUser = (String) session.getAttribute("pseudo");
		voitureDao.ajouter(voiture,nomUser);
		System.out.println(voitureDao.getLastId());
		request.setAttribute("Ajouts", "oui");
		request.setAttribute("id", voitureDao.getLastId());
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutOffreForm.jsp").forward(request, response);
	}
	
	private String getFileName( Part part ) 
	{
        for ( String content : part.getHeader( "content-disposition" ).split( ";" ) ) 
        {
            if ( content.trim().startsWith( "filename" ) )
                return content.substring( content.indexOf( "=" ) + 2, content.length() - 1 );
        }
        return "Default.file";
    }

}
