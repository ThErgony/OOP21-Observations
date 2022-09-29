package oop21.tentelli.fonti.observations;

import java.io.IOException;
import java.util.ArrayList;

import oop21.tentelli.fonti.observations.utility.Pair;

public interface Counter {

	/**
	 * Count all equals occurency of the list passed contained all data.
	 * Return array with first item is a string with the type of observations, second is the number of occurrency.
	 * @param arrayList
	 * 					arrayList is the list of all observations 
	 */
	ArrayList<Pair<String, Integer>> counter(final ArrayList<String> arrayList) throws IOException;

}