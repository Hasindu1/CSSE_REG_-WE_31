package com.hackerthon.common;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;

import com.hackerthon.config.GlobalConstant;

import org.w3c.dom.Element;

public class UtilQuery extends UtilConfig {
	/*
	 * this method read EmployeeQuery.xml file and 
	 * retrieve the query by query id
	 */
	public static String query(String id) throws Exception {
		NodeList nodeList;
		Element element = null;
		nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new File(GlobalConstant.CONFIG_EMP_QUERY_XML))
				.getElementsByTagName("query");
		for (int x = 0; x < nodeList.getLength(); x++) {
			element = (Element) nodeList.item(x);
			if (element.getAttribute("id").equals(id))
				break;
		}
		return element.getTextContent().trim();
	}
}
