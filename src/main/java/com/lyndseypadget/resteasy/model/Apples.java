package com.lyndseypadget.resteasy.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "apples")
public class Apples {

	private static Comparator<Apple> comparator = new FruitComparator<Apple>();
	
	@XmlElement(name = "apple", type = Apple.class)
	private List<Apple> apples;

	public List<Apple> getApples() {
		Collections.sort(apples, comparator);
		return apples;
	}
	
	public void setApples(Collection<Apple> apples) {
		this.apples = new ArrayList<Apple>(apples);
	}
}