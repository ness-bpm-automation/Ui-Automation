package com.ness.automation.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestInfo {

	public enum Priority {
		LOW, MEDIUM, HIGH
	}

	String[] testCaseId();

	Priority priority() default Priority.MEDIUM;

	String[] features() default "";

	String automatedBy() default "Nagesh";

	String lastModified() default "";

	int manualEffortInMinutes();
}