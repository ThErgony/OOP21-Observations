package oop21.tentelli.fonti.observations.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import oop21.tentelli.fonti.observations.Loader;
import oop21.tentelli.fonti.observations.Saved;
import oop21.tentelli.fonti.observations.utility.LoaderImpl;
import oop21.tentelli.fonti.observations.utility.SavedImpl;

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
	private final ArrayList<String> MomentsList = new ArrayList();
	private final ArrayList<String> TypeList = new ArrayList();
	
	public ModelCoreImpl() throws IOException {
		super();
		this.save = new SavedImpl();
		this.loader = new LoaderImpl();
		if (!new File(DIR).exists()) {
			save.makeDir(DIR);
			firstLoad();
		}
	}

	private void firstLoad() throws IOException {
		if (!new File(DIR + SEP + MOMENTS_LIST).exists()) {
			save.makeFile(DIR + SEP, MOMENTS_LIST);
		} else if (!new File(DIR + SEP + TYPE_OBSERVED_LIST).exists()) {
			save.makeFile(DIR + SEP, TYPE_OBSERVED_LIST);
		}
	}

	public void updateObservations(final FileWriter fw, final String time, final String type) throws IOException {
		save.updateObservations(fw, time, type);
		
	}
	
	
}
