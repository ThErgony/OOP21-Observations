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
		this.firstLoad(save, loader);
	}

	/* fill list with item at first load, if the first first time, create and fill file and folder required*/
	private void firstLoad(final Saved save, final Loader loader) throws IOException {
		if (!new File(this.dir + "students").exists()) {
			save.makeDir(this.dir + "students");
		}
		if (!new File(this.dir + this.moments).exists() && !new File(this.dir + this.types).exists()) {
			for (final MomentsList item : MomentsList.values()) {
				this.arrayMomentsList.add(item.getText());
			}
			for (final ObservationsList item : ObservationsList.values()) {
				this.arrayTypeList.add(item.getText());
			}
			save.writeList(this.dir + this.moments, this.arrayMomentsList);
			save.writeList(this.dir + this.types, this.arrayTypeList);
		} else {
			loader.fillList(this.dir + this.moments, this.arrayMomentsList);
			loader.fillList(this.dir + this.types, this.arrayTypeList);
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
