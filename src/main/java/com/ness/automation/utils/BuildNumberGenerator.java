package com.ness.automation.utils;

public class BuildNumberGenerator {

	public static String getBuildNumber(){

		String incrementBuildNumber = "Build";    		
		for(int i=0; i<=50; i++){
			if(i==0)
				incrementBuildNumber = String.valueOf(i);
			else
				incrementBuildNumber = incrementBuildNumber +", "+i;
		}
		System.out.println("Automatically Increment Build no:::::"+incrementBuildNumber);
		return incrementBuildNumber;	
	}

}
