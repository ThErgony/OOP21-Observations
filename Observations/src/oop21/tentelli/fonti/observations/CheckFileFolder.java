package oop21.tentelli.fonti.observations;

import java.io.IOException;
import java.util.ArrayList;

public interface CheckFileFolder {

	ArrayList<String> check(final Loader loader, final String dir);
	
	public void chooseStudent(final String student, final Saved save, final Loader loader) throws IOException;
	
	public void chooseMoment(final String moment, final ArrayList<String> updateList, final Saved save, final Loader loader) throws IOException;
	
	public void chooseDate(final String student, final Saved save, final Loader loader) throws IOException;
	
	public ArrayList<String> getObservedStudents();
	
	public ArrayList<String> getObservedMoments();
	
	public ArrayList<String> getObservedDates();

	void updateObservations(final String element, final Saved save, final Loader loader) throws IOException;

	void updateObservations(final String string, final String type, final Saved save, final Loader loader) throws IOException;

}
