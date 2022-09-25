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
		if (new File(dir).exists()) {
			for (final String e : new File(dir).list()) {
				listFileFolder.add(e);
			}
			listFileFolder.sort((a,b)->a.compareTo(b));
		}
		return listFileFolder;
	}
	
	/** read file
	 * @throws FileNotFoundException */
	private BufferedReader readFile(final String path) throws FileNotFoundException {
		if (!new File(path).exists()) {
			File file = new File(path);
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("Impossibile create file " + new File(path).getName());
				e.printStackTrace();
			}
		}
		return new BufferedReader(new FileReader(new File(path)));
	}
	
	/** import list file to array*/
	public ArrayList<String> fillList(final String path) throws IOException {
		ArrayList<String> arrayList = new ArrayList<>();
		BufferedReader reader = this.readFile(path);
		String item = reader.readLine();
		while (item != null) {
			arrayList.add(item);
			item = reader.readLine();			
		}
		arrayList.sort((a,b)->a.compareTo(b));
		reader.close();
		return arrayList;
	}
	
}
