package com.ness.automation.rest;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ness.automation.mapper.TestNgModelMapper;
import com.ness.automation.model.TestCasePriority;
import com.ness.automation.model.TestSessionQueue;
import com.ness.automation.repository.TestApplicationEnvironmentRepository;
import com.ness.automation.repository.TestCasePriorityRepository;
import com.ness.automation.repository.TestSessionQueueRepository;
import com.ness.automation.utils.AutomationConstants;
import com.ness.automation.utils.PreemptiveAuth;

@RestController
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
@RequestMapping("/rest/execution")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExecutionManagerController {

	private static final Logger LOGGER = Logger.getLogger(ExecutionManagerController.class);

	@Autowired
	TestCasePriorityRepository aTestCasePriorityRepository;

	@Autowired
	TestSessionQueueRepository aTestSessionQueueRepository;

	@Autowired
	TestApplicationEnvironmentRepository aTestApplicationEnvironmentRepository;

	@Value("${pega.test.source}")
	private String pegaSourceLocation;

	@Value("${rest.test.source}")
	private String restTestLocation;

	@Value("${jenkins.url}")
	private String jenkinsUrl;

	@Value("${jenkins.username}")
	private String jenkinsUserName;

	@Value("${jenkins.password}")
	private String jenkinsPassword;

	@Value("${jenkis.build.job.base.url}")
	private String jenkinsBuildJobUrl;

	@Value("${jenkins.job.name}")
	private String jenkinsJobName;

	@RequestMapping(value = "/getAllTestCasePriorities", method = RequestMethod.GET)
	public ResponseEntity<List<TestCasePriority>> getAllTestCasePriorities() throws Exception {
		List<TestCasePriority> allPriorities = (List<TestCasePriority>) aTestCasePriorityRepository.findAll();
		LOGGER.info("test Case priorities size::::"+allPriorities.size());
		if(allPriorities.isEmpty()){
			return new ResponseEntity<List<TestCasePriority>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<TestCasePriority>>(allPriorities, HttpStatus.OK);
	}

	@RequestMapping(value = "/getLatestSessionQueue", method = RequestMethod.GET)
	public ResponseEntity<TestSessionQueue> getLatestSessionQueue() throws Exception {
		TestSessionQueue aTestSessionQueue = aTestSessionQueueRepository.findLatestSessionQueue();
		if(aTestSessionQueue == null){			
			return new ResponseEntity<TestSessionQueue>(HttpStatus.NO_CONTENT);
		}		
		return new ResponseEntity<TestSessionQueue>(aTestSessionQueue, HttpStatus.OK);
	}

	@RequestMapping(value = "/saveTestSessionQueue", method = RequestMethod.POST)
	public ResponseEntity<Void> insertTestSessionQueue(@RequestBody TestNgModelMapper aTestNgModelMapper) {

		LOGGER.info("insertTestSessionQueue() Requested Parameters::::"+aTestNgModelMapper.toString());

		FileOutputStream fos = null;

		File file;

		TestSessionQueue aTestSessionQueue = new TestSessionQueue();

		aTestSessionQueue.setTestApplication(aTestNgModelMapper.getTestNgTestApp());
		aTestSessionQueue.setTestEnvironment(aTestNgModelMapper.getTestNgTestEnvironment());
		aTestSessionQueue.setTestBrowserName(aTestNgModelMapper.getTestNgTestBrowser());
		aTestSessionQueue.setTestBuildNumber(aTestNgModelMapper.getTestNgBuildNumber());
		aTestSessionQueue.setTestReleaseName(aTestNgModelMapper.getTestNgReleaseNumber());
		aTestSessionQueue.setTestUserName(aTestNgModelMapper.getTestNgUserName());
		aTestSessionQueue.setTestExecutionStatus("Init");
		aTestSessionQueue.setTestExecutionType(aTestNgModelMapper.getTestNgExecutionType());

		TestSessionQueue savedSessionObject = aTestSessionQueueRepository.save(aTestSessionQueue);

		LOGGER.info("Test session queue save succesfully::::");

		LOGGER.info("Pega source Location from properties file::::"+ pegaSourceLocation);

		try {

			String path = System.getProperty("user.dir");

			file = new File(path + "/" + aTestNgModelMapper.getTestNgTestApp() + "-" + aTestNgModelMapper.getTestNgTestEnvironment() + "process"+".bat");

			fos = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			DataOutputStream dos=new DataOutputStream(fos); 

			dos.writeBytes("cd " + pegaSourceLocation);

			dos.writeBytes("\n");

			dos.writeBytes("call ");
			dos.writeBytes("mvn ");
			dos.writeBytes("surefire-report:report ");
			dos.writeBytes("-Dtest=NessQSetup#uiSetUp ");
			dos.writeBytes("-Dtest.env=");
			dos.writeBytes(savedSessionObject.getTestApplication());
			dos.writeBytes(".");
			dos.writeBytes(savedSessionObject.getTestEnvironment());
			dos.writeBytes(" ");
			dos.writeBytes("-DrunId=");
			dos.writeBytes(savedSessionObject.getId().toString());
			dos.writeBytes("\n");
			dos.writeBytes("EXIT");

			System.out.println("Wrote the batch file to the specified folder");

			fos.flush();
			fos.close();		

			Process p = Runtime.getRuntime().exec("cmd /c start/wait "+ savedSessionObject.getTestApplication() + "-" + savedSessionObject.getTestEnvironment() + "process"+".bat");

			LOGGER.info("Waiting for batch file execution::::::::::::");

			int exitValue = p.waitFor();

			LOGGER.info("Batch file execution done::::::" +exitValue);

			p.destroy();

			file.delete();

		} catch(Exception e){
			LOGGER.error("Exception occured in generating batch file::::"+e.getMessage());
		}
		return new ResponseEntity<Void>( HttpStatus.OK );
	}

	/*@RequestMapping(value = "/launchTestNgTest/{fileName}/{allCheckBox}", method = RequestMethod.POST)
	public ResponseEntity<Void> runTestNGTest(@PathVariable String fileName, @PathVariable String allCheckBox) {*/
	@RequestMapping(value = "/launchTestNgTest/{allCheckBox}", method = RequestMethod.POST)
	public ResponseEntity<Void> runTestNGTest(@PathVariable String allCheckBox) {

		LOGGER.info("Inside runTestNGTest()::::");

		FileOutputStream fos = null;

		File file= null;

		LOGGER.info("TestNg Requested Checkbox Value::::"+allCheckBox);
		try {

			String path = System.getProperty("user.dir");

			file = new File(path + "/" + AutomationConstants.TESTNG_LAUNCH_FILE_NAME +".bat");

			fos = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			DataOutputStream dos=new DataOutputStream(fos); 

			dos.writeBytes("cd " +pegaSourceLocation);

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

			fos.flush();
			fos.close();		

			Process testNgExcuteProcess = Runtime.getRuntime().exec("cmd /c start/wait " + AutomationConstants.TESTNG_LAUNCH_FILE_NAME +".bat");

			testNgExcuteProcess.waitFor();

			testNgExcuteProcess.exitValue();

			file.delete();
		} catch(Exception e){
			LOGGER.error("Exception occured while running the Testng test::::"+e.getMessage());
		}
		return new ResponseEntity<Void>( HttpStatus.OK );
	}

	@RequestMapping(value = "/launchTestNgJenkinsJob/{allCheckBox}", method = RequestMethod.POST)
	public ResponseEntity<Void> executeTestNgJenkinsJob(@PathVariable String allCheckBox) {

		LOGGER.info("Inside executeTestNgJenkinsJob()::::");

		// Create your httpclient
		DefaultHttpClient client = new DefaultHttpClient();

		// Then provide the right credentials
		client.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
				new UsernamePasswordCredentials(jenkinsUserName, jenkinsPassword));

		// Generate BASIC scheme object and stick it to the execution context
		BasicScheme basicAuth = new BasicScheme();

		BasicHttpContext context = new BasicHttpContext();

		context.setAttribute("preemptive-auth", basicAuth);

		// Add as the first (because of the zero) request interceptor
		// It will first intercept the request and preemptively initialize the authentication scheme if there is not
		client.addRequestInterceptor(new PreemptiveAuth(), 0);

		// You get request that will start the build
		//String buildJobUrl = "http://192.168.1.108:8080" + "/job/" + jobName + "/build?token=" + buildToken;

		String buildJobUrl = jenkinsBuildJobUrl + "/job/" + jenkinsJobName + "/build";

		LOGGER.info("Jenkins build job URL:::::::"+buildJobUrl);

		String lastBuildJobUrl = jenkinsBuildJobUrl + "/job/" + jenkinsJobName + "/lastBuild/buildTimestamp";

		HttpGet authenticateJenkins = new HttpGet(jenkinsUrl);

		HttpResponse connectionResponse = null;
		try {
			connectionResponse = client.execute(authenticateJenkins, context);

			EntityUtils.consumeQuietly(connectionResponse.getEntity());

			LOGGER.info("Connection response status code::::" + connectionResponse.getStatusLine().getStatusCode());

			if(connectionResponse.getStatusLine().getStatusCode() == 200){
				// Execute your request with the given context
				buildJobEntity(buildJobUrl, client, context);

				// getJenkinsJobDetails(jobName,client, context);
			}

		} catch (ClientProtocolException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}	
		return new ResponseEntity<Void>( HttpStatus.OK );

	}

	private HttpEntity buildJobEntity(String buildJobUrl, DefaultHttpClient client, BasicHttpContext context){

		HttpPost buildJob = new HttpPost(buildJobUrl);

		HttpResponse buildJobResponse = null;

		try {
			buildJobResponse = client.execute(buildJob, context);
		} catch (ClientProtocolException e) {
			System.out.println("Exception Occured:::"+e.getMessage());
		} catch (IOException e) {
			System.out.println("IO Exception Occured:::"+e.getMessage());
		}
		LOGGER.info("Jenkins build job URL response:::::::"+buildJobResponse);

		HttpEntity buildjobEntity = buildJobResponse.getEntity();

		LOGGER.info("Jenkins build job URL response entity:::::::"+buildjobEntity.toString());

		EntityUtils.consumeQuietly(buildjobEntity);

		return buildjobEntity;		
	}

}
