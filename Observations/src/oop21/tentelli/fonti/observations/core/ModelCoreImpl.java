package oop21.tentelli.fonti.observations.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import oop21.tentelli.fonti.observations.CheckFileFolder;
import oop21.tentelli.fonti.observations.FirstLoader;
import oop21.tentelli.fonti.observations.Loader;
import oop21.tentelli.fonti.observations.Saved;
import oop21.tentelli.fonti.observations.utility.CheckFileFolderImpl;
import oop21.tentelli.fonti.observations.utility.FirstLoaderImpl;
import oop21.tentelli.fonti.observations.utility.LoaderImpl;
import oop21.tentelli.fonti.observations.utility.SavedImpl;

public class ModelCoreImpl {

	private static final String SEP = File.separator;
	private static final String ROOT = System.getProperty("user.home");
	private static final String NAME_APP = "Observations";
	private static final String SAVE_DIR = "save";
	private static final String STUDENT_DIR = "student";
	private static final String DIR = ROOT + SEP + NAME_APP + SEP + SAVE_DIR + SEP;
	
	private static final String MOMENTS_LIST = "moments.txt";
	private static final String TYPE_OBSERVED_LIST = "observations.txt";
	private static final String EXTENSION = ".txt";
	
	private final Saved save;
	private final Loader loader;
	private final FirstLoader firstStart;
	private final CheckFileFolder finder;
	
	public ModelCoreImpl() throws IOException {
		super();
		this.save = new SavedImpl();
		this.loader = new LoaderImpl();
		this.firstStart = new FirstLoaderImpl(DIR, MOMENTS_LIST, TYPE_OBSERVED_LIST, save, loader);
		this.finder = new CheckFileFolderImpl(DIR, SEP, MOMENTS_LIST, TYPE_OBSERVED_LIST, DIR + STUDENT_DIR + SEP, loader);
	}
	
	public BufferedReader readFile(final String name) throws IOException {
		return this.loader.readFile(name);
	}
	
	public void updateObservations(final String time, final String type) throws IOException {
		this.finder.updateObservations(time + "\n" + type, save, loader);
	}
	
	public void addObservationType(final String type) throws IOException {
		if (!this.getArrayTypeList().contains(type)) {
			this.finder.updateObservations(DIR + TYPE_OBSERVED_LIST, type, save, loader);
		}
	}
	
	public ArrayList<String> getArrayMomentsList() throws IOException {
		return new ArrayList<>(List.copyOf(loader.fillList(DIR + MOMENTS_LIST, firstStart.getArrayMomentsList())));
	}

	public ArrayList<String> getArrayTypeList() throws IOException {
		return new ArrayList<>(List.copyOf(loader.fillList(DIR + TYPE_OBSERVED_LIST, firstStart.getArrayTypeList())));
	}
	
	public ArrayList<String> getObservedStudents() {
		return new ArrayList<>(List.copyOf(finder.getObservedStudents()));
	}

	public ArrayList<String> getObservedMoments() {
		return new ArrayList<>(List.copyOf(finder.getObservedMoments()));
	}

	public ArrayList<String> getObservedDates() {
		return new ArrayList<>(List.copyOf(finder.getObservedDates()));
	}
	
	public void chooseStudent(final String student) throws IOException {
		finder.chooseStudent(student, save, loader);
	}
	
	public void chooseMoment(final String moment) throws IOException {
		finder.chooseMoment(moment, this.getArrayMomentsList(), save, loader);
	}

	public void chooseDate(final String date) throws IOException {
		finder.chooseDate(date + EXTENSION, save, loader);
	}

}