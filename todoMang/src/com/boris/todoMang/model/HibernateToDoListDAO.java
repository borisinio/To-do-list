package com.boris.todoMang.model;

//Boris Ifraimov - 316711746

import java.util.ArrayList;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import com.boris.todoMang.model.IToDoListDAO;
import com.boris.todoMang.model.Item;
import com.boris.todoMang.model.ToDoListDAOException;
import com.boris.todoMang.model.User;



public class HibernateToDoListDAO implements IToDoListDAO {
	
	
	   /**
     * the singleton instance of this class
     */
	
	private static final HibernateToDoListDAO INSTANCE = new HibernateToDoListDAO();
	private static SessionFactory factory;
	
	
	
	   private HibernateToDoListDAO() {
		   
		   factory =  new AnnotationConfiguration().configure().buildSessionFactory();
		   
	   };
	   

		public static HibernateToDoListDAO getInstance() {
			// TODO Auto-generated method stub
		return INSTANCE;
		}


	   
	   
       @Override
	   public List<Item> getItems(String userEmail) throws ToDoListDAOException {//get items list by user email
    	   
			Session session = factory.openSession();
			try {
				Query query = session.createQuery("from Item i WHERE i.email = '"+ userEmail +"'");
				List queryList = query.list();
				System.out.println("from dao" + queryList);
				if(queryList != null && queryList.isEmpty())
					return (List<Item>)queryList;
				else
					return (List<Item>)queryList;
			}
			catch ( HibernateException e ) {
				throw new ToDoListDAOException("Unable to get task list from the database");
			} finally {
				if (session != null)
					session.close(); 
			} 
		}


	
       
		@Override
		public void addItem(Item item) throws ToDoListDAOException { //ass item to list
			
			Session session = factory.openSession();
			try {
				session.beginTransaction();
				session.save(item);
				session.getTransaction().commit();
			}
			catch ( HibernateException e ) {
				if ( session.getTransaction() != null )
					session.getTransaction().rollback();
			} finally {
				if (session != null)
					session.close(); 
			} 
		}


		@Override
		public boolean deleteItem(Item item) throws ToDoListDAOException { //delete item from list of the current user
			Session session = factory.openSession();
			try {
				session.beginTransaction();
				session.delete(item);
				session.getTransaction().commit();
				return true;
			}
			catch ( HibernateException e ) {
				if ( session.getTransaction() != null )
					session.getTransaction().rollback();
			} finally {
				if (session != null)
					session.close(); 
			} 
			return false;
		}

		
		@Override
		public boolean updateItem(Item item) throws ToDoListDAOException { 
		
			Session session = factory.openSession();
			try {
				session.beginTransaction();
				session.saveOrUpdate(item);
				session.getTransaction().commit();
				return true;
			}
			catch ( HibernateException e ) {
				if ( session.getTransaction() != null )
					session.getTransaction().rollback();
			} finally {
				if (session != null)
					session.close(); 
			}
			return false; 
		}
		
		


		@Override
		public User getUser(String email) throws ToDoListDAOException { //return user 
			Session session = factory.openSession();
			try {
				session.beginTransaction();
				Query query = session.createQuery("from User u where u.userEmail='"+email+"'");
				session.getTransaction().commit();
				//List queryList = query.list();
				User user1 =  (User) query.uniqueResult();
				return user1;
			} finally {
				if (session != null)
					session.close();
			} 
		}
		
		@Override
		public void addUser(User user) throws ToDoListDAOException { //add new user to database
			Session session = factory.openSession();
			User generatedUser = null;
			try {
				session.beginTransaction();
				session.save(user);
				session.getTransaction().commit();
			}
			catch ( HibernateException e ) {
				if ( session.getTransaction() != null )
					session.getTransaction().rollback();
				throw new ToDoListDAOException(e.getMessage());
			} finally {
				if (session != null)
					session.close(); 
			}
		}

	
		
		@Override
		public boolean deleteUser(User user) throws ToDoListDAOException { //delete user from data base
			Session session = factory.openSession();
			try {
				session.beginTransaction();
				session.delete(user);
				session.getTransaction().commit();
				return true;
			}
			catch ( HibernateException e ) {
				if ( session.getTransaction() != null )
					session.getTransaction().rollback();
			} finally {
				if (session != null)
					session.close();
			} 
			return false;
		}
		
		@Override
		public List<User> getUsers() throws ToDoListDAOException { //return list of users from db
			Session session = factory.openSession();
			try {
				session.beginTransaction();
				Query query = session.createQuery("from User u");
				session.getTransaction().commit();
				List queryList = query.list();
				if(queryList != null && queryList.isEmpty())
					return null;
				else
					return (List<User>)queryList;
			} 
			catch ( Exception e ) {
				throw new ToDoListDAOException("cant get users list from database");
			}  finally {
				if (session != null)
					session.close();
			} 
		}
		
		@Override
		public User checkUserInfo(String useremail  ) { //check if user's exist by email
			
			Session session = factory.openSession();
			Transaction tr = session.beginTransaction();
			
		
	        Query query = session.createQuery("from User u where u.email='"+useremail+"'");
	        User user1 =  (User) query.uniqueResult();
			
	            if (user1 != null) {
	                System.out.println("User is allready exists!!!");
	                
	                return user1;
	            }
	        return null;
			

			}
		
		
		
		public Item getItemById(int itemId) throws ToDoListDAOException {
	        Session session = factory.openSession();
	        Item task = null;
	        try {
	            session.beginTransaction();
	            if ((session.get(Item.class, itemId) != null)) {
	                session.getTransaction().commit();
	                task = (Item) session.get(Item.class, itemId);
	            }
	        } catch (HibernateException exception) {
	            if (session.getTransaction() != null) {
	                session.getTransaction().rollback();
	                throw new ToDoListDAOException(exception.getMessage(), exception.getCause());
	            }
	        } finally {
	            session.close();
	        }
	        return task;
	    }
	    }
			
		
		
	
	


