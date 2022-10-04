package org.observations.model.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.observations.model.ModelCore;
import org.observations.model.utility.Pair;

/**
 * Class for adapt data send from view to model and return map and list request from view. 
 */
public class ModelAdapter {

  private final ModelCore mc;

  public ModelAdapter() throws IOException {
    super();
    this.mc = new ModelCoreImpl();
  }

  /**
   * return list of all student observed or empty list.
   */
  public List<String> getStudentsList() throws IOException {
    return this.listMaker(this.mc.getCounterStudents());
  }

  /**
   * return map: key is the student choose, value is list of moment observed for the student.

   * @param student
   *      name of student choose
   */
  public Map<String, List<String>> getMomentsList(final String student) throws IOException {
    final Map<String, List<String>> map = new HashMap<>();
    this.mc.chooseStudent(student);
    map.put(student, this.listMaker(this.mc.getCounterMoments()));
    return map;
  }

  /**
   * return map: key is the date, value is map: key type observation, value counter.

   * @param moment
   *      moment choose 
   */
  public Map<String, Map<String, Integer>> getDatesAndObservations(final String moment)
      throws IOException {
    final Map<String, Map<String, Integer>> map = new HashMap<>();
    this.mc.chooseMoment(moment);
    final List<String> list = List.copyOf(this.listMaker(this.mc.getCounterDates()));
    for (final String element : list) {
      this.mc.chooseDate(element);
      final Map<String, Integer> mapValue = new HashMap<>();
      for (final Pair<String, Integer> pair : this.mc.getCounterDayChoose()) {
        mapValue.put(pair.getX(), pair.getY());
      }
      map.put(element, mapValue);
    }
    return map;
  }

  /**
   * make list of all string from List of Pair, use only getX() for the string attribute.

   * @param list
   *      need the list of pair to convert
   */
  private List<String> listMaker(final List<Pair<String, Integer>> list) {
    final List<String> fList = new ArrayList<>();
    for (final Pair<String, Integer> pair : list) {
      fList.add(pair.getX());
    }
    return fList;
  }

}
