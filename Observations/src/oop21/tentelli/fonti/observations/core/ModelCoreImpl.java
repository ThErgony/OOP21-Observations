package oop21.tentelli.fonti.observations.core;

/**
 * Model core app, create all class need and pass reference for create, write and load. Create a first load:
 * if is first time start software
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
import oop21.tentelli.fonti.observations.utility.Pair;
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
	
	private String student;
	private String moment;
	private String date;
	
	/**
	 * create new object for save and loader, first start and updater with parameter need for class
	 * */
	public ModelCoreImpl() throws IOException {
		super();
		this.save = new SavedImpl();
		this.loader = new LoaderImpl();
		new FirstLoaderImpl().firstLoad(DIR, STUDENT_DIR, MOMENTS_LIST, TYPE_OBSERVED_LIST, save, loader);;
		this.updater = new UpdaterImpl(DIR + MOMENTS_LIST, SEP, DIR + STUDENT_DIR + SEP, loader);
	}
	
	public ArrayList<String> getArrayMomentsList() throws IOException {
		return new ArrayList<>(List.copyOf(loader.fillList(DIR + MOMENTS_LIST)));
	}

	public ArrayList<String> getArrayTypeList() throws IOException {
		return new ArrayList<>(List.copyOf(loader.fillList(DIR + TYPE_OBSERVED_LIST)));
	}
	
	public void chooseStudent(final String student) throws IOException {
		this.student = student;
		this.updater.chooseStudent(student, this.save);
	}
	
	public void chooseMoment(final String moment) throws IOException {
		this.moment = moment;
		this.updater.chooseMoment(moment, this.getArrayMomentsList(), this.save);
	}

	public void chooseDate(final String date) throws IOException {
		this.date = date;
		this.updater.chooseDate(date + EXTENSION, this.save);
	}
	
	public ArrayList<String> getDataDayChoose() throws IOException {
		return new ArrayList<>(List.copyOf(this.updater.getObservedDay()));
	}
	
	public ArrayList<Pair<String, Integer>> getDataMomentChoose() throws IOException {
		return this.counter(this.momentObservations());
	}
	
	public ArrayList<Pair<String, Integer>> getDataStudentChoose() throws IOException {
		return this.counter(this.studentObservations());
	}
	
	public ArrayList<Pair<String, Integer>> getCounterDayChoose() throws IOException {
		ArrayList<Pair<String, Integer>> list = this.counter(this.getDataDayChoose());
		return list;
	}
	
	public ArrayList<Pair<String, Integer>> getCounterDates() throws IOException {
		String dat = this.date;
		ArrayList<Pair<String, Integer>> list = new ArrayList<>();
		for (final String element : this.getObservedDates()) {
			this.chooseDate(element);
			ArrayList<Pair<String, Integer>> tempList = this.counter(this.getDataDayChoose());
			int sum = 0;
			for (final Pair<String, Integer> pair : tempList) {
				sum += pair.getY();
			}
			list.add(new Pair<>(element, sum));
		}
		this.refreshDate(dat);
		return list;
	}

	public ArrayList<Pair<String, Integer>> getCounterMoments() throws IOException {
		String mom = this.moment;
		String dat = this.date;
		ArrayList<Pair<String, Integer>> list = new ArrayList<>();
		for (final String element : this.getObservedMoments()) {
			this.chooseMoment(element);
			ArrayList<Pair<String, Integer>> tempList = this.getCounterDates();
			int sum = 0;
			for (final Pair<String, Integer> pair : tempList) {
				sum += pair.getY();
			}
			list.add(new Pair<>(element, sum));
		}
		this.refreshMoment(mom, dat);
		return list;
	}
	
	public ArrayList<Pair<String, Integer>> getCounterStudents() throws IOException {
		String stud = this.student;
		String mom = this.moment;
		String dat = this.date;
		ArrayList<Pair<String, Integer>> list = new ArrayList<>();
		for (final String element : this.getObservedStudents()) {
			this.chooseStudent(element);
			ArrayList<Pair<String, Integer>> tempList = this.getCounterMoments();
			int sum = 0;
			for (final Pair<String, Integer> pair : tempList) {
				sum += pair.getY();
			}
			list.add(new Pair<>(element, sum));
		}
		this.refreshStudent(stud, mom, dat);
		return list;
	}
	
	public void updateObservations(final String time, final String type) throws IOException {
		this.updater.updateObservations(type + " - " + time, this.save);
	}

	public void addObservationType(final String type) throws IOException {
		if (!this.getArrayTypeList().contains(type)) {
			this.updater.updateObservations(DIR + TYPE_OBSERVED_LIST, type, this.save);
		}
	}
	
	private ArrayList<Pair<String, String>> splitDatas(final ArrayList<String> arrayList) throws IOException{
		ArrayList<Pair<String, String>> list = new ArrayList<>();
		for (final String string : arrayList) {
			String[] split = string.split(" - ");
			list.add(new Pair<>(split[0], split[1]));
		}
		return list;
	}

	private ArrayList<Pair<String, Integer>> counter(final ArrayList<String> arrayList) throws IOException {
		ArrayList<Pair<String, Integer>> list = new ArrayList<>();
		for (final Pair<String, String> counter : this.splitDatas(arrayList)) {
			ArrayList<Pair<String, Integer>> tempList = new ArrayList<>();
			tempList.addAll(list);
			String element = counter.getX();
			int inc = 0;
			if (!list.isEmpty()) {	
				for (final Pair<String, Integer> item : tempList) {
					if (item.getX().equals(element)) {
						inc = item.getY();
						list.remove(new Pair<>(item.getX(), item.getY()));
					}
				}
			}
			list.add(new Pair<>(element, ++inc));
		}
		return list;
	}

	private ArrayList<String> momentObservations() throws IOException {
		String mom = this.moment;
		String dat = this.date;
		ArrayList<String> list = new ArrayList<>();
		for (final String e : this.getObservedDates()) {
			this.chooseDate(e);
			list.addAll(this.getDataDayChoose());
		}
		list.sort((a,b)->a.compareTo(b));
		this.refreshMoment(mom, dat);
		return list;
	}
	
	private ArrayList<String> studentObservations() throws IOException {
		String stud = this.student;
		String mom = this.moment;
		String dat = this.date;
		ArrayList<String> list = new ArrayList<>();
		for (final String e : this.getObservedMoments()) {
			this.chooseMoment(e);
			list.addAll(this.momentObservations());
		}
		list.sort((a,b)->a.compareTo(b));
		this.refreshStudent(stud, mom, dat);
		return list;
	}
	
	private ArrayList<String> getObservedStudents() {
		return new ArrayList<>(List.copyOf(this.updater.getObservedStudents()));
	}

	private ArrayList<String> getObservedMoments() {
		return new ArrayList<>(List.copyOf(this.updater.getObservedMoments()));
	}

	private ArrayList<String> getObservedDates() {
		ArrayList<String> list = new ArrayList<>();
		for (String string : List.copyOf(this.updater.getObservedDates())) {
			string = string.substring(0, string.length()-4);
			list.add(string);
		}
		return list;
	}
	
	private void refreshStudent(final String stud, final String mom, final String dat) throws IOException {
		this.student = stud;
		this.chooseStudent(this.student);
		refreshMoment(mom, dat);
	}

	private void refreshMoment(final String mom, final String dat) throws IOException {
		this.moment = mom;
		this.chooseMoment(this.moment);
		refreshDate(dat);
	}

	private void refreshDate(final String dat) throws IOException {
		this.date = dat;
		this.chooseDate(this.date);
	}
	
}