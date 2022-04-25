package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Customer;

@Path("/Customer")
public class CustomerRegister {
	Customer CustomerObj = new Customer();

	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomer() {
		return CustomerObj.readCustomer();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(
	 @FormParam("cName") String cName,			
	 @FormParam("cAddress") String cAddress,
	 @FormParam("cNic") String cNic,
	 @FormParam("cGen") String cGen,
	 @FormParam("cEmail") String cEmail,
	 @FormParam("cCNo") String cCNo)
	{
	 String output = CustomerObj.insertCustomer(cName, cAddress, cNic, cGen, cEmail, cCNo);
	return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String customData)
	{
	//Convert the input string to a JSON object
	 JsonObject customerObject = new JsonParser().parse(customData).getAsJsonObject();
	//Read the values from the JSON object
	 String cID = customerObject.get("cID").getAsString();
	 String cName = customerObject.get("cName").getAsString();
	 String cAddress = customerObject.get("cAddress").getAsString();
	 String cNic = customerObject.get("cNic").getAsString();
	 String cGen = customerObject.get("cGen").getAsString();
	 String cEmail = customerObject.get("cEmail").getAsString();
	 String cCNo = customerObject.get("cCNo").getAsString();
	 String output = CustomerObj.updateCustomer(cID, cName, cAddress, cNic, cGen, cEmail, cCNo);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String customData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customData, "", Parser.xmlParser()); 

	//Read the value from the element <ID>
	 String cID = doc.select("cID").text();
	 String output = CustomerObj.deleteCustomer(cID);
	return output;
	}
	
}
