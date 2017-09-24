package com.lyndseypadget.resteasy.model;

import java.util.Comparator;

public class FruitComparator<F extends Fruit> implements Comparator<F> {
	public int compare(F f1, F f2) {
		return f1.getVariety().compareTo(f2.getVariety());
	}
}