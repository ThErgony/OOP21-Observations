package oop21.tentelli.fonti.observations.utility;

/*
 * Simple class for create folder from root, return a filepath string 
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import oop21.tentelli.fonti.observations.Saved;


public class SavedImpl implements Saved {
	
	/** create a folder and subfolder require for the path requested, return a string of the current path directory */
	@Override
	public String makeDir(final String dir) {
		final File createFolder = new File(dir.toString());
		createFolder.mkdirs();
		return createFolder.toString();
	}
	
	/** create a file request in the path selected, return a string of the current path of the file */
	@Override
	public String makeFile(final String dir, final String name) throws IOException {
		if (!new File(dir.toString()).exists()) {
			makeDir(dir);
		}
		final File createFile = new File(dir+name);
		createFile.createNewFile();
		return createFile.toString();
	}
	
	/** write time and type of observations to data file */
	@Override
	public void updateObservations(final FileWriter fw, final String time, final String type) throws IOException {	
		fw.write(time + "-" + type + "\n");
	}
	
}
