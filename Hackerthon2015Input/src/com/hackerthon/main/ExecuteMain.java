package com.hackerthon.main;

import java.util.concurrent.AbstractExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.hackerthon.common.UtilTransform;
import com.hackerthon.service.EmpService;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Logger log = Logger.getLogger(AbstractExecutorService.class.getName());

		EmpService employeeService = EmpService.getInstance();
		try {
			UtilTransform.requestTransform();
			employeeService.employeesFromXml();
			employeeService.employeeTableCreate();
			employeeService.employeesAdd();
//			employeeService.eMPLOYEEGETBYID("EMP10004");
//			employeeService.EMPLOYEEDELETE("EMP10001");
			employeeService.employeeDisplay();
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}

	}

}
