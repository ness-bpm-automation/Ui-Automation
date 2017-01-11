package com.ness.automation.mapper;

import java.util.List;

import com.ness.automation.model.TestExecutionQueue;

public class TestNgModelMapper {
	
	 private String testNgTestApp;
	
	 private String testNgTestEnvironment;
	 
	 private String testNgTestBrowser;
	 
	 private String testNgBuildNumber;
	 
	 private String testNgReleaseNumber;
	 
	 private String testNgUserName;
	 
	 private String testNgExecutionType;
	 
	 private List<String> selectedPriorities;
	 
	 private String selectAllCheckbox;
	 
	 private List<TestExecutionQueue> testNgExecutionQueueObjects;
	 
	 private String testNgFileName;
	 
	 public String getTestNgTestApp() {
		return testNgTestApp;
	}

	public void setTestNgTestApp(String testNgTestApp) {
		this.testNgTestApp = testNgTestApp;
	}

	public String getTestNgTestEnvironment() {
		return testNgTestEnvironment;
	}

	public void setTestNgTestEnvironment(String testNgTestEnvironment) {
		this.testNgTestEnvironment = testNgTestEnvironment;
	}

	public String getTestNgTestBrowser() {
		return testNgTestBrowser;
	}

	public void setTestNgTestBrowser(String testNgTestBrowser) {
		this.testNgTestBrowser = testNgTestBrowser;
	}

	public String getTestNgBuildNumber() {
		return testNgBuildNumber;
	}

	public void setTestNgBuildNumber(String testNgBuildNumber) {
		this.testNgBuildNumber = testNgBuildNumber;
	}

	public String getTestNgReleaseNumber() {
		return testNgReleaseNumber;
	}

	public void setTestNgReleaseNumber(String testNgReleaseNumber) {
		this.testNgReleaseNumber = testNgReleaseNumber;
	}

	public String getTestNgUserName() {
		return testNgUserName;
	}

	public void setTestNgUserName(String testNgUserName) {
		this.testNgUserName = testNgUserName;
	}

	public String getTestNgExecutionType() {
		return testNgExecutionType;
	}

	public void setTestNgExecutionType(String testNgExecutionType) {
		this.testNgExecutionType = testNgExecutionType;
	}

	public List<String> getSelectedPriorities() {
		return selectedPriorities;
	}

	public void setSelectedPriorities(List<String> selectedPriorities) {
		this.selectedPriorities = selectedPriorities;
	}

	public String getSelectAllCheckbox() {
		return selectAllCheckbox;
	}

	public void setSelectAllCheckbox(String selectAllCheckbox) {
		this.selectAllCheckbox = selectAllCheckbox;
	}

	public List<TestExecutionQueue> getTestNgExecutionQueueObjects() {
		return testNgExecutionQueueObjects;
	}

	public void setTestNgExecutionQueueObjects(List<TestExecutionQueue> testNgExecutionQueueObjects) {
		this.testNgExecutionQueueObjects = testNgExecutionQueueObjects;
	}

	public String getTestNgFileName() {
		return testNgFileName;
	}

	public void setTestNgFileName(String testNgFileName) {
		this.testNgFileName = testNgFileName;
	}	

}
