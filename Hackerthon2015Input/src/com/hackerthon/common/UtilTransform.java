package com.hackerthon.common;

import javax.xml.xpath.XPathFactory;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.Document;

import com.hackerthon.config.GlobalConstant;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

public class UtilTransform extends UtilConfig {

	private static final ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();

	private static Map<String, String> map = null;

	public static void requestTransform() throws Exception {

		Source req = new StreamSource(new File(GlobalConstant.CONFIG_EMP_REQ_XML));
		Source mod = new StreamSource(new File(GlobalConstant.CONFIG_EMP_MODIFIED_XSL));
		Result res = new StreamResult(new File(GlobalConstant.CONFIG_EMP_RESPONSE_XML));
		TransformerFactory.newInstance().newTransformer(mod).transform(req, res);
	}

	public static ArrayList<Map<String, String>> xmlXPaths() throws Exception {

		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(GlobalConstant.CONFIG_EMP_RESPONSE_XML);
		XPath xPath = XPathFactory.newInstance().newXPath();
		int n = Integer.parseInt((String) xPath.compile("count(//Employees/Employee)").evaluate(doc, XPathConstants.STRING));
		for (int i = 1; i <= n; i++) {
			map = new HashMap<String, String>();
			map.put(GlobalConstant.X_PATH_EMPLOYEE_ID_KEY, (String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeID/text()")
					.evaluate(doc, XPathConstants.STRING));
			map.put(GlobalConstant.X_PATH_EMPLOYEE_NAME_KEY , (String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeFullName/text()")
					.evaluate(doc, XPathConstants.STRING));
			map.put(GlobalConstant.X_PATH_EMPLOYEE_ADDRESS_KEY,
					(String) xPath.compile("//Employees/Employee[" + i + "]/EmployeeFullAddress/text()").evaluate(doc,
							XPathConstants.STRING));
			map.put(GlobalConstant.X_PATH_FACULTY_NAME_KEY, (String) xPath.compile("//Employees/Employee[" + i + "]/FacultyName/text()")
					.evaluate(doc, XPathConstants.STRING));
			map.put(GlobalConstant.X_PATH_DEPARTMENT_KEY, (String) xPath.compile("//Employees/Employee[" + i + "]/Department/text()")
					.evaluate(doc, XPathConstants.STRING));
			map.put(GlobalConstant.X_PATH_DESIGNATION_KEY, (String) xPath.compile("//Employees/Employee[" + i + "]/Designation/text()")
					.evaluate(doc, XPathConstants.STRING));
			list.add(map);
		}
		return list;
	}
}
