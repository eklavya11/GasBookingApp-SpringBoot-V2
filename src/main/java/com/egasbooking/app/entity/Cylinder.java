package com.egasbooking.app.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Cylinder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cylinderId;
	@Size(min=1,max=8)
	private String type;
	private float weight;
	private float price;
	@Size(min=1,max=8)
	private String strapColor;
	public Cylinder() {
	}
	public Cylinder(int cylinderId, String type, float weight, String strapColor, float price) {
		super();
		this.cylinderId = cylinderId;
		this.type = type;
		this.weight = weight;
		this.strapColor = strapColor;
		this.price = price;
	}
	public int getCylinderId() {
		return cylinderId;
	}
	public void setCylinderId(int cylinderId) {
		this.cylinderId = cylinderId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getStrapColor() {
		return strapColor;
	}
	public void setStrapColor(String strapColor) {
		this.strapColor = strapColor;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Cylinder [cylinderId=" + cylinderId + ", type=" + type + ", weight=" + weight + ", strapColor="
				+ strapColor + ", price=" + price + "]";
	}
	
	
}
