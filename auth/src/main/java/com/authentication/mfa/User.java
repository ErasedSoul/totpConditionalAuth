package com.authentication.mfa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "user_info") 
public class User {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private String userName;
    private String password;
    private boolean active;
    private String roles;
    private String hash;
    
    
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getRoles() {
		return "USER";
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
    
}
