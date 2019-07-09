package com.boris.todoMang.model;


public class Item   {
	
	
	private int id;
	
	private String email;
	
	private String task;

	
	public Item() {}; // default c'tor.
	/**
	 * 
	 * @param task
	 * @param userEmail
	 */
	public Item( String task  , String userEmail ) {
		this.task = task ;
		this.email = userEmail;
		
	}
	
	/**
	 * 
	 * @param id
	 * @param task
	 * @param userEmail
	 */
	public Item(int id , String task  , String userEmail ) {
		this.task = task ;
		this.id = id ;
		this.email = userEmail;
		
	}
	
	

	
	/**
	 * 
	 * @param userEmail
	 */
	public void setEmail(String userEmail) {
		
		this.email = userEmail;
		
	
	}
	/**
	 * 
	 * @return
	 */
	public String getEmail () {
		
		return email;
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
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getTask() {
		return task;
	}


/**
 * 
 * @param task
 * @return
 */
	public boolean setTask(String task) {
		if(task == null || task.isEmpty()) //task can't be empty or null
			return false;
		this.task = task;
		return true;
	}

	/**
	 * just a print func
	 */
	@Override
	public String toString() {
		
		return "Item [idItem=" + id + ", whatToDo=" + task +
				 "]";
}
}
