package com.circlesLife.Utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Utils 
{
	static String testDataFilePath = System.getProperty("user.dir")+"/TestData/";
	
	//This Method is used for Parse through XML Nodes and return Test Data
	public static String getTestData(String testDataValueInXML)
	{
		try 
		{
			String locatorName = testDataValueInXML.split("\\.")[1];
			String pageName = testDataValueInXML.split("\\.")[0];
			String locatorValue = "";
			boolean isLocatorPresent = false;
			
			Document xmlDocument = readXML(testDataFilePath + pageName + ".xml");

			if (xmlDocument != null) 
			{
				NodeList locatorNodes = xmlDocument.getElementsByTagName("data");

				for (int nodeCounter = 0; nodeCounter < locatorNodes.getLength(); nodeCounter++) 
				{
					Element object = (Element) locatorNodes.item(nodeCounter);
					if (object.getAttribute("name").equalsIgnoreCase(locatorName)) 
					{
						locatorValue = object.getElementsByTagName("value").item(0).getTextContent();
						isLocatorPresent = true;						
						break;
					}
				}
			}
			if (isLocatorPresent)
				return locatorValue;
			else
				return "";			
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return null;
		}
	}
	
	//This method will return data in XML document
	public static Document readXML(String filePath) 
	{
		try 
		{
			Document xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(filePath));
			xmlDocument.getDocumentElement().normalize();
			return xmlDocument;
		} 
		catch (Exception e) 
		{
			return null;
		}
	}
}
