package oop21.tentelli.fonti.observations;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface Loader {

	/** return string list of file and/or folder from directory path selected */
	public ArrayList<String> loadFileFolder(File dir);

	/** read file*/
	public BufferedReader readFile(final File dir) throws IOException;

}