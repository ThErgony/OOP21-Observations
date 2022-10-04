package org.observations.model.test;


import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.observations.model.Loader;
import org.observations.model.Saved;
import org.observations.model.Updater;
import org.observations.model.utility.LoaderImpl;
import org.observations.model.utility.SavedImpl;
import org.observations.model.utility.UpdaterImpl;



/** class test.*/
public class Test extends TestCore {

  private static final String PIPPO_TXT = "pippo.txt";
  public static final String SEP = File.separator;
  public static final String ROOT = System.getProperty("user.home");
  public static final String SAVE_DIR = ROOT + SEP + "Observations" + SEP + "save";

  /** test make dir in user folder. */
  @org.junit.Test
  public void test1Save() {
    System.out.println("\ntest 1");
    final Saved newTest = new SavedImpl();
    newTest.makeDir(SAVE_DIR);
    final File test = new File(SAVE_DIR);
    System.out.println(test.toString());
    assertTrue(test.exists());
  }

  /** test print all element read in selected directory. */
  @org.junit.Test
  public void test2Load() {
    System.out.println("\ntest 2");
    final Loader newTest = new LoaderImpl();
    for (final String e : newTest.loadFileFolder(new File(SAVE_DIR).getParent())) {
      System.out.println("parent - " + e);
    }
    for (final String e : newTest.loadFileFolder(SAVE_DIR)) {
      System.out.println("save dir - " + e);
    }
  }

  /* test make file with name choose in selected folder */
  @org.junit.Test
  public void test3CreateFile() throws IOException {
    System.out.println("\ntest 3");
    final Saved newTest = new SavedImpl();
    newTest.makeFile(SAVE_DIR + SEP + PIPPO_TXT);
    final File test = new File(SAVE_DIR + SEP, PIPPO_TXT);
    System.out.println(test.toString());
    assertTrue(test.exists());
  }

  /* test from add string to file and read is content*/
  @org.junit.Test
  public void test4WriteReadOnFile() throws IOException {
    System.out.println("\ntest 4");
    final Loader loader = new LoaderImpl();
    final Saved save = new SavedImpl();
    final Updater upd = new UpdaterImpl(SAVE_DIR + SEP + PIPPO_TXT, SEP, SAVE_DIR + SEP);
    String time = new SimpleDateFormat("HH:mm:ss", Locale.ITALIAN)
        .format(Date.from(Instant.now(Clock.systemDefaultZone())));
    System.out.println("time " + time);
    upd.updateObservations(SAVE_DIR + SEP + PIPPO_TXT, "prova" + " - " + time, save, loader);
    time = LocalDate.now().toString();
    upd.updateObservations(SAVE_DIR + SEP + PIPPO_TXT, "4prova4" + " - " + time, save, loader);
    final List<String> list = loader.fillList(SAVE_DIR + SEP + PIPPO_TXT);
    System.out.println(list);
  }

}
