package oop21.tentelli.fonti.observations.utility;

import java.io.File;
import java.util.ArrayList;

import oop21.tentelli.fonti.observations.CheckFileFolder;
import oop21.tentelli.fonti.observations.Loader;

public class CheckFileFolderImpl implements CheckFileFolder {

	private final String dir;
	
	public CheckFileFolderImpl(final String dir) {
		this.dir = dir;
	}

	public ArrayList<String> check(final Loader loader) {
		
		return loader.loadFileFolder(new File(dir));
	}
	
	
}
