package oop21.tentelli.fonti.observations;

/**
 * Simple class for create folder from root, return a filepath string 
 */

import java.io.IOException;
import java.util.List;

public interface Saved {

	/** create a folder and subfolder require for the path requested 
	 * @param dir
	 *             absolute path for create folder
	 */
	void makeDir(String dir);

	/** create a file request in the path selected
	 * @param name
	 *             absolute path for create file
	 */
	void makeFile(String name) throws IOException;
	
	
	/** create file and write all the file in the array list
 	 * @param path, updateList
 	 *            path absolute path for create file if missed
 	 *            updateList list of all file to copy
 	 */
	void writeList(String path, List<String> updateList) throws IOException;

}