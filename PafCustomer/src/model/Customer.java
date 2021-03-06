package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electricityborad?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertCustomer(String cName, String cAddress, String cNic, String cGen, String cEmail, String cCNo) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into customerservice(`cID`,`cName`,`cAddress`,`cNic`,`cGen`,`cEmail`,`cCNo`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cName);
			preparedStmt.setString(3, cAddress);
			preparedStmt.setString(4, cNic);
			preparedStmt.setString(5, cGen);
			preparedStmt.setString(6, cEmail);
			preparedStmt.setString(7, cCNo);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readCustomer() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>ID</th><th>Customer Name</th><th>Address</th><th>NIC</th><th>Gender</th><th>Email</th><th>Telephone Number</th></tr>";
			String query = "select * from customerservice";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String cID = Integer.toString(rs.getInt("cID"));
				String cName = rs.getString("cName");
				String cAddress = rs.getString("cAddress");
				String cNic = rs.getString("cNic");
				String cGen = rs.getString("cGen");
				String cEmail = rs.getString("cEmail");
				String cCNo = rs.getString("cCNo");

				// Add into the html table
				output += "<tr><td>" + cID + "</td>";
				output += "<td>" + cName + "</td>";
				output += "<td>" + cAddress + "</td>";
				output += "<td>" + cNic + "</td>";
				output += "<td>" + cGen + "</td>";
				output += "<td>" + cEmail + "</td>";
				output += "<td>" + cCNo + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateCustomer(String cID, String cName, String cAddress, String cNic, String cGen, String cEmail, String cCNo) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE customerservice SET cName=?,cAddress=?,cNic=?,cGen=?,cEmail=?,cCNo=?" + "WHERE cID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, cName);
			preparedStmt.setString(2, cAddress);
			preparedStmt.setString(3, cNic);
			preparedStmt.setString(4, cGen);
			preparedStmt.setString(5, cEmail);
			preparedStmt.setString(6, cCNo);
			preparedStmt.setInt(7, Integer.parseInt(cID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the customer.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteCustomer(String cID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from customerservice where cID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(cID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the customer.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
