package oop21.tentelli.fonti.observations.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

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
	private static final String MOMENTS_LIST = "moments.txt";
	private static final String TYPE_OBSERVED_LIST = "observations.txt";
	private static final String DIR = ROOT + SEP + NAME_APP + SEP + SAVE_DIR;
	
	private final Saved save;
	private final Loader loader;
	private final FirstLoader firstStart;
	private final CheckFileFolder finder;
	
	private final ArrayList<String> arrayMomentsList;
	private final ArrayList<String> arrayTypeList;
	private final ArrayList<String> observedStudents;
	private final ArrayList<String> observedMoments =  new  ArrayList<>();
	private final ArrayList<String> observedDates = new ArrayList<>();

	private String selectedStudent = Optional.empty().toString();
	private String selectedMoment = Optional.empty().toString();
	
	public ModelCoreImpl() throws IOException {
		super();
		this.save = new SavedImpl();
		this.loader = new LoaderImpl();
		this.firstStart = new FirstLoaderImpl(DIR, SEP, MOMENTS_LIST, TYPE_OBSERVED_LIST, save, loader);
		this.finder = new CheckFileFolderImpl(DIR + SEP + "students");
		this.arrayMomentsList = firstStart.getArrayMomentsList();
		this.arrayTypeList = firstStart.getArrayTypeList();
		this.observedStudents = finder.check(loader);
	}

	public void updateList(final FileWriter fw, final String item) {
		save.updateList(fw, item);
	}
	
	public BufferedReader readFile(final String name) throws IOException {
		return loader.readFile(new File(DIR + SEP + name));
	}
	
	public void updateObservations(final FileWriter fw, final String time, final String type) throws IOException {
		save.updateObservations(fw, time, type);		
	}
	
	public ArrayList<String> getArrayMomentsList() {
		return this.arrayMomentsList;
	}

	public ArrayList<String> getArrayTypeList() {
		return this.arrayTypeList;
	}
	
	public ArrayList<String> getObservedStudents() {
		return observedStudents;
	}

	public ArrayList<String> getObservedMoments() {
		return observedMoments;
	}

	public ArrayList<String> getObservedDates() {
		return observedDates;
	}
	
	public void addSelectStudent(final String student) {
		this.selectedStudent = student;
		if (!new File(DIR + SEP + "students" + SEP + student).exists()) {
			save.makeDir(DIR + SEP + "students" + SEP + student);
			this.observedStudents.add(student);
		}
	}
}