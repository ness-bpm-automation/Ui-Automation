package com.ness.automation.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "execution_queue")
public class TestExecutionQueue implements Serializable{

	private static final long serialVersionUID = -4974830860340281292L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "run_id")
	private int testCaseRunId;
	@Column(name = "test_class")
	private String testCaseClass;
	@Column(name = "test_method")
	private String testCaseMethod;
	@Column(name = "test_case_id")
	private String testCaseId;
	@Column(name = "priority")
	private String testCasePriority;
	@Column(name = "features")
	private String testCaseFeature;
	@Column(name = "automation_author")
	private String testCaseCreatedBy;
	@Column(name = "last_modified")
	private Date testCaseLastModifiedBy;
	@Column(name = "manual_effort")
	private String testCaseManualEffort;
	@Column(name = "groups")
	private String testCaseGroups;
	@Column(name = "depends_on_groups")
	private String testCaseDependonGroups;
	@Column(name = "depends_on_methods")
	private String testCaseDependonMethods;	
	/*@Column(name = "created_date")
	private Date testCaseCreatedDate;*/
	
	public TestExecutionQueue(){
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public String getTestCaseCreatedBy() {
		return testCaseCreatedBy;
	}
	public void setTestCaseCreatedBy(String testCaseCreatedBy) {
		this.testCaseCreatedBy = testCaseCreatedBy;
	}
	
	public Date getTestCaseLastModifiedBy() {
		return testCaseLastModifiedBy;
	}
	public void setTestCaseLastModifiedBy(Date testCaseLastModifiedBy) {
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
	public int getTestCaseRunId() {
		return testCaseRunId;
	}
	public void setTestCaseRunId(int testCaseRunId) {
		this.testCaseRunId = testCaseRunId;
	}
	public String getTestCaseId() {
		return testCaseId;
	}
	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}	
	
	/*public Date getTestCaseCreatedDate() {
		return testCaseCreatedDate;
	}
	public void setTestCaseCreatedDate(Date testCaseCreatedDate) {
		this.testCaseCreatedDate = testCaseCreatedDate;
	}	*/
}
