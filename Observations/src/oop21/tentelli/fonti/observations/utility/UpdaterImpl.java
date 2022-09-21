package oop21.tentelli.fonti.observations.utility;

import java.io.IOException;
import java.util.ArrayList;

import oop21.tentelli.fonti.observations.Updater;
import oop21.tentelli.fonti.observations.Loader;
import oop21.tentelli.fonti.observations.Saved;

public class UpdaterImpl implements Updater {

	private final String dir;
	private final String sep;
	private final String momentList;
	
	private final Loader loader;
	
	private String selected;
	private String student;
	private String moment;
	private String date;
	private final String studentRoot;
	private String momentRoot;
	private String dateRoot;
	
	public UpdaterImpl(final String dir, final String sep, final String momentList,
								final String selected, final Loader loader) {
		super();
		this.dir = dir;
		this.sep = sep;
		this.momentList = momentList;
		this.selected = selected;
		this.studentRoot = selected;
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
			this.selected = this.student;
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
				save.writeList(this.dir + this.momentList, updateList);
			}
		} else {
				this.selected = this.moment;
				this.dateRoot = this.moment;
		}
	}

	public void chooseDate(final String date, final Saved save) throws IOException {
		this.date = this.dateRoot + date;
		if (!this.loader.loadFileFolder(this.moment).contains(date)) {
			save.makeFile(this.date);
		}
		this.selected = this.date;
		
	}

	public ArrayList<String> getObservedStudents() {
		return this.check(this.studentRoot);
	}

	public ArrayList<String> getObservedMoments() {
		return this.check(this.momentRoot);
	}

	public ArrayList<String> getObservedDates() {
		return this.check(this.dateRoot);
	}

//	public void backFolder(final String back, final Loader loader) {
//		if (loader.loadFileFolder(this.studentRoot).contains(back)) {
//			this.selected = this.dir + back;
//		} else if (loader.loadFileFolder(this.momentRoot).contains(back)) {
//			this.selected = new File(this.moment).getParent();
//		} else if (this.selected.equals(back)) {
//			this.selected = this.dir;
//		}
//	}

	public void updateObservations(final String element, final Saved save) throws IOException {
		this.updateObservations(this.selected, element, save);
	}

	public void updateObservations(final String path, final String element,	final Saved save) throws IOException {		
		ArrayList<String> list = this.loader.fillList(path);
		list.add(element);
		list.sort((a,b)-> a.compareTo(b));
		save.writeList(path, list);		
	}
}