package com.lyndseypadget.resteasy;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class FruitApplication extends Application
{
   HashSet<Object> singletons = new HashSet<Object>();

   public FruitApplication()
   {
      singletons.add(new FruitService());
   }

   @Override
   public Set<Class<?>> getClasses()
   {
      HashSet<Class<?>> set = new HashSet<Class<?>>();
      return set;
   }

   @Override
   public Set<Object> getSingletons()
   {
      return singletons;  
   }
}
