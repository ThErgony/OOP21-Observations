package org.observations.model.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.observations.model.Counter;
import org.observations.model.Loader;
import org.observations.model.ModelCore;
import org.observations.model.Saved;
import org.observations.model.Updater;
import org.observations.model.utility.CounterImpl;
import org.observations.model.utility.FirstLoaderImpl;
import org.observations.model.utility.LoaderImpl;
import org.observations.model.utility.Pair;
import org.observations.model.utility.SavedImpl;
import org.observations.model.utility.UpdaterImpl;

/**
 * Model core create all class need and pass reference for create, write, save and load.
 * Make first load if is first time start software application create file moment list and
 * type observed list text format used to show the list of item user can get and add in future.
 * Updater get all file for update and create file and folder user required.
 */
public class ModelCoreImpl implements ModelCore {

  private static final String SEP = File.separator;
  private static final String ROOT = System.getProperty("user.home");
  private static final String NAME_APP = "Observations";
  private static final String SAVE_DIR = "save";
  private static final String STUDENT_DIR = "students";
  private static final String DIR = ROOT + SEP + NAME_APP + SEP + SAVE_DIR + SEP;

  private static final String MOMENTS_LIST = "moments.txt";
  private static final String TYPE_OBSERVED_LIST = "observations.txt";
  private static final String EXTENSION = ".txt";

  private final Saved save;
  private final Loader loader;
  private final Updater updater;
  private final Counter counter;

  private String student = "";
  private String moment = "";

  private String tempStudent = "";
  private String tempMoment = "";

  /**
   * create new object for counter, save and loader, first start and updater with parameter need.
   * FirstLoaderImpl: root save and student folder, 2 string for name list moment and type, save.
   * UpdaterImpl: path moments list, separator, path for students save e loader
   */
  public ModelCoreImpl() throws IOException {
    super();
    this.save = new SavedImpl();
    this.loader = new LoaderImpl();
    this.counter = new CounterImpl();
    new FirstLoaderImpl().firstLoad(DIR, STUDENT_DIR, MOMENTS_LIST, TYPE_OBSERVED_LIST, this.save);
    this.updater = new UpdaterImpl(DIR + MOMENTS_LIST, SEP, DIR + STUDENT_DIR + SEP, this.loader);
  }

  /**
   * return copy of the list of all moments user can choose.
   */
  public List<String> getMomentsList() throws IOException {
    return new ArrayList<>(List.copyOf(this.loader.fillList(DIR + MOMENTS_LIST)));
  }

  /**
   * return copy of the list of all types of observations user can choose.
   */
  public List<String> getTypeList() throws IOException {
    return new ArrayList<>(List.copyOf(this.loader.fillList(DIR + TYPE_OBSERVED_LIST)));
  }

  /**
   * method for choose student or create if folder missed.

   * @param student
   *      string for name student choose/selected 
   */
  public void chooseStudent(final String student) throws IOException {
    this.student = student;
    this.updater.chooseStudent(student, this.save);
  }

  /**
   * method for choose moment for selected student or create if folder missed.

   * @param moment
   *      moment choose/selected for selected student 
   */
  public void chooseMoment(final String moment) throws IOException {
    this.moment = moment;
    this.updater.chooseMoment(moment, this.getMomentsList(), this.save);
  }

  /**
   * method for choose data for selected student and moment or create if file missed.

   * @param date
   *      date choose/selected for selected student and moment 
   */
  public void chooseDate(final String date) throws IOException {
    this.updater.chooseDate(date + EXTENSION, this.save);
  }

  /**
   * method for add new click (observation) at student, moment and date choose.

   * @param time type
   *      time is a string for the HH:mm:ss of click
   *      type is a string for name of type observation
   */
  public void updateObservations(final String time, final String type) throws IOException {
    this.updater.updateObservations(type + " - " + time, this.save);
  }

  /**
   * method for add new type of observation in the first list created.

   * @param type
   *      type is a string for name of type observation
   */
  public void addObservationType(final String type) throws IOException {
    if (!this.getTypeList().contains(type)) {
      this.updater.updateObservations(DIR + TYPE_OBSERVED_LIST, type, this.save);
    }
  }

  /**
   * return a copy of list of all observation for current student, moment and date choose.
   */
  public List<String> getDataDayChoose() throws IOException {
    return new ArrayList<>(List.copyOf(this.updater.getObservedDay()));
  }

  /**
   * return a copy of list for current student and moment.
   * List contain pair: first item is a string with type of observation,
   * second item is number of time the type observed.
   * It can be used for generate graph
   */
  public List<Pair<String, Integer>> getDataMomentChoose() throws IOException {
    return this.counter.counter(this.momentObservations());
  }

  /**
   * return a copy of list for current student.
   * List contain pair: first item is a string with type of observation,
   * second item is number of time the type is observed. 
   * It can be used for generate graph
   */
  public List<Pair<String, Integer>> getDataStudentChoose() throws IOException {
    return this.counter.counter(this.studentObservations());
  }

