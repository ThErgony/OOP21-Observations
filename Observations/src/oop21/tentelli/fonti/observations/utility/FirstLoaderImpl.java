package oop21.tentelli.fonti.observations.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import oop21.tentelli.fonti.observations.FirstLoader;
import oop21.tentelli.fonti.observations.Loader;
import oop21.tentelli.fonti.observations.Saved;

public class FirstLoaderImpl implements FirstLoader {
	
	private final ArrayList<String> arrayMomentsList = new ArrayList<>();
	private final ArrayList<String> arrayTypeList = new ArrayList<>();

	public FirstLoaderImpl() {
		super();
	}

	/* fill list with item at first load, if the first first time, create and fill file and folder required*/
	public void firstLoad(final String dir, final String students, final String moments, final String types,
							final Saved save, final Loader loader) throws IOException {
		if (!new File(dir + students).exists()) {
			save.makeDir(dir + students);
		}
		if (!new File(dir + moments).exists() && !new File(dir + types).exists()) {
			for (final MomentsList item : MomentsList.values()) {
				this.arrayMomentsList.add(item.getText());
			}
			for (final ObservationsList item : ObservationsList.values()) {
				this.arrayTypeList.add(item.getText());
			}
			save.writeList(dir + moments, this.arrayMomentsList);
			save.writeList(dir + types, this.arrayTypeList);
		} else {
			loader.fillList(dir + moments);
			loader.fillList(dir + types);
		}
	}
	
}
