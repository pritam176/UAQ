<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Renew Real Estate Office</title>

<style>
div.static {
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
		right:-150px;
		top:0px;
}
</style>
</head>
<body>
<form:form action="a.jsp" commandName="">
<div class="static">Renew Real Estate Office</div><br /><br /><br />
<table width="57%">
  <tr>
    <td width="10%" height="30"><b>Managing Director Name</b></td>
    <td width="27%">salem</td>
	<td width="63%"><a href="">Edit</a></td>
 </tr>
 <tr>
    <td height="31"><b>Address</b></td>
    <td>----</td>
 <td width="63%"><a href="">Edit</a></td>
 </tr>
 <tr>
    <td height="31"><b>Phone Number</b></td>
    <td width="27%">09xx x  x</td>
    <td width="63%"><a href="">Edit</a></td>
 </tr>
</table>
<br>
<table width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td height="43" colspan="3"><b>Attachements</b></td>
  </tr>
  <tr>
    <td width="5%" height="30">&nbsp;</td>
    <td width="18%">Police Clearance Certificate</td>
    <td width="77%"><form:input type="file" path="file[0]" /></td>
  </tr>
  <tr>
    <td height="29">&nbsp;</td>
    <td>Personal Picture </td>
    <td><label for="label"></label><form:input type="file" path="file[1]"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2" rowspan="2" id="newSignUpSubmit"><input type="submit" value="submit" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>

</form:form>
</body>
</html>