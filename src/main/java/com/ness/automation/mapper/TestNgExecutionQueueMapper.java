package com.ness.automation.mapper;

import java.util.Date;

public class TestNgExecutionQueueMapper {
	
	private int testCaseRunId;
	
	private String testCaseClass;
	
	private String testCaseMethod;
	
	private String testCasePriority;
	
	private String testCaseFeature;
	
	private String testCaseAutomatedBy;
	
	private String testCaseLastModifiedBy;

	private String testCaseManualEffort;
	
	private String testCaseGroups;
	
	private String testCaseDependonGroups;
	
	private String testCaseDependonMethods;	

	private Date testCaseCreatedDate;

	public int getTestCaseRunId() {
		return testCaseRunId;
	}

	public void setTestCaseRunId(int testCaseRunId) {
		this.testCaseRunId = testCaseRunId;
	}

	public String getTestCaseClass() {
		return testCaseClass;
	}

	public void setTestCaseClass(String testCaseClass) {
		this.testCaseClass = testCaseClass;
	}

	public String getTestCaseMethod() {
		return testCaseMethod;
	}

	public void setTestCaseMethod(String testCaseMethod) {
		this.testCaseMethod = testCaseMethod;
	}

	public String getTestCasePriority() {
		return testCasePriority;
	}

	public void setTestCasePriority(String testCasePriority) {
		this.testCasePriority = testCasePriority;
	}

	public String getTestCaseFeature() {
		return testCaseFeature;
	}

	public void setTestCaseFeature(String testCaseFeature) {
		this.testCaseFeature = testCaseFeature;
	}

	public String getTestCaseAutomatedBy() {
		return testCaseAutomatedBy;
	}

	public void setTestCaseAutomatedBy(String testCaseAutomatedBy) {
		this.testCaseAutomatedBy = testCaseAutomatedBy;
	}

	public String getTestCaseLastModifiedBy() {
		return testCaseLastModifiedBy;
	}

	public void setTestCaseLastModifiedBy(String testCaseLastModifiedBy) {
		this.testCaseLastModifiedBy = testCaseLastModifiedBy;
	}

	public String getTestCaseManualEffort() {
		return testCaseManualEffort;
	}

	public void setTestCaseManualEffort(String testCaseManualEffort) {
		this.testCaseManualEffort = testCaseManualEffort;
	}

	public String getTestCaseGroups() {
		return testCaseGroups;
	}

	public void setTestCaseGroups(String testCaseGroups) {
		this.testCaseGroups = testCaseGroups;
	}

	public String getTestCaseDependonGroups() {
		return testCaseDependonGroups;
	}

	public void setTestCaseDependonGroups(String testCaseDependonGroups) {
		this.testCaseDependonGroups = testCaseDependonGroups;
	}

	public String getTestCaseDependonMethods() {
		return testCaseDependonMethods;
	}

	public void setTestCaseDependonMethods(String testCaseDependonMethods) {
		this.testCaseDependonMethods = testCaseDependonMethods;
	}

	public Date getTestCaseCreatedDate() {
		return testCaseCreatedDate;
	}

	public void setTestCaseCreatedDate(Date testCaseCreatedDate) {
		this.testCaseCreatedDate = testCaseCreatedDate;
	}	

}
