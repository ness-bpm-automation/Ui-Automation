package com.ness.automation.rest;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

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

import com.ness.automation.utils.AutomationConstants;

@RestController
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
@RequestMapping("/rest/config")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConfigManagerController {

	private static final Logger LOGGER = Logger.getLogger(ConfigManagerController.class);

	@Value("${rest.test.source}")
	private String restTestLocation;

	@RequestMapping(value = "/launchRestApiTest", method = RequestMethod.POST)
	public ResponseEntity<Void> launchRestApiTest() {

		LOGGER.info("Inside launchRestApiTest()::::");

		FileOutputStream fos = null;

		File file= null;

		try {

			String path = System.getProperty("user.dir");

			LOGGER.info("Current Working Directory: " + path);

			file = new File(path + "/" + AutomationConstants.TESTNG_LAUNCH_FILE_NAME +".bat");

			fos = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			DataOutputStream dos=new DataOutputStream(fos); 

			dos.writeBytes("cd " +restTestLocation);

			dos.writeBytes("\n");			

			dos.writeBytes("call ");
			dos.writeBytes("mvn ");
			dos.writeBytes("surefire-report:report ");
			dos.writeBytes("-Dtest=*Test,*Tests ");
			dos.writeBytes("-Dtest.env=");
			dos.writeBytes("pega");
			dos.writeBytes(".");
			dos.writeBytes("dev");
			dos.writeBytes("\n");
			dos.writeBytes("EXIT");

			LOGGER.info("Wrote the batch file to the specified folder");

			fos.flush();
			fos.close();		

			Process restExcuteProcess = Runtime.getRuntime().exec("cmd /c start/wait " + AutomationConstants.TESTNG_LAUNCH_FILE_NAME +".bat");

			restExcuteProcess.waitFor();

			LOGGER.info("Test Batch file execution done::::::");

			restExcuteProcess.exitValue();

			file.delete();
		} catch(Exception e){
			LOGGER.error("Exception occured while running the Resr API test::::"+e.getMessage());
		}

		System.out.println("After rest api test run succesfully deleted the batch file::::::::");

		return new ResponseEntity<Void>( HttpStatus.OK );
	}

}
