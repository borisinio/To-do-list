package com.boris.todoMang.model;




public class User   {
	
	
	
	private int id ;
	
	private String email;
	
	private String userName;
	
	private String userPassword;
	
	
	

	public User() {}// default ctor
	
	/**
	 * 
	 * @param email
	 * @param password
	 */
	public User( String email , String password ) {
		this.email = email;
		this.userPassword =password;
	}
	
	/**
	 * 
	 * @param name
	 * @param email
	 * @param password
	 */
	public User(String name , String email , String password ) {
	this.userName = name ;
	this.email = email;
	this.userPassword = password;
	
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		
		return id;
	}
	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return userPassword;
	}
	/**
	 * 
	 * @param password
	 * @return
	 */
	public boolean setPassword(String password) {
		if(password == null || password.isEmpty()) //set the user password,can't be null or empty
			return false;
		this.userPassword = password;
		return true;
		
	}
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return userName;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean setName(String name) {
		if(name == null || name.isEmpty()) //name can't be empty or null
			return false;
		this.userName = name;
		return true;
	}

	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "User [ name=" + userName + ", password=" + userPassword +    "]";
	}
/**
 * 
 * @return
 */
	public String getEmail() {
		return email;
	}

	
	
	public void setEmail(String email) {
		this.email = email;
	}
	


}
	
	

