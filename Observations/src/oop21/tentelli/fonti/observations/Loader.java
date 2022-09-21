package oop21.tentelli.fonti.observations;

import java.io.IOException;
import java.util.ArrayList;

public interface Loader {

	/** return string list of file and/or folder from directory path selected */
	ArrayList<String> loadFileFolder(final String dir);
	
	/** import list file to array*/
	ArrayList<String> fillList(final String path) throws IOException;

}