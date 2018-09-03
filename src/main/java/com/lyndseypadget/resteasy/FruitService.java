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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.lyndseypadget.resteasy.model.Apple;
import com.lyndseypadget.resteasy.model.FruitComparator;

@Path("/fruits")
public class FruitService {

  private static Map<String, Apple> apples = new TreeMap<String, Apple>();
  private static Comparator<Apple> comparator = new FruitComparator<Apple>();

  private static int appleCounter = 0;

  @GET
  @Path("/apples")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<Apple> getApples() {
    List<Apple> retVal = new ArrayList<Apple>(apples.values());
    Collections.sort(retVal, comparator);
    return retVal;
  }

  @GET
  @Path("/apples/{id}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response getApple(@PathParam("id") String id) {
    Apple apple = apples.get(id);
    if (apple == null) {
      return Response.status(404).build();
    }
    return Response.ok(apple).build();
  }

  @DELETE
  @Path("/apples/{id}")
  public Response deleteApple(@PathParam("id") String id) {
    Apple apple = apples.remove(id);
    if (apple == null) {
      return Response.status(404).build();
    }
    return Response.ok(200).build();
  }

  @PUT
  @Path("/apples/{id}")
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response createOrReplaceApple(@PathParam("id") String id, Apple newApple) {
    
    // update id
    newApple.setId(id);

    Boolean foundApple = apples.containsKey(id);
    if (foundApple) { // update it
      apples.remove(id);
      apples.put(id, newApple);

    } else { // add new one
      apples.put(id, newApple);
    }
    return Response.ok(200).build();
  }

  @POST
  @Path("/apples")
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response createApple(Apple newApple) {

    // finding first free id
    Integer newAppleCounterValue;
    do {
      newAppleCounterValue = ++appleCounter;
    } while (apples.containsKey(newAppleCounterValue.toString()));
    
    String newId = newAppleCounterValue.toString();
    
    newApple.setId(newId);
    apples.put(newId, newApple);

    return Response.status(201).header("Location", newId).build();
  }
}
