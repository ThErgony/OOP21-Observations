package oop21.tentelli.fonti.observations;

/*
 * Simple class for create folder from root, return a filepath string 
 */

import java.io.File;
import java.io.IOException;


public class Saved {
	
	/** create a folder and subfolder require for the path requested, return a string of the current path directory */
	public String makeDir(String dir) {
		File createFolder = new File(dir.toString());
		createFolder.mkdirs();
		return createFolder.toString();
	}
	
	/** create a file request in the path selected, return a string of the current path of the file */
	public String makeFile(String dir, String name) throws IOException {
		File createFile = new File(dir+name);
		createFile.createNewFile();
		return createFile.toString();
	}
	
}
