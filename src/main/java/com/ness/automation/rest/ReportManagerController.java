package com.ness.automation.rest;

import java.io.File;
import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ness.automation.dto.ProperiesDTO;

@RestController
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
@RequestMapping("/rest/report")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReportManagerController {
	
	private static final Logger LOGGER = Logger.getLogger(ReportManagerController.class);
	
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	@Value("${pega.test.source}")
	private String pegaSourceLocation;

	@Value("${rest.test.source}")
	private String restTestLocation;
	
	@Value("${pega.html.report.file}")
	private String automationReportFileName;
	
	@Value("${restapi.html.report.file}")
	private String restApiReportFileName;
	
	
	@RequestMapping(value = "/getReportData", method = RequestMethod.GET)
	public ResponseEntity<ProperiesDTO> getReportData() {
		
		ProperiesDTO aProperiesDTO = new ProperiesDTO();
		aProperiesDTO.setPegaSourceLocation(pegaSourceLocation);
		aProperiesDTO.setRestSourceLocation(restTestLocation);
		
		File pegaReportFile = new File(pegaSourceLocation+ "/" + automationReportFileName);
		
		File restApiReportFile = new File(restTestLocation+ "/" + restApiReportFileName);		
		
		aProperiesDTO.setPegaReportGeneratedDate(sdf.format(pegaReportFile.lastModified()));
		aProperiesDTO.setRestApiReportGeneratedDate(sdf.format(restApiReportFile.lastModified()));
		aProperiesDTO.setAutomationReportFileName(automationReportFileName);
		aProperiesDTO.setRestApiReportFileName(restApiReportFileName);
		
		return new ResponseEntity<ProperiesDTO>(aProperiesDTO, HttpStatus.OK);		
	}

}
