package oop21.tentelli.fonti.observations.utility;

/*
 * Simple class for create folder from root, return a filepath string 
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import oop21.tentelli.fonti.observations.Saved;


public class SavedImpl implements Saved {
	
	/** create a folder and subfolder require for the path requested */
	public void makeDir(final String dir) {
		final File createFolder = new File(dir);
		createFolder.mkdirs();
	}
	
	/** create a file request in the path selected*/
	public void makeFile(final String name) {
		final File createFile = new File(name);
		try {
			createFile.createNewFile();
		} catch (IOException e) {
			System.out.println("Impossible create file\n");
			e.printStackTrace();
		}
	}
	
	/** write time and type of observations to data file */
	public void updateObservations(final FileWriter fw, final String time, final String type) {	
		try {
			fw.write(time + "-" + type + "\n");
		} catch (IOException e) {
			System.out.println("Impossibile add observations");
			e.printStackTrace();
		}
	}
	
	public void updateList(final FileWriter fw, final String item) {
		try {
			fw.write(item + "\n");
		} catch (IOException e) {
			System.out.println("Impossible add item to list");
			e.printStackTrace();
		}
	}
	
	/* create file and write all the file in the array list*/
	public void writeList(final String list, final ArrayList<String> arrayList) throws IOException {
		this.makeFile(list);
		final FileWriter fw = new FileWriter(new File(list));
		for (final String item : arrayList) {
			this.updateList(fw, item);
		}
		fw.flush();
		fw.close();
	}
}
