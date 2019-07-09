package com.boris.todoMang.model;

import java.util.List;

import com.boris.todoMang.model.ToDoListDAOException;
import com.boris.todoMang.model.User;


public interface IToDoListDAO  {
/**
	 * get the list of all to-do items in the db
	 * return the list of all to-do items in the db
 * @throws ToDoListDAOException 
	 */
	public List<Item> getItems(String useremail) throws ToDoListDAOException ;
	/**
	 * add new item to the database
	 * @throws ToDoListDAOException 
	 */
	public void addItem(Item item) throws ToDoListDAOException;
	/**
	 * delete item
	 * return true if the item was deleted. false otherwise.
	 * @throws ToDoListDAOException 
	 */
	public boolean deleteItem(Item item) throws ToDoListDAOException;
	/**
	 * update item in the database
	 * return true if the update was successfully.
	 * @throws ToDoListDAOException 
	 */
	public boolean updateItem(Item item) throws ToDoListDAOException;
	/**
	 * 
	 * @param name
	 * @return
	 * @throws ToDoListDAOException
	 */
	
	
	public User getUser(String name) throws ToDoListDAOException;
	
	/**
	 * 
	 * @param user
	 * @return
	 * @throws ToDoListDAOException
	 */
		
	public boolean deleteUser(User user) throws ToDoListDAOException;
	
	/**
	 * 
	 * @param user
	 * @throws ToDoListDAOException
	 */
	
	public void addUser(User user) throws ToDoListDAOException;
	
	/**
	 * 
	 * @throws ToDoListDAOException
	 */
	public List<User> getUsers() throws ToDoListDAOException;
	
	/**
	 * checking if the user already exists
	 * @param useremail
	 * @return
	 */
	public User checkUserInfo(String useremail );
	
	/**
	 * 
	 * @param itemId
	 * @throws ToDoListDAOException
	 */
	public Item getItemById(int itemId) throws ToDoListDAOException;
	
	
} 