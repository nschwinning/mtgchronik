package mtgchronik.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import mtgchronik.entities.Player;
import mtgchronik.services.PlayerService;

@ManagedBean(name="playerConverter")
@SessionScoped
public class PlayerConverter implements Converter {
	
	@Inject
	private PlayerService playerService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Long id = Long.valueOf(value);
		Player player = playerService.getPlayerByID(id);
		return player;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Player player = (Player) value;
		String idAsString = String.valueOf(player.getId());
		return idAsString;
	}

	
	
}
