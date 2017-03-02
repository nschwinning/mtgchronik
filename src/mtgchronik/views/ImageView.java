package mtgchronik.views;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import mtgchronik.entities.TeamInstance;
import mtgchronik.services.TeamService;

@Named
@ApplicationScoped
public class ImageView {
	
	 @Inject
	 private TeamService teamService;
	
	 public StreamedContent getTeamPicture() throws IOException{
		 System.out.println("Wuhla");
		 FacesContext context = FacesContext.getCurrentInstance();
		 
		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
				return new DefaultStreamedContent();
		}else{
			String teamInstanceId = context.getExternalContext().getRequestParameterMap().get("pictureTeamInstanceId");
			TeamInstance teamInstance = teamService.getTeamInstanceByID(Long.valueOf(teamInstanceId));
			if (teamInstance.getTeamPicture()==null){
				return new DefaultStreamedContent();
			}
			return new DefaultStreamedContent(new ByteArrayInputStream(teamInstance.getTeamPicture().getContent()));
		}
	 }

}
