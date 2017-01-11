package com.ness.automation.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ness.automation.model.User;
import com.ness.automation.repository.UserRepository;

@RestController
//@PreAuthorize("hasAuthority('USER')")
@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
@RequestMapping("/rest/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRestController {

	private static final Logger LOGGER = Logger.getLogger(UserRestController.class);

	@Autowired
	UserRepository aUserRepository;
	
	@Value("${user.image.upload}")
	private String userImageUploadPath;

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() throws Exception {
		FileOutputStream fos = null;		
		byte[] bAvatar = null;
		List<User> allUsers = (List<User>) aUserRepository.findAll();
		for(User aUser:allUsers){
			try {
				if(aUser.getUserImage() != null){
					bAvatar = aUser.getUserImage();
					fos = new FileOutputStream("C:/Users/p5103974.NESS/Pictures/" + "sample" + aUser.getId() + ".png");
					fos.write(bAvatar);
					fos.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}    
		}
		if(allUsers.isEmpty()){
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
	}

	/*@RequestMapping(value = "/createUser/{fileToUpload}", consumes = MediaType.APPLICATION_JSON, method = RequestMethod.POST)
	public ResponseEntity<List<User>> createUser(@RequestBody User aUser, @PathVariable File  fileToUpload) throws Exception {*/
	@RequestMapping(value = "/createUser", consumes = MediaType.APPLICATION_JSON, method = RequestMethod.POST)
	public ResponseEntity<List<User>> createUser(@RequestBody User aUser) throws Exception {	
	/*public ResponseEntity<List<User>> createUser(@RequestParam(value="file", required=false) MultipartFile file,
            										@RequestParam(value="data") User aUser) throws Exception {*/
		
		File imageToSave = new File(userImageUploadPath);

		LOGGER.info("Image to be saved::::"+imageToSave);

		byte[] bFile = new byte[(int) imageToSave.length()];

		/*String passwordToHash = aUser.getPasswd();

		String generatedPassword = null;*/

		try {
			FileInputStream fileInputStream = new FileInputStream(imageToSave);
			fileInputStream.read(bFile);
			fileInputStream.close();

			// Create MessageDigest instance for MD5
			/*MessageDigest md = MessageDigest.getInstance("SHA");
			//Add password bytes to digest
			md.update(passwordToHash.getBytes("UTF-8"));

			byte raw[] = md.digest(); 
			
			String hash = (new BASE64Encoder()).encode(raw); 
			
			generatedPassword = hash;*/

			/*//Get the hash's bytes 
			byte[] bytes = md.digest();
			//This bytes[] has bytes in decimal format;
			//Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			//Get complete hashed password in hex format
			generatedPassword = sb.toString();*/
		} catch (Exception ex ) {
			LOGGER.error("Exception occured while saving User::::" +ex.getMessage());
		}  		
		LOGGER.info("Byte array to save::::"+bFile);
		
		aUser.setUserImage(bFile);		
		
		aUserRepository.save(aUser);  
		
		LOGGER.info("User created succesfully:::");
		return getAllUsers();
	}

	@RequestMapping(value = "/updateUser", consumes = MediaType.APPLICATION_JSON, method = RequestMethod.PUT)
	public ResponseEntity<List<User>> updateUser(@RequestBody User aUser) throws Exception {
		LOGGER.info("Under update user First Name::::" + aUser.getUsername() + "Last name::::" + aUser.getLastName() + "designation::::" +aUser.getDesignation());
		if(aUser != null){
			aUserRepository.save(aUser); 
			LOGGER.info("Updated the User in the model::::");			         
		}
		return getAllUsers();		
	}

	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<List<User>> deleteContact(@PathVariable long userId) {
		LOGGER.info("Delete USer Id:" + userId);
		aUserRepository.delete(userId);		
		List<User> allUsers =  (List<User>) aUserRepository.findAll();
		return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);	
	}
}
