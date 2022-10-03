package oop21.tentelli.fonti.observations;

/**
 * Simple class for load file and folder from root, return a list file or single file in the selected directory 
 */

import java.io.IOException;
import java.util.List;

public interface Loader {

	/** return string list of file and/or folder from directory path selected 
	 * @param path
	 * 			directory path for reading file
	 */
	List<String> loadFileFolder(String path);
	
	/** import list file and return array String
	 * @param path
	 * 			directory path for reading file
	 */
	List<String> fillList(String path) throws IOException;

}