package org.observations.model;

/**
 * Updater class for all string passed, create and manage all file in all class.
 * Remember the last hit (student, moment, date) passed for save and manage data.
 * Use {@link ArrayList<String>}
 */

import java.io.IOException;
import java.util.List;

public interface Updater {
	
	/**
	 * choose or create the student pass
	 * @param student, save
	 * 			student pass: if present is selected else is create with save
	 * 			save: reference to class for create new folder
	 */
	void chooseStudent(String student, Saved save) throws IOException;
	
	/**
	 * choose or create the moment pass for last student selected, if moment already present select this root.
	 * list pass is the list of all moment list file create, if new moment is missed add to list e sort
	 * @param moment, updateList, save
	 * 			moment pass: if present is selected else is create with save
	 * 			updateList: if moment create is missed in the list add and sort all list
	 * 			save: reference to class for create new folder
	 */
	void chooseMoment(String moment, List<String> updateList, Saved save) throws IOException;
	
	/**
	 * choose or create the date pass for last moment and student selected
	 * @param date, save
	 * 			date pass: if present is selected else is create with save
	 * 			save: reference to class for create new file
	 */
	void chooseDate(String student, Saved save) throws IOException;
	
	/**
	 * return a list of all student observed or empty list, private method observed is a control for item present
	 */
	List<String> getObservedStudents();
	
	/**
	 * return a list of all moment observed for last student selected or empty list,
	 * private method observed is a control for item present
	 */
	List<String> getObservedMoments();
	
	/**
	 * return a list of all dates observed for last moment and student selected or empty list,
	 * private method observed is a control for item present
	 */
	List<String> getObservedDates();
	
	/**
	 * return a list of all observations observed for last date, moment and student selected or empty list,
	 * private method observed is a control for item present
	 */
	List<String> getObservedDay() throws IOException;

	/**
	 * add element pass for last date, moment and student selected
	 * @param element, save
	 * 			element pass: if present is selected else is create with save
	 * 			save: reference to class for create new file
	 */
	void updateObservations(String element, Saved save) throws IOException;

	/**
	 * add element pass at path passed, use class save for update file
	 * @param path, element, save
	 * 			path pass: path of file need update
	 * 			element: element to add at file
	 * 			save: reference to class for create new file
	 */
	void updateObservations(String string, String type, Saved save) throws IOException;

}
