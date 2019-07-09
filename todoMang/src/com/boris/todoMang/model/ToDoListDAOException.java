package com.boris.todoMang.model;

public class ToDoListDAOException  extends Exception {

		/**
		 * creates exception with message that explains this exception
		 */
		public ToDoListDAOException(String msg) {
			super(msg);
		}
		/**
		 *creates exception with a throwable parameter
		 */
		public ToDoListDAOException(String msg, Throwable throwable) {
			super(msg,throwable);
		}
	}

                  
