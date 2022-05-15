package com;
import java.sql.*;
public class Bill
{
private Connection connect()
{
Connection con = null;
try
{
Class.forName("com.mysql.jdbc.Driver");
con =
DriverManager.getConnection(
"jdbc:mysql://127.0.0.1:3306/project_1", "root", "");
}
catch (Exception e)
{
e.printStackTrace();
}
return con;
}
public String readBills()
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for reading.";
}
// Prepare the html table to be displayed
output = "<table border='1'><tr><th>last Reading</th><th>present Reading</th><th>units</th>"+ "<th>amount</th><th>Update</th><th>Remove</th></tr>";
String query = "select * from bills";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
String billID = Integer.toString(rs.getInt("billID"));
String lastReading = rs.getString("lastReading");
String presentReading = rs.getString("presentReading");
String units = Double.toString(rs.getDouble("units"));
String amount = rs.getString("amount");
// Add into the html table
output += "<tr><td><input id='hidBillIDUpdate'name='hidBillIDUpdate'type='hidden' value='" + billID+ "'>" + lastReading + "</td>";
output += "<td>" + presentReading + "</td>";
output += "<td>" + units + "</td>";
output += "<td>" + amount + "</td>";
// buttons
output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-billid='"
+ billID + "'>" + "</td></tr>";
}
con.close();
// Complete the html table
output += "</table>";
}
catch (Exception e)
{
output = "Error while reading the bills.";
System.err.println(e.getMessage());
}
return output;
}




public String insertBill(String lastReading, String presentReading,String units, String amount)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for inserting.";
}
// create a prepared statement
String query = " insert into bills(`billID`,`lastReading`,`presentReading`,`units`,`amount`) + values (?, ?, ?, ?, ?)";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, 0);
preparedStmt.setString(2, lastReading);
preparedStmt.setString(3, presentReading);
preparedStmt.setDouble(4, Double.parseDouble(units));
preparedStmt.setString(5, amount);
// execute the statement
preparedStmt.execute();
con.close();
String newBills = readBills();
output = "{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while inserting the bill.\"}";
System.err.println(e.getMessage());
}
return output;
}
public String updateBill(String ID, String lastReading, String presentReading,String units, String amount)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for updating.";
}
// create a prepared statement
String query = "UPDATE bills SET lastReading=?,presentReading=?,units=?,amount=? WHERE billID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setString(1, lastReading);
preparedStmt.setString(2, presentReading);
preparedStmt.setDouble(3, Double.parseDouble(units));
preparedStmt.setString(4, amount);
preparedStmt.setInt(5, Integer.parseInt(ID));
//execute the statement
preparedStmt.execute();
con.close();
String newBills = readBills();
output = "{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while updating the bill.\"}";
System.err.println(e.getMessage());
}
return output;
}
public String deleteBill(String billID)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for deleting.";
}
//create a prepared statement
String query = "delete from bills where billID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
//binding values
preparedStmt.setInt(1, Integer.parseInt(billID));
//execute the statement
preparedStmt.execute();
con.close();
String newBills = readBills();
output = "{\"status\":\"success\", \"data\": \"" + newBills + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while deleting the bill.\"}";
System.err.println(e.getMessage());
}
return output;
}
}