  /**
   * return a copy of list for current student, moment and date.
   * List contain pair: first item is a string with type of observation,
   * second item is number of time the type is observed.
   * It can be used for generate graph and refresh counter types for the day selected.
   */
  public List<Pair<String, Integer>> getCounterDayChoose() throws IOException {
    return this.counter.counter(this.getDataDayChoose());
  }

  /**
   * return a copy of list for current student and moment.
   * List contain pair: first item is a string with date,
   * second item is number of all observations in this date.
   * It can be used for refresh counter view for date button.
   */
  public List<Pair<String, Integer>> getCounterDates() throws IOException {
    final List<Pair<String, Integer>> list = new ArrayList<>();
    for (final String element : this.getObservedDates()) {
      this.chooseDate(element);
      final List<Pair<String, Integer>> tempList = this.counter.counter(this.getDataDayChoose());
      int sum = 0;
      for (final Pair<String, Integer> pair : tempList) {
        sum += pair.getY();
      }
      list.add(new Pair<>(element, sum));
    }
    return list;
  }

  /**
   * return a copy of list for current student.
   * List contain pair: first item is a string with moment,
   * second item is number of all observations in this date.
   * It can be used for refresh counter view for moment button.
   */
  public List<Pair<String, Integer>> getCounterMoments() throws IOException {
    if (this.tempStudent.isBlank()) {
      this.tempMoment = this.moment;
    }
    final List<Pair<String, Integer>> list = new ArrayList<>();
    for (final String element : this.getObservedMoments()) {
      this.chooseMoment(element);
      final List<Pair<String, Integer>> tempList = this.getCounterDates();
      int sum = 0;
      for (final Pair<String, Integer> pair : tempList) {
        sum += pair.getY();
      }
      list.add(new Pair<>(element, sum));
    }
    if (this.tempStudent.isBlank()) {
      this.refreshMoment(this.tempMoment);
      this.resetMoment();
    }
    return list;
  }

  /**
   * return a copy of list for all students.
   * List contain pair: first item is a string with student,
   * second item is number of all observations in this student.
   * It can be used for refresh counter view for student button.
   */
  public List<Pair<String, Integer>> getCounterStudents() throws IOException {
    if (this.tempStudent.isBlank() && !this.student.isBlank()) { //check if student not selected yet
      tempStudent = this.student;
      tempMoment = this.moment;
    }
    final List<Pair<String, Integer>> list = new ArrayList<>();
    for (final String element : this.getObservedStudents()) {
      this.chooseStudent(element);
      final List<Pair<String, Integer>> tempList = this.getCounterMoments();
      int sum = 0;
      for (final Pair<String, Integer> pair : tempList) {
        sum += pair.getY();
      }
      list.add(new Pair<>(element, sum));
    }
    this.refreshStudent(this.tempStudent, this.tempMoment); //reset temporary variable
    this.resetStudent();
    return list;
  }

  /**
   * Return list of all observation for all date observed for the student and moment choose. 
   */
  private List<String> momentObservations() throws IOException {
    final List<String> list = new ArrayList<>();
    for (final String e : this.getObservedDates()) {
      this.chooseDate(e);
      list.addAll(this.getDataDayChoose());
    }
    list.sort((a, b) -> a.compareTo(b));
    return list;
  }

  /**
   * Return list of all observation for all dates and all moments observed for the current student. 
   */
  private List<String> studentObservations() throws IOException {
    this.tempMoment = this.moment;
    final List<String> list = new ArrayList<>();
    for (final String e : this.getObservedMoments()) {
      this.chooseMoment(e);
      list.addAll(this.momentObservations());
    }
    list.sort((a, b) -> a.compareTo(b));
    this.refreshMoment(this.tempMoment);
    this.resetMoment();
    return list;
  }

  /**
   * Return list of all students observed. 
   */
  private List<String> getObservedStudents() {
    return new ArrayList<>(List.copyOf(this.updater.getObservedStudents()));
  }

  /**
   * Return list of all moments observed for the current student. 
   */
  private List<String> getObservedMoments() {
    return new ArrayList<>(List.copyOf(this.updater.getObservedMoments()));
  }

  /**
   * Return list of all dates observed for the current student and moment. 
   */
  private List<String> getObservedDates() {
    final List<String> list = new ArrayList<>();
    for (final String string : List.copyOf(this.updater.getObservedDates())) {
      final String s;
      s = string.substring(0, string.length() - 4);
      list.add(s);
    }
    return list;
  }

  /**
   * simple refresher for choose student and refresh moment.

   * @param stud mom
   *      student choose
   *      moment choose 
   */
  private void refreshStudent(final String stud, final String mom) throws IOException {
    this.student = stud;
    this.chooseStudent(this.student);
    this.refreshMoment(mom);
  }

  /**
   * simple refresher for choose moment.

   * @param mom
   *      moment choose 
   */
  private void refreshMoment(final String mom) throws IOException {
    this.moment = mom;
    this.chooseMoment(this.moment);
  }

  /**
   * simple reset for student for access control.
   */
  private void resetStudent() {
    this.tempStudent = "";
    this.resetMoment();
  }
  
  /**
   * simple reset for moment for access control.
   */
  private void resetMoment() {
    this.tempMoment = "";
  }

}