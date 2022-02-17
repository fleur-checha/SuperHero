package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import dao.HeroDaoImpl;
import dao.IHeroDao;
import metier.entities.Declaration;
import metier.entities.Incident;
import metier.entities.SuperHero;

@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
	
	 IHeroDao metier;
     List<String> incidents = new ArrayList<>();
	 @Override
	public void init() throws ServletException {
		metier = new HeroDaoImpl();
		incidents.add(Incident.Incendie.name());
		incidents.add(Incident.AccidentAerien.name());
		incidents.add(Incident.AccidentFluviale.name());
		incidents.add(Incident.AccidentRoutier.name());
		incidents.add(Incident.Braquage.name());
		incidents.add(Incident.Eboulement.name());
		incidents.add(Incident.EvasionPrisonnier.name());
		incidents.add(Incident.FuiteDeGaz.name());
		incidents.add(Incident.Manifestation.name());
		incidents.add(Incident.InvasionDeSerpent.name());
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			             HttpServletResponse response) 
			            throws ServletException, IOException {
		String path=request.getServletPath();
		if (path.equals("/index.do"))
		{
			request.getRequestDispatcher("index.jsp").forward(request,response);
		}
		else if (path.equals("/saisieSuperHero.do")  )
		{
		
			request.setAttribute("incidents", this.incidents);
			request.getRequestDispatcher("saisieSuperHero.jsp").forward(request,response);
		}
		else if (path.equals("/saveSuperHero.do")  && request.getMethod().equals("POST"))
		{
		    String nom=request.getParameter("nom");
		    double latitude = Double.parseDouble(request.getParameter("latitude"));
		   	double longitude = Double.parseDouble(request.getParameter("longitude"));
		   	String phone = request.getParameter("phone");
		   	String[] incidents =  request.getParameterValues("incidents");
		   				SuperHero sh = metier.saveSuperHero(new SuperHero(nom, Arrays.asList(incidents), latitude, longitude, phone));
			request.setAttribute("superHero", sh);
			request.getRequestDispatcher("confirmation.jsp").forward(request,response);
		}
		
		else if (path.equals("/saisieDeclaration.do"))
		{
			request.setAttribute("incidents", this.incidents);
			request.getRequestDispatcher("saisieDeclaration.jsp").forward(request,response);
		}
		else if (path.equals("/saveDeclaration.do")  && request.getMethod().equals("POST"))
		{
		    String ville=request.getParameter("ville");
		    double latitude = Double.parseDouble(request.getParameter("latitude"));
		   	double longitude = Double.parseDouble(request.getParameter("longitude"));
		   	String typeIncident =  request.getParameter("typeIncident").trim();
			List<SuperHero> superHeroResult= metier.saveDeclaration(new Declaration(ville, latitude, longitude, typeIncident));
			request.setAttribute("latitude", latitude);
			request.setAttribute("longitude", longitude);
			request.setAttribute("superHeros", superHeroResult);
			request.getRequestDispatcher("superHerosResult.jsp").forward(request,response);
		}

		else
		{
			response.sendError(Response.SC_NOT_FOUND);		
		}	
	}
	
	@Override
	protected void doPost(HttpServletRequest request, 
						  HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
}