package com.lyndseypadget.resteasy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lyndseypadget.resteasy.model.Apple;
import com.lyndseypadget.resteasy.model.FruitComparator;

@Path("/fruits")
public class FruitService {

	private static Map<String, Apple> apples = new TreeMap<String, Apple>();
	private static Comparator<Apple> comparator = new FruitComparator<Apple>();

	@GET
	@Path("/apples")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Apple> getApples() {
		List<Apple> retVal = new ArrayList<Apple>(apples.values());
		Collections.sort(retVal, comparator);
		return retVal;
	}
}