package oop21.tentelli.fonti.observations;

/*
 * Simple loader class, return list folder and file in the selected directory
 */

import java.io.File;
import java.util.ArrayList;

public class Loader {

	ArrayList<String> listFileFolder = new ArrayList<>();

	/** return string list of file and/or folder from directory path selected */
	public ArrayList<String> loadFileFolder(File dir) {
		for (String e : dir.list()) {
			listFileFolder.add(e);
		}
		return listFileFolder;

	}
	
}
