package oop21.tentelli.fonti.observations;

/*
 * Simple class for load file and folder from root, return a list file or single file in the selected directory 
 */

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LoaderImpl implements Loader {

	private final ArrayList<String> listFileFolder = new ArrayList<>();

	/** return string list of file and/or folder from directory path selected */
	@Override
	public ArrayList<String> loadFileFolder(final File dir) {
		for (String e : dir.list()) {
			listFileFolder.add(e);
		}
		return listFileFolder;
	}
	
	/** read file*/
	@Override
	public BufferedReader readFile(final File dir) throws IOException {
		final BufferedReader br = new BufferedReader(new FileReader(dir));
		return br;
	}
	
}
