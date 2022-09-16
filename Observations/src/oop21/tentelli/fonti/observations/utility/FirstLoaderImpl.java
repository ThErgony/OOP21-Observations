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
	private final String dir;
	private final String moments;
	private final String types;

	public FirstLoaderImpl(final String dir,final String moments, final String types,
			final Saved save, final Loader loader) throws IOException {
		super();
		this.dir = dir;
		this.moments = moments;
		this.types = types;
		firstLoad(save, loader);
	}

	/* fill list with item at first load, if the first first time, create and fill file and folder required*/
	private void firstLoad(final Saved save, final Loader loader) throws IOException {
		if (!new File(dir + "students").exists()) {
			save.makeDir(dir + "students");
		}
		if (!new File(dir + moments).exists() && !new File(dir + types).exists()) {
			for (final MomentsList item : MomentsList.values()) {
				arrayMomentsList.add(item.getText());
			}
			for (final ObservationsList item : ObservationsList.values()) {
				arrayTypeList.add(item.getText());
			}
			save.writeList(dir+moments, arrayMomentsList);
			save.writeList(dir+types, arrayTypeList);
		} else {
			loader.fillList(dir+moments, arrayMomentsList);
			loader.fillList(dir+types, arrayTypeList);
		}
	}

	@Override
	public ArrayList<String> getArrayMomentsList() {
		return this.arrayMomentsList;
	}

	@Override
	public ArrayList<String> getArrayTypeList() {
		return this.arrayTypeList;
	}
	
}
