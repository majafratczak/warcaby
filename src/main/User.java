package main;

import java.io.Serializable;


public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private double points;
	
	
	public User(String name){
		this.name = name;
	}
	
	public User(String name, double points){
		this.name = name;
		this.points= points;
	}

	public String getName() {
		return name;
	}

	public double getPoints() {
		return points;
	}
	
	
}
