package org.observations.model.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.observations.model.Loader;
import org.observations.model.Saved;
import org.observations.model.Updater;

/**
 * Updater class for all string passed, create and manage all file in all class.
 * Remember the last hit (student, moment, date) passed for save and manage data.
 * Use @link {ArrayList<>}
 */
public class UpdaterImpl implements Updater {

  private final String pathMomentList;
  private final String sep;

  private final Loader loader;

  private String moment;
  private String date;
  private final String studentRoot;
  private String momentRoot;
  private String dateRoot;
  
  /**
   * Constructor for this class.

   * @param pathMomentList sep startPath loader
   *      path for moment list
   *      file separator for folder
   *      start path for student folder
   *      loader for load all file needed
   */
  public UpdaterImpl(final String pathMomentList, final String sep, final String startPath,
      final Loader loader) {
    super();
    this.pathMomentList = pathMomentList;
    this.sep = sep;
    this.studentRoot = startPath;
    this.loader = loader;
  }

  /**
   * Return list of String for the selected path.

   * @param path
   *     path selected for load all file
   */
  private List<String> check(final String path) {
    return this.loader.loadFileFolder(path);
  }

  /**
   * Choose or create the student pass.

   * @param student save
   *      student pass: if present is selected else is create with save
   *      save: reference to class for create new folder
   */
  public void chooseStudent(final String student, final Saved save) throws IOException {
    final String studentChoose = this.studentRoot + student + this.sep;
    if (!this.loader.loadFileFolder(this.studentRoot).contains(student)) {
      save.makeDir(studentChoose);
    } else {
      this.momentRoot = studentChoose;
    }
  }

  /**
   * Choose or create the moment pass for last student selected,
   * if moment already present select this root.
   * pass the list of all moment list file create, if new moment is missed add to list e sort

   * @param moment updateList save
   *      moment pass: if present is selected else is create with save
   *      updateList: if moment create is missed in the list add and sort the list
   *      save: reference to class for make new folder
   */
  public void chooseMoment(final String moment, final List<String> updateList,
      final Saved save) throws IOException {
    this.moment = this.momentRoot + moment + this.sep;
    if (!this.loader.loadFileFolder(this.momentRoot).contains(moment) && !moment.isBlank()) {
      save.makeDir(this.moment);
      if (!updateList.contains(moment)) {
        updateList.add(moment);
        updateList.sort((a, b) -> a.compareTo(b));
        save.writeList(this.pathMomentList, updateList);
      }
    } else {
      this.dateRoot = this.moment;
    }
  }

  /**
   * Choose or create the date pass for last moment and student selected.

   * @param date save
   *      date pass: if present is selected else is create with save
   *     save: reference to class for create new file
   */
  public void chooseDate(final String date, final Saved save) throws IOException {
    this.date = this.dateRoot + date;
    if (!this.loader.loadFileFolder(this.moment).contains(date)) {
      save.makeFile(this.date);
    }
  }

  /**
   * Return a list of all student observed or empty list,
   * private method observed is a control for item present.
   */
  public List<String> getObservedStudents() {
    return this.observed(this.studentRoot, this.check(this.studentRoot));
  }

  /**
   * Return a list of all moment observed for last student selected or empty list,
   * private method observed is a control for item present.
   */
  public List<String> getObservedMoments() {
    return this.observed(this.momentRoot, this.check(this.momentRoot));
  }

  /**
   * Return a list of all dates observed for last moment and student selected or empty list,
   * private method observed is a control for item present.
   */
  public List<String> getObservedDates() {
    return this.observed(this.dateRoot, this.check(this.dateRoot));
  }

  /**
   * Return a list of all observations observed for current date/moment/student or empty list,
   * private method observed is a control for item present.
   */
  public List<String> getObservedDay() throws IOException {
    return this.observed(this.date, this.loader.fillList(this.date));
  }

  /**
   * Add element pass for the current date, moment and student.

   * @param element save
   *      element pass: if present is selected else is create with save
   *      save: reference to class for create new file
   */
  public void updateObservations(final String element, final Saved save) throws IOException {
    this.updateObservations(this.date, element, save);
  }

  /**
   * Add element pass at path passed, use class save for update file.

   * @param path element save
   *      path pass: path of file need update
   *      element: element to add at file
   *      save: reference to class for create new file
   */
  public void updateObservations(final String path, final String element, final Saved save)
      throws IOException {
    final List<String> list = this.loader.fillList(path);
    list.add(element);
    list.sort((a, b) -> a.compareTo(b));
    save.writeList(path, list);
  }

  /**
   * Private method for control for item present, return empty list or list.
   */
  private List<String> observed(final String observed, final List<String> returnOk) {
    if (observed.isBlank()) {
      return new ArrayList<>();
    }
    return returnOk;
  }
}
