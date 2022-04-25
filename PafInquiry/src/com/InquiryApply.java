package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Inquiry;

@Path("/Inquiry")
public class InquiryApply {
	Inquiry InquiryObj = new Inquiry();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInquiry() {
		return InquiryObj.readInquiry();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertInquiry(
	 @FormParam("INname") String INname,			
	 @FormParam("INadderss") String INadderss,
	 @FormParam("INdate") String INdate,
	 @FormParam("INreason") String INreason)
	{
	 String output = InquiryObj.insertInquiry(INname, INadderss, INdate, INreason);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInquiry(String inquiryData)
	{
	//Convert the input string to a JSON object
	 JsonObject inquiryObject = new JsonParser().parse(inquiryData).getAsJsonObject();
	//Read the values from the JSON object
	 String INid = inquiryObject.get("INid").getAsString();
	 String INname = inquiryObject.get("INname").getAsString();
	 String INadderss = inquiryObject.get("INadderss").getAsString();
	 String INdate = inquiryObject.get("INdate").getAsString();
	 String INreason = inquiryObject.get("INreason").getAsString();
	 String output = InquiryObj.updateInquiry(INid, INname, INadderss, INdate, INreason);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInquiry(String inquiryData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String INid = doc.select("INid").text();
	 String output = InquiryObj.deleteInquiry(INid);
	return output;
	}
}
