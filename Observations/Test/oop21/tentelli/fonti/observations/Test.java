package oop21.tentelli.fonti.observations;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;




public class Test {

	public static final String SEP = File.separator;
	public static final String ROOT = System.getProperty("user.home");
	public static final String SAVE_DIR = ROOT + SEP + "Observations" + SEP + "save";
	
	@org.junit.Test
	public void test1Save() {
		Saved newTest = new Saved();
		File test = new File(newTest.makeDir(SAVE_DIR));		
		System.out.println(test.toString());
		assertFalse(!test.exists());
	}
	
	@org.junit.Test
	public void test2Load() {
		Loader newTest = new Loader();
		File testLoader = new File(SAVE_DIR);
		for (String e : newTest.loadFileFolder(testLoader)) {
			System.out.println(e);
		}
	}
	
	@org.junit.Test
	public void test3CreateFile() throws IOException {
		Saved newTest = new Saved();
		File test = new File(newTest.makeFile(SAVE_DIR + SEP, "pippo.txt"));	
		System.out.println(test.toString());
		assertFalse(!test.exists());
	}

}
