package com.lyndseypadget.resteasy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lyndseypadget.resteasy.model.Apple;
import com.lyndseypadget.resteasy.model.Apples;
import com.lyndseypadget.resteasy.model.FruitComparator;

@Path("/fruits")
public class FruitService {

	private static Map<String, Apple> apples = new TreeMap<String, Apple>();
	private static Comparator<Apple> comparator = new FruitComparator<Apple>();
	private static int appleCount = 0;

	@GET
	@Path("/apples")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Apple> getApples() {
		List<Apple> retVal = new ArrayList<Apple>(apples.values());
		Collections.sort(retVal, comparator);
		return retVal;
	}

	@GET
	@Path("/apples-wrapped")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Apples getApplesWrapped() {
		Apples retVal = new Apples();
		retVal.setApples(apples.values());
		return retVal;
	}

	@GET
	@Path("/apples/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getApple(@PathParam("id") String id) {
		Apple found = apples.get(id);
		if(found == null) {
			return Response.status(404).build();
		}
		return Response.ok(found).build();
	}
	
	@DELETE
	@Path("/apples/{id}")
	public Response deleteApple(@PathParam("id") String id) {
		apples.remove(id);
		return Response.status(200).build();
	}
	
	@POST
	@Path("/apples")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createApple(Apple apple) {
		String newId = Integer.toString(++appleCount);
	
		apple.setId(newId);
		apples.put(newId, apple);
		
		return Response.status(201).header("Location", newId).build();
	}
}