package mtgchronik.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtgchronik.entities.Resource;
import mtgchronik.entities.TeamInstance;
import mtgchronik.services.TeamService;

@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {

	@EJB
	private TeamService teamService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.valueOf(request.getPathInfo().substring(1));
		//Image image = service.find(id);
		TeamInstance teamInstance = teamService.getTeamInstanceByID(Long.valueOf(id));
		if (teamInstance.getTeamPicture()!=null){
			Resource image = teamInstance.getTeamPicture();
			response.setContentType(getServletContext().getMimeType(image.getFileName()));
			response.setContentLength(image.getContent().length);
			response.getOutputStream().write(image.getContent());
		}
	}

}
