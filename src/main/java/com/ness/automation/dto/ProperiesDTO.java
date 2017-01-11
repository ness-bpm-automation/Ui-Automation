package com.ness.automation.dto;

public class ProperiesDTO {
	
	private String pegaSourceLocation;
	private String restSourceLocation;	
	private String automationReportFileName;
	private String restApiReportFileName;	
	private String pegaReportGeneratedDate;
	private String restApiReportGeneratedDate;
	
	public ProperiesDTO(){
		
	}

	public String getPegaSourceLocation() {
		return pegaSourceLocation;
	}

	public void setPegaSourceLocation(String pegaSourceLocation) {
		this.pegaSourceLocation = pegaSourceLocation;
	}

	public String getRestSourceLocation() {
		return restSourceLocation;
	}

	public void setRestSourceLocation(String restSourceLocation) {
		this.restSourceLocation = restSourceLocation;
	}

	public String getPegaReportGeneratedDate() {
		return pegaReportGeneratedDate;
	}

	public void setPegaReportGeneratedDate(String pegaReportGeneratedDate) {
		this.pegaReportGeneratedDate = pegaReportGeneratedDate;
	}

	public String getRestApiReportGeneratedDate() {
		return restApiReportGeneratedDate;
	}

	public void setRestApiReportGeneratedDate(String restApiReportGeneratedDate) {
		this.restApiReportGeneratedDate = restApiReportGeneratedDate;
	}

	public String getAutomationReportFileName() {
		return automationReportFileName;
	}

	public void setAutomationReportFileName(String automationReportFileName) {
		this.automationReportFileName = automationReportFileName;
	}

	public String getRestApiReportFileName() {
		return restApiReportFileName;
	}

	public void setRestApiReportFileName(String restApiReportFileName) {
		this.restApiReportFileName = restApiReportFileName;
	}	

}
