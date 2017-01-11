package com.ness.automation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_environment")
public class TestApplicationEnvironment implements Serializable{
	
	private static final long serialVersionUID = -4903856496931641134L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name = "environment_name")
	private String testEnvironmentName;
	
	@Column(name = "application_name")
	private String testApplicationName;
	
	public TestApplicationEnvironment(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestEnvironmentName() {
		return testEnvironmentName;
	}

	public void setTestEnvironmentName(String testEnvironmentName) {
		this.testEnvironmentName = testEnvironmentName;
	}

	public String getTestApplicationName() {
		return testApplicationName;
	}

	public void setTestApplicationName(String testApplicationName) {
		this.testApplicationName = testApplicationName;
	}	

}
