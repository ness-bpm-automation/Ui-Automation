package com.ness.automation.utils;

public class ReleaseNumberGenerator {

	public static String getReleaseNumber(){
		
		String incrementReleaseNumber = "Release";    		
		for(int i=0; i<=50; i++){
			if(i==0)
				incrementReleaseNumber = String.valueOf(i);
			else
				incrementReleaseNumber = incrementReleaseNumber +", "+i;
		}
		System.out.println("Automatically Increment release no:::::"+incrementReleaseNumber);
		return incrementReleaseNumber;	
	}
}
