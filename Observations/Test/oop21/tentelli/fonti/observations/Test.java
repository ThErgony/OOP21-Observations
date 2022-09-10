package oop21.tentelli.fonti.observations;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import oop21.tentelli.fonti.observations.core.ModelCoreImpl;
import oop21.tentelli.fonti.observations.utility.LoaderImpl;
import oop21.tentelli.fonti.observations.utility.SavedImpl;




public class Test {

	public static final String SEP = File.separator;
	public static final String ROOT = System.getProperty("user.home");
	public static final String SAVE_DIR = ROOT + SEP + "Observations" + SEP + "save";
	
	@org.junit.Test
	public void test1Save() {
		Saved newTest = new SavedImpl();
		newTest.makeDir(SAVE_DIR);
		File test = new File(SAVE_DIR);		
		System.out.println(test.toString());
		assertFalse(!test.exists());
	}
	
	@org.junit.Test
	public void test2Load() {
		Loader newTest = new LoaderImpl();
		File testLoader = new File(SAVE_DIR);
		for (String e : newTest.loadFileFolder(testLoader)) {
			System.out.println(e);
		}
	}
	
	@org.junit.Test
	public void test3CreateFile() throws IOException {
		Saved newTest = new SavedImpl();
		newTest.makeFile(SAVE_DIR + SEP, "pippo.txt");
		File test = new File(SAVE_DIR + SEP, "pippo.txt");	
		System.out.println(test.toString());
		assertFalse(!test.exists());
	}
	
	@org.junit.Test
	public void test3WriteReadOnFile() throws IOException {
		Saved writeTest = new SavedImpl();
		FileWriter fw = new FileWriter(new File(SAVE_DIR + SEP + "pippo.txt"));
		writeTest.updateObservations(fw, "09.02.22","prova");
		writeTest.updateObservations(fw, "09.05.22","prova");
		fw.flush();
		fw.close();
		Loader loadTest = new LoaderImpl();
		BufferedReader br = loadTest.readFile(new File(SAVE_DIR + SEP + "pippo.txt"));
		System.out.println(br.readLine());
		System.out.println(br.readLine());

	}
	
	@org.junit.Test
	public void test3UseCore() throws IOException {
		ModelCoreImpl coreTest = new ModelCoreImpl();
		FileWriter fw = new FileWriter(new File(SAVE_DIR + SEP + "pippo.txt"));
		coreTest.updateObservations(fw, "09.02.22","prova");
		coreTest.updateObservations(fw, "09.05.22","prova");
		fw.flush();
		fw.close();
		Loader loadTest = new LoaderImpl();
		BufferedReader br = loadTest.readFile(new File(SAVE_DIR + SEP + "pippo.txt"));
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		br = coreTest.readFile("observations.txt");
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		System.out.println(br.readLine());
		br.close();
	}

}
