package mtgchronik.comparators;

import java.io.Serializable;
import java.util.Comparator;

import mtgchronik.entities.PlayerInstance;

public class PlayerInstanceComparator implements Comparator<PlayerInstance>, Serializable{

	@Override
	public int compare(PlayerInstance o1, PlayerInstance o2) {
		if (o1.getPosition()<o2.getPosition())
			return -1;
		if (o1.getPosition()>o2.getPosition())
			return 1;
		return 0;
	}

}
