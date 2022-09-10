package oop21.tentelli.fonti.observations.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import oop21.tentelli.fonti.observations.Loader;
import oop21.tentelli.fonti.observations.Saved;
import oop21.tentelli.fonti.observations.utility.LoaderImpl;
import oop21.tentelli.fonti.observations.utility.SavedImpl;
import oop21.tentelli.fonti.observations.utility.MomentsList;
import oop21.tentelli.fonti.observations.utility.ObservationsList;

public class ModelCoreImpl {

	private static final String SEP = File.separator;
	private static final String ROOT = System.getProperty("user.home");
	private static final String NAME_APP = "Observations";
	private static final String SAVE_DIR = "save";
	private static final String MOMENTS_LIST = "moments.txt";
	private static final String TYPE_OBSERVED_LIST = "observations.txt";
	private static final String DIR = ROOT + SEP + NAME_APP + SEP + SAVE_DIR;
	
	private final Saved save;
	private final Loader loader;
	private final ArrayList<String> arrayMomentsList = new ArrayList<>();
	private final ArrayList<String> arrayTypeList = new ArrayList<>();
	
	public ModelCoreImpl() throws IOException {
		super();
		this.save = new SavedImpl();
		this.loader = new LoaderImpl();
		firstLoad();
	}

	/* fill list with item at first load, if the first first time, create and fill file and folder required*/
	private void firstLoad() throws IOException {
		if (!new File(DIR).exists()) {
			save.makeDir(DIR);
		}
		if (!new File(DIR + SEP + MOMENTS_LIST).exists() || !new File(DIR + SEP + TYPE_OBSERVED_LIST).exists()) {
			for (final MomentsList item : MomentsList.values()) {
				arrayMomentsList.add(item.getText());
			}
			for (final ObservationsList item : ObservationsList.values()) {
				arrayTypeList.add(item.getText());
			}
			makeFirstTimeList(MOMENTS_LIST, arrayMomentsList);
			makeFirstTimeList(TYPE_OBSERVED_LIST, arrayTypeList);
		} else {
			fillList(MOMENTS_LIST, arrayMomentsList);
			fillList(TYPE_OBSERVED_LIST, arrayTypeList);
		}
	}

	/* import list file to array*/
	private void fillList(final String list, final ArrayList<String> arrayList) throws IOException {
		arrayList.clear();
		BufferedReader reader = this.readFile(list);
		String item = reader.readLine();
		while (item != null) {
			arrayList.add(item);
			item = reader.readLine();			
		}
		reader.close();
	}

	/* create file and write all the file in the array list*/
	private void makeFirstTimeList(final String list, final ArrayList<String> arrayList) throws IOException {
		save.makeFile(DIR + SEP, list);
		final FileWriter fw = new FileWriter(new File(DIR + SEP + list));
		for (final String item : arrayList) {
			updateList(fw, item);
		}
		fw.flush();
		fw.close();
	}

	public void updateObservations(final FileWriter fw, final String time, final String type) throws IOException {
		save.updateObservations(fw, time, type);		
	}
	
	public void updateList(final FileWriter fw, final String item) {
		save.updateList(fw, item);
	}
	
	public BufferedReader readFile(final String name) throws IOException {
		return loader.readFile(new File(DIR + SEP + name));
	}
}
