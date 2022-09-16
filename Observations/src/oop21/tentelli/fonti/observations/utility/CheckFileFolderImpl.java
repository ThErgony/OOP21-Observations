package oop21.tentelli.fonti.observations.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import oop21.tentelli.fonti.observations.CheckFileFolder;
import oop21.tentelli.fonti.observations.Loader;
import oop21.tentelli.fonti.observations.Saved;

public class CheckFileFolderImpl implements CheckFileFolder {

	private ArrayList<String> observedStudents;
	private ArrayList<String> observedMoments =  new  ArrayList<>();
	private ArrayList<String> observedDates = new ArrayList<>();
	
	private final String dir;
	private final String sep;
	private final String momentList;
	private final String typeList;
	
	private String selected;
	private String student;
	private String moment;
	private String date;
	private final String studentRoot;
	private String momentRoot;
	
	public CheckFileFolderImpl(final String dir, final String sep, final String momentList, final String typeList,
			final String selected, final Loader loader) {
		super();
		this.dir = dir;
		this.sep = sep;
		this.momentList = momentList;
		this.typeList = typeList;
		this.selected = selected;
		this.studentRoot = selected;
		this.observedStudents = this.check(loader, this.selected);
	}

	public ArrayList<String> check(final Loader loader, final String dir) {
		return loader.loadFileFolder(dir);
	}

	public void chooseStudent(final String student, final Saved save, final Loader loader) throws IOException {
		this.student = this.studentRoot + student + this.sep;
		if (!loader.loadFileFolder(this.studentRoot).contains(student)) {
			save.makeDir(this.student);
			this.refreshStudents(loader);
		} else {
			this.selected = this.student;
			this.momentRoot = this.student;
			this.refreshMoments(loader);
		}
	}
	
	public void chooseMoment(final String moment, final ArrayList<String> updateList, final Saved save, final Loader loader) throws IOException {
		this.moment = this.momentRoot + moment + this.sep;
		System.out.println("conta " + updateList);
		if (!loader.loadFileFolder(this.momentRoot).contains(moment)) {
			save.makeDir(this.moment);
			refreshMoments(loader);
			if (!updateList.contains(moment)) {
				updateList.add(moment);
				System.out.println("after add " + updateList);
				save.writeList(this.dir + this.momentList, updateList);
			}
		} else {
				this.selected = this.moment;
				refreshDates(loader);
		}
	}

	public void chooseDate(final String date, final Saved save, final Loader loader) throws IOException {
		this.date = this.selected + date + ".txt";
		if (!loader.loadFileFolder(this.moment).contains(date)) {
			save.makeFile(this.date);
			this.refreshDates(loader);
		} else {
			this.selected = this.date;
		}
	}
	
	private void refreshStudents(final Loader loader) {
		this.observedStudents.clear();
		this.observedStudents = this.check(loader, this.studentRoot);
	}

	private void refreshMoments(final Loader loader) {
		this.observedMoments.clear();
		this.observedMoments = this.check(loader, this.momentRoot);
	}
	
	private void refreshDates(final Loader loader) {
		this.observedDates.clear();
		this.observedDates = this.check(loader, this.moment);
	}
	
	public ArrayList<String> getObservedStudents() {
		return this.observedStudents;
	}

	public ArrayList<String> getObservedMoments() {
		return observedMoments;
	}

	public ArrayList<String> getObservedDates() {
		return observedDates;
	}
	
	public void backFolder(final String back, final Loader loader) {
		if (loader.loadFileFolder(this.studentRoot).contains(back)) {
			this.selected = this.dir + back;
			refreshMoments(loader);
		} else if (loader.loadFileFolder(this.momentRoot).contains(back)) {
			this.selected = new File(this.moment).getParent();
			refreshDates(loader);
		} else if (this.selected.equals(back)) {
			this.selected = this.dir;
			refreshStudents(loader);
		}
	}
}
