package com.ness.automation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "session_queue")
public class TestSessionQueue implements Serializable{

	private static final long serialVersionUID = 8726919675341871256L;
	
	@Id
	@Column(name="run_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "test_application")
	private String testApplication;
	
	@Column(name = "test_environment")
	private String testEnvironment;
	
	@Column(name = "browser_name")
	private String testBrowserName;
	
	@Column(name = "build_number")
	private String testBuildNumber;
	
	@Column(name = "release_name")
	private String testReleaseName;
	
	@Column(name = "user_name")
	private String testUserName;
	
	@Column(name = "status")
	private String testExecutionStatus;
	
	@Column(name = "execution_type")
	private String testExecutionType;
	
	public TestSessionQueue(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTestApplication() {
		return testApplication;
	}

	public void setTestApplication(String testApplication) {
		this.testApplication = testApplication;
	}

	public String getTestEnvironment() {
		return testEnvironment;
	}

	public void setTestEnvironment(String testEnvironment) {
		this.testEnvironment = testEnvironment;
	}

	public String getTestBrowserName() {
		return testBrowserName;
	}

	public void setTestBrowserName(String testBrowserName) {
		this.testBrowserName = testBrowserName;
	}

	public String getTestBuildNumber() {
		return testBuildNumber;
	}

	public void setTestBuildNumber(String testBuildNumber) {
		this.testBuildNumber = testBuildNumber;
	}

	public String getTestReleaseName() {
		return testReleaseName;
	}

	public void setTestReleaseName(String testReleaseName) {
		this.testReleaseName = testReleaseName;
	}

	public String getTestUserName() {
		return testUserName;
	}

	public void setTestUserName(String testUserName) {
		this.testUserName = testUserName;
	}

	public String getTestExecutionType() {
		return testExecutionType;
	}

	public void setTestExecutionType(String testExecutionType) {
		this.testExecutionType = testExecutionType;
	}

	public String getTestExecutionStatus() {
		return testExecutionStatus;
	}

	public void setTestExecutionStatus(String testExecutionStatus) {
		this.testExecutionStatus = testExecutionStatus;
	}		

}
