<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>New Real Estate Office</title>
<style>
.details
{
background-color:#3399FF;
color:#FFFFFF;
width: 100%;
font-size:14px;
text-decoration:blink;
border-style: 1px solid;
border-width:1px;
padding: 10px;
margin: 0;
}
div.static
{
text-align: center;
position: static;
border: 20px solid blue;
color:white;
background-color:blue;
text-decoration: underline;
}
#newSignupSubmit
{
		position:relative;
		right:-200px;
		top:0px;
}
</style>
</head>

<body>
<form:form  action="" commandName="" >
<div class="static">New Real Estate Office</div><br /><br /><br />
<table>
  <tr>
    <td width="40%" height="30"><b>Request No</b> </td>
    <td colspan="2"><form:input type="text" path="requestNo" /></td>
    </tr>
  <tr>
    <td height="31"><b>Applicant Details</b></td>
    <td width="30%"><form:input type="text" path="applicationDetails" /></td>
    <td width="28%"><form:input type="button" path="details" value="moreinfo" /></td>
  </tr>
</table><br /><br />
<p class="details">Request Details</p><br />
Manading Director:<br /><br />
Address:<br /><br />
<p class="details">Actions</p>
<table>
  <tr>
    <td width="5%">&nbsp;</td>
    <td>remarks</td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td><form:textarea path="comment" rows="7" cols="80"></form:textarea></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="5%">&nbsp;</td>
    <td>Trade Licence</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><form:input type="file" path="file" /></td>
  </tr>
  <tr>
    <td height="105">&nbsp;</td>
    <td id="newSignUpSubmit"><input type="submit" value="submit" /></td>
  </tr>
</table>
</form:form>
</body>
</html>
