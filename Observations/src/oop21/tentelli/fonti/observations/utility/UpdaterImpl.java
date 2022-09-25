package oop21.tentelli.fonti.observations.utility;

import java.io.IOException;
import java.util.ArrayList;

import oop21.tentelli.fonti.observations.Updater;
import oop21.tentelli.fonti.observations.Loader;
import oop21.tentelli.fonti.observations.Saved;

public class UpdaterImpl implements Updater {

	private final String pathMomentList;
	private final String sep;
	
	private final Loader loader;
	
	private String student;
	private String moment;
	private String date;
	private final String studentRoot;
	private String momentRoot;
	private String dateRoot;
	
	public UpdaterImpl(final String pathMomentList, final String sep,
								final String startPath, final Loader loader) {
		super();
		this.pathMomentList = pathMomentList;
		this.sep = sep;
		this.studentRoot = startPath;
		this.loader = loader;
	}

	private ArrayList<String> check(final String path) {
		return this.loader.loadFileFolder(path);
	}

	public void chooseStudent(final String student, final Saved save) throws IOException {
		this.student = this.studentRoot + student + this.sep;
		if (!this.loader.loadFileFolder(this.studentRoot).contains(student)) {
			save.makeDir(this.student);
		} else {
			this.momentRoot = this.student;
		}
	}
	
	public void chooseMoment(final String moment, final ArrayList<String> updateList,
								final Saved save) throws IOException {
		this.moment = this.momentRoot + moment + this.sep;
		if (!this.loader.loadFileFolder(this.momentRoot).contains(moment)) {
			save.makeDir(this.moment);
			if (!updateList.contains(moment)) {
				updateList.add(moment);
				updateList.sort((a,b)-> a.compareTo(b));
				save.writeList(this.pathMomentList, updateList);
			}
		} else {
				this.dateRoot = this.moment;
		}
	}

	public void chooseDate(final String date, final Saved save) throws IOException {
		this.date = this.dateRoot + date;
		if (!this.loader.loadFileFolder(this.moment).contains(date)) {
			save.makeFile(this.date);
		}
	}

	public ArrayList<String> getObservedStudents() {
		return this.observed(this.studentRoot, this.check(this.studentRoot));
	}

	public ArrayList<String> getObservedMoments() {
		return this.observed(this.momentRoot, this.check(this.momentRoot));
	}

	private ArrayList<String> observed(final String observed, ArrayList<String> returnOK) {
		if (observed.isBlank()) {
			return new ArrayList<>();
		}
		return returnOK;
	}

	public ArrayList<String> getObservedDates() {
		return this.observed(this.dateRoot, this.check(this.dateRoot));
	}
	
	public ArrayList<String> getObservedDay() throws IOException {
		return this.observed(this.date, this.loader.fillList(this.date));
	}

	public void updateObservations(final String element, final Saved save) throws IOException {
		this.updateObservations(this.date, element, save);
	}

	public void updateObservations(final String path, final String element,	final Saved save) throws IOException {		
		ArrayList<String> list = this.loader.fillList(path);
		list.add(element);
		list.sort((a,b)-> a.compareTo(b));
		save.writeList(path, list);		
	}
}
