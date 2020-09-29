package com.hackerthon.main;

import com.hackerthon.common.UtilTransform;
import com.hackerthon.service.GetEmpService;

public class ExecuteMain {

	/**
	 * @param args
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
