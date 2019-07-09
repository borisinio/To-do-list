package com.boris.todoMang.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class controller
 */
@WebServlet("/Servlet/*")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String pkg = "com.boris.todoMang.controller" ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String str = request.getPathInfo();
		String vec[] = str.split("/");
		String controller = vec[1];
		String action = vec[2];
		String contollerClassName = controller.substring(0,1).toUpperCase()+controller.substring(1)+"Controller";
		try {
			
			Class clazz = Class.forName(pkg + "." + contollerClassName);
			Object object = clazz.newInstance();
			Method method = clazz.getMethod(action, HttpServletRequest.class , HttpServletResponse.class);
			method.invoke(object , request , response);
			
			
			
			getServletContext().getRequestDispatcher("/"+action+".jsp").forward(request, response);
		}catch(ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e ) {
			e.printStackTrace();
		}
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}