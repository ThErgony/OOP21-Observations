package oop21.tentelli.fonti.observations.core;

/**
 * Model core app, create all class need and pass reference for create, write and load. Create a first load: if is first time start software
 * application create file moment list and type observed list format .txt, used for view to show the list of item user can get, if user need
 * more item can add; if the installation are do after, load all list for user. Updater get all file for update and create file and folder
 * user required.
 * */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import oop21.tentelli.fonti.observations.Updater;
import oop21.tentelli.fonti.observations.Loader;
import oop21.tentelli.fonti.observations.Saved;
import oop21.tentelli.fonti.observations.utility.UpdaterImpl;
import oop21.tentelli.fonti.observations.utility.FirstLoaderImpl;
import oop21.tentelli.fonti.observations.utility.LoaderImpl;
import oop21.tentelli.fonti.observations.utility.SavedImpl;

public class ModelCoreImpl {

	private static final String SEP = File.separator;
	private static final String ROOT = System.getProperty("user.home");
	private static final String NAME_APP = "Observations";
	private static final String SAVE_DIR = "save";
	private static final String STUDENT_DIR = "students";
	private static final String DIR = ROOT + SEP + NAME_APP + SEP + SAVE_DIR + SEP;
	
	private static final String MOMENTS_LIST = "moments.txt";
	private static final String TYPE_OBSERVED_LIST = "observations.txt";
	private static final String EXTENSION = ".txt";
	
	private final Saved save;
	private final Loader loader;
	private final Updater updater;
	
	/**
	 * create new object for save and loader, first start and updater with parameter need for class
	 * */
	public ModelCoreImpl() throws IOException {
		super();
		this.save = new SavedImpl();
		this.loader = new LoaderImpl();
		new FirstLoaderImpl().firstLoad(DIR, STUDENT_DIR, MOMENTS_LIST, TYPE_OBSERVED_LIST, save, loader);;
		this.updater = new UpdaterImpl(DIR, SEP, MOMENTS_LIST, DIR + STUDENT_DIR + SEP, loader);
	}
	
	public void updateObservations(final String time, final String type) throws IOException {
		this.updater.updateObservations(time + "\n" + type, this.save);
	}

	public void addObservationType(final String type) throws IOException {
		if (!this.getArrayTypeList().contains(type)) {
			this.updater.updateObservations(DIR + TYPE_OBSERVED_LIST, type, this.save);
		}
	}
	
	public ArrayList<String> getArrayMomentsList() throws IOException {
		return new ArrayList<>(List.copyOf(loader.fillList(DIR + MOMENTS_LIST)));
	}

	public ArrayList<String> getArrayTypeList() throws IOException {
		return new ArrayList<>(List.copyOf(loader.fillList(DIR + TYPE_OBSERVED_LIST)));
	}
	
	public ArrayList<String> getObservedStudents() {
		return new ArrayList<>(List.copyOf(this.updater.getObservedStudents()));
	}

	public ArrayList<String> getObservedMoments() {
		return new ArrayList<>(List.copyOf(this.updater.getObservedMoments()));
	}

	public ArrayList<String> getObservedDates() {
		return new ArrayList<>(List.copyOf(this.updater.getObservedDates()));
	}
	
	public void chooseStudent(final String student) throws IOException {
		this.updater.chooseStudent(student, this.save);
	}
	
	public void chooseMoment(final String moment) throws IOException {
		this.updater.chooseMoment(moment, this.getArrayMomentsList(), this.save);
	}

	public void chooseDate(final String date) throws IOException {
		this.updater.chooseDate(date + EXTENSION, this.save);
	}

}