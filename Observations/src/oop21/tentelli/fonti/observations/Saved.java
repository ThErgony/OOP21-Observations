package oop21.tentelli.fonti.observations;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public interface Saved {

	/** create a folder and subfolder require for the path requested */
	void makeDir(final String dir);

	/** create a file request in the path selected */
	void makeFile(final String ame) throws IOException;

	/** write time and type of observations to data file */
	void updateObservations(final FileWriter fw, final String time, final String type) throws IOException;
	
	void updateList(final FileWriter fw, final String item);
	
	void writeList(final String list, final ArrayList<String> arrayList) throws IOException;

}