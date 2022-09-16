package oop21.tentelli.fonti.observations;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public interface Loader {

	/** return string list of file and/or folder from directory path selected */
	ArrayList<String> loadFileFolder(final String dir);

	/** read file*/
	BufferedReader readFile(final String dir) throws IOException;
	
	/** import list file to array*/
	ArrayList<String> fillList(final String list, final ArrayList<String> arrayList) throws IOException;

}