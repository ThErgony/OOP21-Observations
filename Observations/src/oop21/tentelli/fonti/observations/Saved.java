package oop21.tentelli.fonti.observations;

/*
 * Simple class for create folder from root, return a filepath string 
 */

import java.io.File;


public class Saved {
	
	public String makeDir(String dir) {
		File createFolder = new File(dir.toString());
		createFolder.mkdirs();
		return createFolder.toString();
	}
	
}
