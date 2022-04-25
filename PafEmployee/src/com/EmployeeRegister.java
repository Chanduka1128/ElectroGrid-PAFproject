package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Employee;

@Path("/Employee")
public class EmployeeRegister {
	Employee EmployeeObj = new Employee();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readEmployee() {
		return EmployeeObj.readEmployee();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmployee(
	 @FormParam("Name") String Name,			
	 @FormParam("Address") String Address,
	 @FormParam("Nic") String Nic,
	 @FormParam("PhoneNo") String PhoneNo)
	{
	 String output = EmployeeObj.insertEmployee(Name, Address, Nic, PhoneNo);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmployee(String employeeData)
	{
	//Convert the input string to a JSON object
	 JsonObject employeeObject = new JsonParser().parse(employeeData).getAsJsonObject();
	//Read the values from the JSON object
	 String eID = employeeObject.get("eID").getAsString();
	 String Name = employeeObject.get("Name").getAsString();
	 String Address = employeeObject.get("Address").getAsString();
	 String Nic = employeeObject.get("Nic").getAsString();
	 String PhoneNo = employeeObject.get("PhoneNo").getAsString();
	 String output = EmployeeObj.updateEmployee(eID, Name, Address, Nic, PhoneNo);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmployee(String employeeData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(employeeData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String eID = doc.select("eID").text();
	 String output = EmployeeObj.deleteEmployee(eID);
	return output;
	}
}
