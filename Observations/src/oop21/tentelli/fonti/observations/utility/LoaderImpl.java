package oop21.tentelli.fonti.observations.utility;

/*
 * Simple class for load file and folder from root, return a list file or single file in the selected directory 
 */

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import oop21.tentelli.fonti.observations.Loader;

public class LoaderImpl implements Loader {

	/** return string list of file and/or folder from directory path selected */
	public ArrayList<String> loadFileFolder(final String dir) {
		ArrayList<String> listFileFolder = new ArrayList<>();
		for (final String e : new File(dir).list()) {
			listFileFolder.add(e);
		}
		return listFileFolder;
	}
	
	/** read file
	 * @throws FileNotFoundException */
	public BufferedReader readFile(final String dir) throws FileNotFoundException {
		if (!new File(dir).exists()) {
			File file = new File(dir);
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Impossibile create file " + new File(dir).getName());
				e.printStackTrace();
			}
		}
		return new BufferedReader(new FileReader(new File(dir)));
	}
	
	/* import list file to array*/
	public ArrayList<String> fillList(final String list, final ArrayList<String> arrayList) throws IOException {
		arrayList.clear();
		BufferedReader reader = this.readFile(list);
		String item = reader.readLine();
		while (item != null) {
			arrayList.add(item);
			item = reader.readLine();			
		}
		reader.close();
		return arrayList;
	}
	
}
