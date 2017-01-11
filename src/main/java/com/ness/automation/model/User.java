package com.ness.automation.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users_automation")
public class User implements Serializable {

    private static final long serialVersionUID = -2359527020775651362L;
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "username", length =35)
    private String username;
    @Column(name = "first_name", nullable = false, length =35)
    private String firstName;
    @Column(name = "last_name", nullable = false, length =35)
    private String lastName;
    @Column(name = "designation", nullable = false, length =35)
    private String designation;
    @Column(name = "email", nullable = false, length =35)
    private String userEmail;
    @Column(name = "passwd", nullable = false, length =35)
    private String passwd;
    @Column(name = "permissions", nullable = false, length =35)
    private String permissions;
    
    @Column(name = "location", nullable = false, length =35)
    private String location;
    
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Basic(fetch=FetchType.LAZY)
    @Lob
    @Column(name = "user_image")
    private byte[] userImage;
    //private Blob  userImage;
   
   /* @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Contact> contacts;*/


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

   /* public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }*/

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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public byte[] getUserImage() {
		return userImage;
	}

	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}

	/*public java.sql.Blob getUserImage() {
		return userImage;
	}

	public void setUserImage(java.sql.Blob userImage) {
		this.userImage = userImage;
	}*/	
    
}