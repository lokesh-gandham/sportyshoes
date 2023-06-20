package com.sports.sportshoes.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="occasions")
public class Occasion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//Properties
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="occasionId")    
	private Long id; //primary key
	@Column(name="occasion_name")  
	private String occasionName;
	
	//Default Constructor
	public Occasion() {}
	
	//Parameterized Constructor
	public Occasion(Long id, String occasionName) {
		this.id = id;
		this.occasionName = occasionName;
	}
	
	//Getters & Setters
	public String getOccasionName() {
		return occasionName;
	}

	public void setOccasionName(String occasionName) {
		this.occasionName = occasionName;
	}

	public Long getId() {
		return id;
	}
	
	//Other Methods
	@Override
	public String toString() {
		return "Occasion [id=" + id + ", occasionName=" + occasionName + "]";
	}

}//end class