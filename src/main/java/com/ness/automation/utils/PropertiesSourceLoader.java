package com.ness.automation.utils;

import org.springframework.beans.factory.annotation.Value;

import com.ness.automation.dto.ProperiesDTO;

public class PropertiesSourceLoader {
	
	@Value("${pega.test.source}")
	private String pegaSourceLocation;

	@Value("${rest.test.source}")
	private String restTestLocation;
	
	public PropertiesSourceLoader(){
		
	}

	public ProperiesDTO loadTestSourceLocations(){
		ProperiesDTO aProperiesDTO = new ProperiesDTO();
		aProperiesDTO.setPegaSourceLocation(pegaSourceLocation);
		aProperiesDTO.setRestSourceLocation(restTestLocation);
		System.out.println("Pega tests source location::::"+pegaSourceLocation);
		System.out.println("Rest tests source location ::::"+restTestLocation);		
		return aProperiesDTO;
	}

}
