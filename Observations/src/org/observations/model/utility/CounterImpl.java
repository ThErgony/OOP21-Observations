package org.observations.model.utility;

/**
 * Utility class for counter number of time same observation
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.observations.model.Counter;

public class CounterImpl implements Counter {
	
	/**
	 * Remove separator " - " for all string in the list passed.
	 * Return array with this string splitted.
	 * @param arrayList
	 * 					arrayList is the list of all observations 
	 */
	private List<Pair<String, String>> splitDatas(final List<String> list) throws IOException{
		final List<Pair<String, String>> fList = new ArrayList<>();
		for (final String string : list) {
			final String[] split = string.split(" - ");
			fList.add(new Pair<>(split[0], split[1]));
		}
		return fList;
	}

	/**
	 * Count all equals occurrence of the list passed contained all data.
	 * Return array with first item is a string with the type of observations, second is the number of occurrency.
	 * @param list
	 * 					arrayList is the list of all observations 
	 */
	public List<Pair<String, Integer>> counter(final List<String> list) throws IOException {
		final List<Pair<String, Integer>> fList = new ArrayList<>();
		for (final Pair<String, String> counter : this.splitDatas(list)) {
			final List<Pair<String, Integer>> tempList = new ArrayList<>();
			tempList.addAll(fList);
			final String element = counter.getX();
			int inc = 0;
			if (!fList.isEmpty()) {	
				for (final Pair<String, Integer> item : tempList) {
					if (item.getX().equals(element)) {
						inc = item.getY();
						fList.remove(new Pair<>(item.getX(), item.getY()));
					}
				}
			}
			fList.add(new Pair<>(element, ++inc));
		}
		return fList;
	}
	
}
