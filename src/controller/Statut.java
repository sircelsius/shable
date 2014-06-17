package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Statut
 */
public class Statut extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Boolean down =true;
	private String cur_work = "Dommage, revenez plus tard!";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Statut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("down", down);
		request.setAttribute("cur_work", cur_work);
		this.getServletContext().getRequestDispatcher( "/WEB-INF/statut.jsp" ).forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
