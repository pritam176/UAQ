<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<table width="636">
  <tr>
    <td width="26%" height="60"><b>Managing Director Name</b></td>
    <td width="74%"><form:input type="text" path="managingDirectorName"  maxlength="100"/></td>
  </tr>
  <tr>
    <td height="31"><b>Address</b></td>
    <td><form:input type="text" path="address"  maxlength="100" /></td>
  </tr>
</table><br>
<table width="49%">
  <tr>
    <td height="43" colspan="3"><b>Attachements</b></td>
  </tr>
  <tr>
    <td width="8%" height="30">&nbsp;</td>
    <td width="36%">Police Clearance Certificate </td>
    <td width="56%"><form:input type="file" path="file[0]"  accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword"/></td>
  </tr>
  <tr>
    <td height="29">&nbsp;</td>
    <td>Personal Picture </td>
    <td><label for="label"></label><form:input type="file" path="file[1]"  accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2" rowspan="2" id="newSignupSubmit"><input type="submit" value="submit" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>

</form:form>
</body>
</html>