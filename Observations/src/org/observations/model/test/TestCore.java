package org.observations.model.test;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.observations.model.Loader;
import org.observations.model.ModelCore;
import org.observations.model.core.ModelAdapter;
import org.observations.model.core.ModelCoreImpl;
import org.observations.model.utility.LoaderImpl;

/** Simple class for test model core e model adapter. */
public class TestCore {

  private static final String TIME_STAMP = "HH:mm:ss";
  private static final String STUPISCIMI = "stupiscimi";
  private static final String COMPRAMI = "comprami";
  private static final String TROVAMI = "trovami";
  private static final String PLUTO = "pluto";
  private static final String PROVA = "prova";
  private static final String PIPPO = "pippo";
  public static final String SEP = File.separator;
  public static final String ROOT = System.getProperty("user.home");
  public static final String SAVE_DIR = ROOT + SEP + "Observations" + SEP + "save";

  /** test for new core model class. */
  @org.junit.Test
  public void test5UseCore() throws IOException {
    System.out.println("\ntest 5");
    final Loader loadTest = new LoaderImpl();
    final List<String> list = loadTest.fillList(SAVE_DIR + SEP + "pippo.txt");
    System.out.println(list);
  }

  /** test for check file/folder in selected folder with model core. */
  @org.junit.Test
  public void test6() throws IOException {
    System.out.println("\ntest 6");
    final ModelCore mci = new ModelCoreImpl();
    System.out.println("moments list " + mci.getMomentsList() + "\n"
        + "types list " + mci.getTypeList());
    mci.chooseStudent(PIPPO);
    mci.chooseStudent(PLUTO);
    mci.chooseStudent(PIPPO);
    mci.chooseMoment(PROVA);
    mci.chooseMoment(PROVA);
    mci.chooseMoment(TROVAMI);
    mci.chooseMoment(COMPRAMI);
    mci.chooseMoment(PROVA);
    mci.chooseDate("20.09.2022");
    mci.chooseDate("21.09.2022");
    mci.chooseDate("20.09.2022");
    String time = new SimpleDateFormat(TIME_STAMP, Locale.ITALIAN)
        .format(Date.from(Instant.now(Clock.systemDefaultZone())));
    mci.updateObservations(time, PROVA);
    time = new SimpleDateFormat(TIME_STAMP, Locale.ITALIAN)
        .format(Date.from(Instant.now(Clock.systemDefaultZone())));
    mci.updateObservations(time, "asseconda prova");
    mci.addObservationType(STUPISCIMI);
    mci.addObservationType(STUPISCIMI);
    mci.chooseDate("21.09.2022");
    time = new SimpleDateFormat(TIME_STAMP, Locale.ITALIAN)
        .format(Date.from(Instant.now(Clock.systemDefaultZone())));
    mci.updateObservations(time, "provami");
    time = new SimpleDateFormat(TIME_STAMP, Locale.ITALIAN)
        .format(Date.from(Instant.now(Clock.systemDefaultZone())));
    mci.updateObservations(time, "asseconda provati");
    System.out.println("moments list " + mci.getMomentsList() + "\n"
        + "types list " + mci.getTypeList());
    System.out.println("data student choose" + mci.getDataStudentChoose());
    System.out.println("data moment choose" + mci.getDataMomentChoose());
    System.out.println("data day choose " + mci.getDataDayChoose());
    System.out.println("counter observed day " + mci.getCounterDayChoose());
    System.out.println("counter observed date " + mci.getCounterDates() + " "
        + mci.getCounterDates().size());
    System.out.println("counter observed moment " + mci.getCounterMoments() + " "
        + mci.getCounterMoments().size());
    System.out.println("counter observed student " + mci.getCounterStudents() + " "
        + mci.getCounterStudents().size());
    assertEquals(2, mci.getCounterStudents().size());
    System.out.println("counter observed moment " + mci.getCounterMoments() + " "
        + mci.getCounterMoments().size());
    System.out.println("counter observed date " + mci.getCounterDates() + " "
        + mci.getCounterDates().size());
    assertEquals(3, mci.getCounterMoments().size());
    assertEquals(2, mci.getCounterDates().size());
    mci.chooseMoment(COMPRAMI);
    assertEquals(0, mci.getCounterDates().size());
  }

  /** test for test class model adapter. */
  @org.junit.Test
  public void test7UseAdapter() throws IOException {
    System.out.println("\ntest 7");
    final ModelAdapter ma = new ModelAdapter();
    assertEquals(2, ma.getStudentsList().size());
    System.out.println(ma.getStudentsList());
    Map<String, List<String>> map = ma.getMomentsList(PIPPO);
    assertEquals(3, map.get(PIPPO).size());
    map = ma.getMomentsList(PLUTO);
    assertEquals(0, map.get(PLUTO).size());
    map = ma.getMomentsList(PIPPO);
    System.out.println(ma.getDatesAndObservations(PROVA));
    assertEquals(3, map.get(PIPPO).size());
  }

}
