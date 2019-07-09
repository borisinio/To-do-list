package com.boris.todoMang.controller;

import java.io.IOException;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import com.boris.todoMang.model.HibernateToDoListDAO;
import com.boris.todoMang.model.Item;
import com.boris.todoMang.model.ToDoListDAOException;
import com.boris.todoMang.model.User;

import sun.rmi.server.Dispatcher;

public class AppController {
	   /**
     * the singleton instance of this class
     */
	private HibernateToDoListDAO hiber =  HibernateToDoListDAO.getInstance();
	private RequestDispatcher dispatcher ;
	
	
	  /**
     * treatemnt of error page
     */

	public void errorPage(HttpServletRequest request , HttpServletResponse response , String error ) throws ServletException, IOException{
		System.out.println("errorpage method");
		String email = (String) request.getParameter("email");
		System.out.println(email);
		request.getSession().setAttribute("email", email);
		request.getSession().setAttribute("flag", error);
		RequestDispatcher dispatcher=null;
		dispatcher = request.getRequestDispatcher("/errorpage.jsp");
		dispatcher.forward(request, response);
		
       
	}
	
	public void registerUser(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher=null;
		dispatcher = request.getRequestDispatcher("/register.jsp");
		dispatcher.forward(request, response);

		}
	
	/**
	  
	 *treatment of register page
	 * @throws ServletException
	 * @throws IOException
	 * @throws ToDoListDAOException
	 */
	public void register(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException, ToDoListDAOException
	{		String error = null;
			final Logger logger = Logger.getLogger("controller"); 
		   	BasicConfigurator.configure(); 
			RequestDispatcher dispatcher=null;
			String username = request.getParameter("name");
			String email = request.getParameter("email"); 
			String password = request.getParameter("password");
			logger.info("the new user is" +username);
			System.out.println(username);
			System.out.println(password);
			HibernateToDoListDAO hiber = HibernateToDoListDAO.getInstance();
			User user1 = hiber.checkUserInfo(email);
			if(user1 == null && password.length()> 4)
			{	
			request.getSession().setAttribute("email", email);
			User user =new User(username,email ,password);
			hiber.addUser(user);
			List<Item> tasks = hiber.getItems(email);
			request.getSession().setAttribute("tasks", tasks);
			dispatcher = request.getRequestDispatcher("/tasklist.jsp");
			dispatcher.forward(request, response);
			return;
			}
			else if(password.length() <= 4) { //if password not long enough
				error = "password have to be at least 4 char's";
				System.out.println("password length testttt");
				errorPage(request,response, error);
				
			}
			else {//if the user email already exist
				error = "the user email already exist";
				String email1 = (String) request.getSession().getAttribute("email");
				errorPage(request,response, error);
			}

		}
	// move to login page	
	public void loginUser(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException, ToDoListDAOException {

	}
	
	
	/**
	 * treatment of login page 
	 * @throws ServletException
	 * @throws IOException
	 * @throws ToDoListDAOException
	 */
	public void login(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException, ToDoListDAOException {
		System.out.println("login page");
		String error = null;
		RequestDispatcher dispatcher=null;
		String username=request.getParameter("name");
		String email =request.getParameter("email");
		String password=request.getParameter("password");
		
		String email1 = (String) request.getSession().getAttribute("email");
		HibernateToDoListDAO hiber = HibernateToDoListDAO.getInstance();
		User user = hiber.checkUserInfo(email); //check if user exists
		
			if(user.getEmail().equals(email) && user.getPassword().equals(password)) //checks if email and password match
			{
				  request.getSession().setAttribute("email", email);
				  List<Item> tasks = hiber.getItems(email);
				  System.out.println("e:" + email); 
				  System.out.println("t:" + tasks);
				  request.getSession().setAttribute("email", email);
				  request.getSession().setAttribute("tasks", tasks);
				  dispatcher = request.getRequestDispatcher("/tasklist.jsp");
				  dispatcher.forward(request, response);
				  return;
			}
			else if(user.getEmail().equals(email) && user.getPassword().equals(password) == false)// this for the error page
			{ 
				
				error = " password not correct";
				errorPage(request,response, error);
			}
			else// this for the error page
			{
				error = " email not correct";
				errorPage(request,response, error);
			}
			
		}
	//logout from user
	public void logout (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("logout page");
		HttpSession session = request.getSession();
		if(!session.isNew()) //delete session 
		{
			session.invalidate();
			session=request.getSession();
		}
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}
	
	//users task list treatment
	public void tasklist(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException, ToDoListDAOException { 
		String email = request.getParameter("email");
		request.getSession().getAttribute("tasks");
		if (request.getParameter("deleteBtn") != null) //if user wants to delete task.
		   {
			String email1 = (String)request.getSession().getAttribute("email");
			
			Item task = hiber.getItemById(Integer.parseInt(request.getParameter("id")));
            hiber.deleteItem(task);
            List<Item> tasks = hiber.getItems(email1);
            request.getSession().setAttribute("tasks", tasks);
	        dispatcher = request.getRequestDispatcher("/tasklist.jsp");
	        dispatcher.forward(request, response);
            
		   }
		
		else if (request.getParameter("outBtn") != null)//logout from current user
		 {	
			 logout(request,response);
			 	 
		 }
		else if (request.getParameter("addBtn") != null)//add new task
		 {	
			 addtask(request,response);
			 	 
		 }
		else if (request.getParameter("editBtn") != null)// edit chosen task
		{
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("id", id);
			dispatcher = request.getRequestDispatcher("/editTask.jsp");
	        dispatcher.forward(request, response);
		}
	
	}
		
	/**
	 * add task treatment
	 * @throws ServletException
	 * @throws IOException
	 * @throws ToDoListDAOException
	 */
	public void addtask (HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException, ToDoListDAOException {
		String task = request.getParameter("task");
		String email = (String) request.getSession().getAttribute("email");
		Item item = new Item( task , email);
		hiber.addItem(item);
		List<Item> tasks = hiber.getItems(email);
	    System.out.println("e:" + email); // todo: make from session email
	    System.out.println("t:" + tasks);
	    request.getSession().setAttribute("tasks", tasks);
	        
	    dispatcher = request.getRequestDispatcher("/tasklist.jsp");
	    dispatcher.forward(request, response);
	     
	}
		

	/**
	 * edit task treatment
	 * @throws ServletException
	 * @throws IOException
	 * @throws ToDoListDAOException
	 */
	 public void editTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ToDoListDAOException{
		     
	      		if (request.getParameter("UpdateBtn") != null) {
	        	System.out.println("if in editTask");
	        	String email=(String)request.getSession().getAttribute("email");
	        	int id = Integer.parseInt(request.getParameter("id"));
	        	String task = request.getParameter("task");
	        	Item item = new Item(id,task,email);
	        	hiber.updateItem(item);
	        
	        	
	        	List<Item> tasks = hiber.getItems(email);
	        	request.getSession().setAttribute("tasks",tasks);
	        	dispatcher = request.getRequestDispatcher("/tasklist.jsp");
		        dispatcher.forward(request, response);
	        }	
	        
	        
	        	
	        }
	 }
	        

	      



	 
	 
