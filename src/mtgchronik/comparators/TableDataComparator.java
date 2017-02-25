package mtgchronik.comparators;

import java.io.Serializable;
import java.util.Comparator;

import mtgchronik.entities.TableData;

public class TableDataComparator implements Comparator<TableData>, Serializable{

	@Override
	public int compare(TableData o1, TableData o2) {
		if (o1.getPosition()<o2.getPosition())
			return -1;
		if (o1.getPosition()>o2.getPosition())
			return 1;
		return 0;
	}

}
