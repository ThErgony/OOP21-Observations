package oop21.tentelli.fonti.observations;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import oop21.tentelli.fonti.observations.core.ModelCoreImpl;
import oop21.tentelli.fonti.observations.utility.LoaderImpl;

public class TestCore {

	public static final String SEP = File.separator;
	public static final String ROOT = System.getProperty("user.home");
	public static final String SAVE_DIR = ROOT + SEP + "Observations" + SEP + "save";
	
	/* test for new core model class */
	@org.junit.Test
	public void test5UseCore() throws IOException {
		System.out.println("\ntest 5");
		Loader loadTest = new LoaderImpl();
		ArrayList<String> list = loadTest.fillList(SAVE_DIR + SEP + "pippo.txt");
		System.out.println(list);
	}

	/* test for check file/folder in selected folder */
	@org.junit.Test
	public void test6() throws IOException {
		System.out.println("\ntest 6");
		ModelCoreImpl mci = new ModelCoreImpl();
		mci.chooseStudent("pippo");
		mci.chooseStudent("pluto");
		mci.chooseStudent("pippo");
		mci.chooseMoment("prova");
		mci.chooseMoment("prova");
		mci.chooseMoment("trovami");
		mci.chooseMoment("comprami");
		System.out.println(mci.getObservedStudents().size() + " " + mci.getObservedStudents());
		assertEquals(2, mci.getObservedStudents().size());
		assertEquals(3, mci.getObservedMoments().size());
		System.out.println(mci.getObservedMoments().size() + " " + mci.getObservedMoments());
		System.out.println(mci.getArrayMomentsList() + "\n" + mci.getArrayTypeList());
		mci.chooseMoment("prova");
		mci.chooseDate("20.09.2022");
		mci.chooseDate("21.09.2022");
		mci.chooseDate("20.09.2022");
		System.out.println(mci.getObservedDates().size() + " " + mci.getObservedDates());
		String time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		mci.updateObservations(time, "prova");
		time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		mci.updateObservations(time, "asseconda prova");
		mci.addObservationType("stupiscimi");
		mci.chooseDate("21.09.2022");
		time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		mci.updateObservations(time, "provami");
		time = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
		mci.updateObservations(time, "asseconda provati");
		mci.addObservationType("stupiscimi");
		System.out.println(mci.getArrayMomentsList() + "\n" + mci.getArrayTypeList());
		System.out.println(mci.getDataDayChoose());
		System.out.println("elenco finale " + mci.getDataMomentChoose());
		System.out.println("il pair " + mci.getDataSplitDayChoose());
		System.out.println("counter day " + mci.getCounterDayChoose());
		System.out.println("counter date " + mci.getCounterDateChoose());
		System.out.println("counter moment " + mci.getCounterMomentChoose());

	}

}
