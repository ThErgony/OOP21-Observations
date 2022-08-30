package oop21.tentelli.fonti.observations;

import static org.junit.Assert.*;

import java.io.File;

public class Test {

	@org.junit.Test
	public void testRoot() {
		Saved newTest = new Saved();
		File test = new File(newTest.makeDir(""));		
		assertFalse(!test.exists());
		System.out.println(Saved.ROOT);
		System.out.println(Saved.SEP);
		String test1 = test.toString();
		System.out.println(test1);
	}

}
