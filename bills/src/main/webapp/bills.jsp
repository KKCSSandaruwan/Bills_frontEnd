<%@page import="com.Bill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bills Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/bills.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Bills Management V10.1</h1>
<form id="formBill" name="formBill">
last Reading:
<input id="lastReading" name="lastReading" type="text"
class="form-control form-control-sm">
<br> present Reading:
<input id="presentReading" name="presentReading" type="text"
class="form-control form-control-sm">
<br> units:
<input id="units" name="units" type="text"
class="form-control form-control-sm">
<br> amount:
<input id="amount" name="amount" type="text"
class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save"
class="btn btn-primary">
<input type="hidden" id="hidBillIDSave"
name="hidBillIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br>
<div id="divBillsGrid">
<%

Bill billObj = new Bill();
out.print(billObj.readBills());
%>
</div>
</div> </div> </div>
</body>
</html>