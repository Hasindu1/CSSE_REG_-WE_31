package com.hackerthon.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.AbstractExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.hackerthon.common.UtilTransform;
import com.hackerthon.service.GetEmpService;

public class ExecuteMain {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {

		GetEmpService employeeService = GetEmpService.getInstance();
		try {
			UtilTransform.requestTransform();
			employeeService.employeesFromXml();
			employeeService.employeeTableCreate();
			employeeService.employeesAdd();
//			employeeService.eMPLOYEEGETBYID("EMP10004");
//			employeeService.EMPLOYEEDELETE("EMP10001");
			employeeService.employeeDisplay();
		} catch (Exception e) {
		} 
	}

}
