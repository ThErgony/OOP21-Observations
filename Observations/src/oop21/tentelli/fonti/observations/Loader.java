package oop21.tentelli.fonti.observations;

import java.io.BufferedReader;

/*
 * Simple loader class, return list folder and file in the selected directory
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
	
	/** read file*/
	public BufferedReader readFile(File dir) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(dir));
		return br;
	}
	
}
