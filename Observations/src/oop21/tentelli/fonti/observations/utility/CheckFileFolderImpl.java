package oop21.tentelli.fonti.observations.utility;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
	private String dateRoot;
	
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
			this.observedStudents = this.refresh(loader, this.observedStudents, this.studentRoot);
		} else {
			this.selected = this.student;
			this.momentRoot = this.student;
			this.observedMoments = this.refresh(loader, this.observedMoments, this.momentRoot);
		}
	}
	
	public void chooseMoment(final String moment, final ArrayList<String> updateList, final Saved save, final Loader loader) throws IOException {
		this.moment = this.momentRoot + moment + this.sep;
		if (!loader.loadFileFolder(this.momentRoot).contains(moment)) {
			save.makeDir(this.moment);
			this.observedMoments = this.refresh(loader, this.observedMoments, this.momentRoot);
			if (!updateList.contains(moment)) {
				updateList.add(moment);
				save.writeList(this.dir + this.momentList, updateList);
			}
		} else {
				this.selected = this.moment;
				this.dateRoot = this.moment;
				this.observedDates = this.refresh(loader, observedDates, this.selected);
		}
	}

	public void chooseDate(final String date, final Saved save, final Loader loader) throws IOException {
		this.date = this.dateRoot + date;
		if (!loader.loadFileFolder(this.moment).contains(date)) {
			save.makeFile(this.date);
			this.observedDates = this.refresh(loader, observedDates, this.selected);
		}
		this.selected = this.date;
		
	}
	
	private ArrayList<String> refresh(final Loader loader, ArrayList<String> updateList, final String choose) {
		updateList.clear();
		return updateList = this.check(loader, choose);
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
			this.observedMoments = this.refresh(loader, observedMoments, momentRoot);
		} else if (loader.loadFileFolder(this.momentRoot).contains(back)) {
			this.selected = new File(this.moment).getParent();
			this.observedDates = this.refresh(loader, observedDates, this.selected);
		} else if (this.selected.equals(back)) {
			this.selected = this.dir;
			this.observedStudents = this.refresh(loader, observedStudents, studentRoot);
		}
	}

	@Override
	public void updateObservations(final String time, final String type, final Saved save, final Loader loader) throws IOException {
		ArrayList<String> list = loader.fillList(this.selected, new ArrayList<>());
		list.add(time + " \n " + type);
		save.writeList(this.selected, list);
	}
}
