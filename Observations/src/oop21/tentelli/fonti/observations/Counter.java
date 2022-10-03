package oop21.tentelli.fonti.observations;

import java.io.IOException;
import java.util.List;

import oop21.tentelli.fonti.observations.utility.Pair;

public interface Counter {

	/**
	 * Count all equals occurency of the list passed contained all data.
	 * Return array with first item is a string with the type of observations, second is the number of occurrency.
	 * @param arrayList
	 * 					arrayList is the list of all observations 
	 */
	List<Pair<String, Integer>> counter(List<String> list) throws IOException;

}