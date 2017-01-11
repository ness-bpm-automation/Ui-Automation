package com.ness.automation.dto;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class UserModel {
    private String uuid;
    private String username;
    private String firstName;
    private String lastName;
    private String permissions;

    private static ObjectMapper mapper = new ObjectMapper(); //Jackson's JSON marshaller

    // the fromString is needed to convert json to object. This is needed for using this object as a PathParam in REST
    public static UserModel fromString(String jsonRepresentation) throws IOException {
        return mapper.readValue(jsonRepresentation, UserModel.class);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}    
}
