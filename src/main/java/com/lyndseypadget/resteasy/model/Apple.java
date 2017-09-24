package com.lyndseypadget.resteasy.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "apple")
public class Apple extends Fruit {
    
    private String color;

	@XmlElement
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
}