package com.hackerthon.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import com.hackerthon.common.UtilTransform;
import com.hackerthon.config.GlobalConstant;

import java.sql.ResultSet;
import java.sql.Statement;
import com.hackerthon.common.UtilQuery;
import com.hackerthon.model.Employee;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.AbstractExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.xpath.XPathExpressionException;


import com.hackerthon.common.UtilConfig;

public class GetEmpService extends UtilConfig {

	private final ArrayList<Employee> employeeList = new ArrayList<Employee>();
	
	public static final Logger log = Logger.getLogger(AbstractExecutorService.class.getName());
	
	private static GetEmpService singleInstance = null; 

	private static Connection conn;

	private static Statement statement;

	private PreparedStatement preparedStatement;

	private GetEmpService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
					prop.getProperty("password"));
		} catch (Exception e) {
		} 
	}
	
	// Singleton pattern
	public static GetEmpService getInstance() 
    { 
        if (singleInstance == null) 
        	singleInstance = new GetEmpService(); 
  
        return singleInstance; 
    } 

	public void employeesFromXml() {

		try {
			/*
			 * The method xmlXPaths() returns ArrayList that comprises 
			 * employee details as map elements. Read each map element and 
			 * assign to Employee object
			 */
			for (Map<String, String> empSet : UtilTransform.xmlXPaths()) {
				Employee emp = new Employee();
				emp.setEmpId(empSet.get(GlobalConstant.X_PATH_EMPLOYEE_ID_KEY));
				emp.setFullName(empSet.get(GlobalConstant.X_PATH_EMPLOYEE_NAME_KEY));
				emp.setAddress(empSet.get(GlobalConstant.X_PATH_EMPLOYEE_ADDRESS_KEY));
				emp.setFacultyName(empSet.get(GlobalConstant.X_PATH_FACULTY_NAME_KEY));
				emp.setDepartment(empSet.get(GlobalConstant.X_PATH_DEPARTMENT_KEY));
				emp.setDesignation(empSet.get(GlobalConstant.X_PATH_DESIGNATION_KEY));
				employeeList.add(emp);
				
				//Logger
				log.info(emp.toString() + "\n");
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	public void employeeTableCreate() {
		try {
			
			// create and drop statement 
			statement = conn.createStatement();
			statement.executeUpdate(UtilQuery.query(GlobalConstant.Q2));
			statement.executeUpdate(UtilQuery.query(GlobalConstant.Q1));
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	public void employeesAdd() {
		try {
			/*
			 * prepare statement to execute insert
			 * data to employee table
			 */
			preparedStatement = conn.prepareStatement(UtilQuery.query(GlobalConstant.Q3));
			conn.setAutoCommit(false);
			for(Employee emp : employeeList ){			
				preparedStatement.setString(GlobalConstant.ONE, emp.getEmpId());
				preparedStatement.setString(GlobalConstant.TWO, emp.getFullName());
				preparedStatement.setString(GlobalConstant.THREE, emp.getAddress());
				preparedStatement.setString(GlobalConstant.FOUR, emp.getFacultyName());
				preparedStatement.setString(GlobalConstant.FIVE, emp.getDepartment());
				preparedStatement.setString(GlobalConstant.SIX, emp.getDesignation());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			conn.commit();
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	public void employeeGetById(String eid) {

		Employee emp = new Employee();
		try {
			// get employee details by his id
			preparedStatement = conn.prepareStatement(UtilQuery.query(GlobalConstant.Q4));
			preparedStatement.setString(1, eid);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				emp.setEmpId(resultSet.getString(1));
				emp.setFullName(resultSet.getString(2));
				emp.setAddress(resultSet.getString(3));
				emp.setFacultyName(resultSet.getString(4));
				emp.setDepartment(resultSet.getString(5));
				emp.setDesignation(resultSet.getString(6));
			}
			ArrayList<Employee> empList = new ArrayList<Employee>();
			empList.add(emp);
			employeeOutput(empList);
		} catch (Exception ex) {
			log.log(Level.SEVERE, ex.getMessage());
		}
	}

	public void employeeDelete(String eid) {

		try {
			// prepare statement to delete a employee 
			preparedStatement = conn.prepareStatement(UtilQuery.query(GlobalConstant.Q6));
			preparedStatement.setString(1, eid);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	public void employeeDisplay() {

		ArrayList<Employee> empList = new ArrayList<Employee>();
		try {
			/*
			 * prepare statement to retrieve employee details
			 * and assign to ArrayList to display
			 */
			preparedStatement = conn.prepareStatement(UtilQuery.query(GlobalConstant.Q5));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee emp = new Employee();
				emp.setEmpId(resultSet.getString(1));
				emp.setFullName(resultSet.getString(2));
				emp.setAddress(resultSet.getString(3));
				emp.setFacultyName(resultSet.getString(4));
				emp.setDepartment(resultSet.getString(5));
				emp.setDesignation(resultSet.getString(6));
				empList.add(emp);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		employeeOutput(empList);
	}
	
	public void employeeOutput(ArrayList<Employee> l){
		
		System.out.println(GlobalConstant.EMPID + "\t\t" + GlobalConstant.ADDRESS + "\t\t" + "Address" + "\t\t" + GlobalConstant.FACULTY_NAME + "\t\t"
				+ GlobalConstant.DEPARTMENT+ "\t\t" + GlobalConstant.DESIGNATION + "\n");
		System.out
				.println("================================================================================================================");
		for(Employee e : l){
			System.out.println(e.getEmpId() + "\t" + e.getFullName() + "\t\t"
					+ e.getAddress() + "\t" + e.getFacultyName() + "\t" + e.getDepartment() + "\t"
					+ e.getDesignation() + "\n");
			System.out
			.println("----------------------------------------------------------------------------------------------------------------");
		}
		
	}
}
