package oop21.tentelli.fonti.observations;

/*
 * Simple loader class, return list folder and file in the selected directory
 */

import java.io.File;
import java.util.ArrayList;

public class Loader {

	ArrayList<String> listFolder = new ArrayList<>();
	ArrayList<String> listFile = new ArrayList<>();

	/** return string list of folder from directory path selected */
	public ArrayList<String> loadFolder(File dir) {
		for (String e : dir.list()) {
			listFolder.add(e);
		}
		return listFolder;

	}
	
}
