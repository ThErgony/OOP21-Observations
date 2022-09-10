package oop21.tentelli.fonti.observations;

import java.io.FileWriter;
import java.io.IOException;

public interface Saved {

	/** create a folder and subfolder require for the path requested */
	public void makeDir(final String dir);

	/** create a file request in the path selected */
	public void makeFile(final String dir, final String name) throws IOException;

	/** write time and type of observations to data file */
	public void updateObservations(final FileWriter fw, final String time, final String type) throws IOException;
	
	public void updateList(final FileWriter fw, final String item);

}