package com.lyndseypadget.resteasy.model;

import javax.xml.bind.annotation.XmlElement;

public abstract class Fruit {
	private String id;
    private String variety;

    @XmlElement
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getVariety() {
        return variety;
    }
    
    public void setVariety(String variety) {
        this.variety = variety;
    }
}