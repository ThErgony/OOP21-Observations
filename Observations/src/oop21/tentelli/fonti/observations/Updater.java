package oop21.tentelli.fonti.observations;

import java.io.IOException;
import java.util.ArrayList;

public interface Updater {
	
	void chooseStudent(final String student, final Saved save) throws IOException;
	
	void chooseMoment(final String moment, final ArrayList<String> updateList, final Saved save) throws IOException;
	
	void chooseDate(final String student, final Saved save) throws IOException;
	
	ArrayList<String> getObservedStudents();
	
	ArrayList<String> getObservedMoments();
	
	ArrayList<String> getObservedDates();

	void updateObservations(final String element, final Saved save) throws IOException;

	void updateObservations(final String string, final String type, final Saved save) throws IOException;

}
