package controller;

import java.io.IOException;
/*import java.security.Principal;*/
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Eleve;

/**
 * Servlet implementation class Dashboard
 */

public class Dashboard extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/* TODO the number of salle and classe should be from the DB in the future */
	
	private String nb_salle = "2";
	private String nb_classe = "3";
	

    private Eleve marc = new Eleve("Bramaud", "Marc");
	
	/* TODO When database is setup, these will be 
	 * (respectively) ArrayList<Classe> and ArrayList<Matrix>*/
	
	private ArrayList<String> list_salle = new ArrayList<String>();
	private ArrayList<String> list_classe = new ArrayList<String>();
	
	/*  TODO they will contain the Classe and Matrix objects from the DB, not the generic ones
	 * defined below */
	
	private String salle1 = "TG-201";
	private String salle2 = "CT-401";
	
	private String classe1 = "ING2-GIC1";
	private String classe2 = "ING2-GIC2";
	private String classe3 = "ING2-GIC3";
	
	

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Principal principal = request.getUserPrincipal();
		String userName = principal.getName();
		request.setAttribute("username", userName);*/
		
		list_salle.add(salle1);
		list_salle.add(salle2);
		
		list_classe.add(classe1);
		list_classe.add(classe2);
		list_classe.add(classe3);
		
		request.setAttribute("testEl", marc);
		request.setAttribute("list_salle", (ArrayList<String>)list_salle);
		request.setAttribute("list_classe", (ArrayList<String>)list_classe);
		request.setAttribute("nb_salle", nb_salle);
		request.setAttribute("nb_classe", nb_classe);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/private/dashboard.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String table = "TOTO";
		table = request.getParameter("salle");

		System.out.println("La salle est:" + table);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/private/dashboard.jsp" ).forward( request, response );
	}

}
