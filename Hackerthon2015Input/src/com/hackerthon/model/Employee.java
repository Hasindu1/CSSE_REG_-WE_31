package com.hackerthon.model;

import com.hackerthon.config.GlobalConstant;

public class Employee {

	public String empId;
	public String fullName;
	public String address;
	public String facultyName;
	public String department;
	public String designation;
	
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		
		return GlobalConstant.EMPID + empId + "\n" + GlobalConstant.FULL_NAME + fullName + "\n" + GlobalConstant.ADDRESS + address + "\n"
				+ GlobalConstant.FACULTY_NAME + facultyName + "\n" + GlobalConstant.DEPARTMENT + department + "\n" + GlobalConstant.DESIGNATION
				+ designation;
	}
}
