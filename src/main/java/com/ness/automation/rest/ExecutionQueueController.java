package com.ness.automation.rest;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ness.automation.model.TestExecutionQueue;
import com.ness.automation.repository.TestExecutionQueueRepository;

@RestController
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
@RequestMapping("/rest/executionqueue")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExecutionQueueController {

	private static final Logger LOGGER = Logger.getLogger(ExecutionQueueController.class);

	@Autowired
	TestExecutionQueueRepository aTestExecutionQueueRepository;
	
	@Value("${pega.test.source}")
	private String pegaSourceLocation;

	@RequestMapping(value = "/populatequeue", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Void> runTestNGExecutionQueue() {

		LOGGER.info("Inside runTestNGExecutionQueue::::");

		/*TestExecutionQueue aTestExecutionQueue = new TestExecutionQueue();

		aTestExecutionQueue.setTestCaseRunId(new Integer(1));
		aTestExecutionQueue.setTestCaseClass("com.nessq.pega.login.LoginTest14");
		aTestExecutionQueue.setTestCaseMethod("inValidLogin");
		aTestExecutionQueue.setTestCasePriority("Priority2");
		aTestExecutionQueue.setTestCaseFeature("login,homepage,functest1");
		aTestExecutionQueue.setTestCaseCreatedBy("P5103974");
		aTestExecutionQueue.setTestCaseLastModifiedBy(new Date());
		aTestExecutionQueue.setTestCaseManualEffort("04:15");
		aTestExecutionQueue.setTestCaseGroups("group1,group2,group3");
		aTestExecutionQueue.setTestCaseDependonGroups("group1");
		aTestExecutionQueue.setTestCaseDependonMethods("inValidLogin");
		//aTestExecutionQueue.setTestCaseCreatedDate(new Date());		

		aTestExecutionQueueRepository.save(aTestExecutionQueue);*/		
		
			FileOutputStream fos = null;

			File file;

			LOGGER.info("Pega source Location::::"+pegaSourceLocation);
			
			try {

				String path = System.getProperty("user.dir");

				LOGGER.info("Current Working Directory: " + path);

				file = new File(path + "/" + "pega" + "-" + "dev" + "process"+".bat");

				fos = new FileOutputStream(file);

				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				DataOutputStream dos=new DataOutputStream(fos); 

				dos.writeBytes("cd "+pegaSourceLocation);

				dos.writeBytes("\n");

				dos.writeBytes("call ");
				dos.writeBytes("mvn ");
				dos.writeBytes("surefire-report:report ");
				dos.writeBytes("-Dtest=NessQSetup#uiSetUp ");
				dos.writeBytes("-Dtest.env=");
				dos.writeBytes("pega");
				dos.writeBytes(".");
				dos.writeBytes("dev");
				dos.writeBytes(" ");
				dos.writeBytes("-DrunId=");
				dos.writeBytes("1");
				dos.writeBytes("\n");
				dos.writeBytes("EXIT");
								
				fos.flush();
				fos.close();		
				
				Process p = Runtime.getRuntime().exec("cmd /c start/wait "+ "pega" + "-" + "dev" + "process"+".bat");
				
				LOGGER.info("Waiting for batch file execution::::::::::::");

				int exitValue = p.waitFor();

				LOGGER.info("Wrote the batch file to the specified folde:::" +exitValue);
								
				p.destroy();

				file.delete();				

			} catch(Exception e){
				LOGGER.error("Exception occured in generating batch file::::"+e.getMessage());
			}

			return new ResponseEntity<Void>( HttpStatus.OK );
		}


	@RequestMapping(value = "/getPriorities", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getAllTestCasePriorities() throws Exception {
		List<String> allPrioritiesList = new ArrayList<String>();
		List<TestExecutionQueue> allQueues = listAllExecutionQueues();
		if(allQueues.size() > 0){
			for(TestExecutionQueue aTestExecutionQueue:allQueues){
				if(aTestExecutionQueue.getTestCasePriority() != null && !allPrioritiesList.contains(aTestExecutionQueue.getTestCasePriority())){
					allPrioritiesList.add(aTestExecutionQueue.getTestCasePriority());		
				}
			}
			allPrioritiesList.removeAll(Arrays.asList("", null));
			LOGGER.info("Testcase priorities::::"+allPrioritiesList.toString());
		}		
		if(allPrioritiesList.isEmpty()){
			return new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<String>>(allPrioritiesList, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllQueueCustomList", method = RequestMethod.GET)
	public ResponseEntity<List<TestExecutionQueue>> getAllQueueCustomList() throws Exception {
		List<TestExecutionQueue> allCustomQueuesList = listAllExecutionQueues();
		if(allCustomQueuesList.isEmpty()){
			return new ResponseEntity<List<TestExecutionQueue>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TestExecutionQueue>>(allCustomQueuesList, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllQueueFeatureList", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getAllQueueFeatureList() throws Exception {
		List<String> finalFeatureList = new ArrayList<String>();
		List<TestExecutionQueue> allCustomQueueFeatureList = listAllExecutionQueues();
		if(allCustomQueueFeatureList.size() > 0){
			for(TestExecutionQueue aTestExecutionQueue:allCustomQueueFeatureList){				
				List<String> allFeaturesList = Arrays.asList(aTestExecutionQueue.getTestCaseFeature().split("\\s*,\\s*"));
				finalFeatureList.addAll(allFeaturesList);
			}			
		}
		Set<String> duplicateRemovedSet = new HashSet<String>(finalFeatureList);
		finalFeatureList.clear();
		finalFeatureList.addAll(duplicateRemovedSet);

		LOGGER.info("Feature details with removed duplicates : "+finalFeatureList);
		
		finalFeatureList.removeAll(Arrays.asList("", null));
		
		LOGGER.info("Feature details with removed null values : "+finalFeatureList);

		if(finalFeatureList.isEmpty()){
			return new ResponseEntity<List<String>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<String>>(finalFeatureList, HttpStatus.OK);
	}

	private List<TestExecutionQueue> listAllExecutionQueues(){
		List<TestExecutionQueue> allQueuesList = (List<TestExecutionQueue>) aTestExecutionQueueRepository.findAll();
		return allQueuesList;
	}

	//@RequestMapping(value = "/saveTestNgXml/{priorities}/{fileName}/{allCheckBox}/{testExecutionQueues}", method = RequestMethod.POST)
	@RequestMapping(value = "/saveTestNgXml/{fileName}/{allCheckBox}", method = RequestMethod.POST)
	//@RequestMapping(value = "/saveTestNgXml/{fileName}/{allCheckBox}/{priorities}/{testExecutionQueues}/{priorities}", method = RequestMethod.POST)
	/*public ResponseEntity<Void> saveTestNgXml(@PathVariable  String[] priorities,@PathVariable String fileName,
											  @PathVariable String allCheckBox, @PathVariable List<TestExecutionQueue> testExecutionQueues) {	*/
	public ResponseEntity<Void> saveTestNgXml(@PathVariable String fileName, @PathVariable String allCheckBox) {		
    
		return new ResponseEntity<Void>( HttpStatus.OK );
	}
}
