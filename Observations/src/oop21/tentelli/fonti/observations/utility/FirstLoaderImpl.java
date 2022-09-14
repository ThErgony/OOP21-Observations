package oop21.tentelli.fonti.observations.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import oop21.tentelli.fonti.observations.FirstLoader;
import oop21.tentelli.fonti.observations.Loader;
import oop21.tentelli.fonti.observations.Saved;

public class FirstLoaderImpl implements FirstLoader {
	
	private final ArrayList<String> arrayMomentsList;
	private final ArrayList<String> arrayTypeList;
	private final String dir;
	private final String sep;
	private final String moments;
	private final String types;
//	private final Saved save;
//	private final Loader loader;

	public FirstLoaderImpl(final String dir, final String sep,
			final String moments, final String types, final Saved save, final Loader loader) throws IOException {
		super();
		this.arrayMomentsList = new ArrayList<>();
		this.arrayTypeList = new ArrayList<>();
		this.dir = dir;
		this.sep = sep;
		this.moments = moments;
		this.types = types;
//		this.save = save;
//		this.loader = loader;
		firstLoad(save, loader);
	}

	/* fill list with item at first load, if the first first time, create and fill file and folder required*/
	private void firstLoad(final Saved save, final Loader loader) throws IOException {
		if (!new File(dir + sep + "students").exists()) {
			save.makeDir(dir + sep + "students");
		}
		if (!new File(dir + sep + moments).exists() && !new File(dir + sep + types).exists()) {
			for (final MomentsList item : MomentsList.values()) {
				arrayMomentsList.add(item.getText());
			}
			for (final ObservationsList item : ObservationsList.values()) {
				arrayTypeList.add(item.getText());
			}
			makeFirstTimeList(save, moments, arrayMomentsList);
			makeFirstTimeList(save, types, arrayTypeList);
		} else {
			fillList(loader, moments, arrayMomentsList);
			fillList(loader, types, arrayTypeList);
		}
	}

	/* import list file to array*/
	private void fillList(final Loader loader, final String list, final ArrayList<String> arrayList) throws IOException {
		arrayList.clear();
		BufferedReader reader = this.readFile(loader, list);
		String item = reader.readLine();
		while (item != null) {
			arrayList.add(item);
			item = reader.readLine();			
		}
		reader.close();
	}

	/* create file and write all the file in the array list*/
	private void makeFirstTimeList(final Saved save, final String list, final ArrayList<String> arrayList) throws IOException {
		save.makeFile(dir + sep, list);
		final FileWriter fw = new FileWriter(new File(dir + sep + list));
		for (final String item : arrayList) {
			save.updateList(fw, item);
		}
		fw.flush();
		fw.close();
	}
	
	private BufferedReader readFile(final Loader loader, final String name) throws IOException {
		return loader.readFile(new File(dir + sep + name));
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
