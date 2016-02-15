
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%-- <%@ page import="net.tanesha.recaptcha.ReCaptcha"%>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory"%> --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>New Individual Registration Request</title>
</head>
<style type="text/css">
div.static 
{
		text-align: center;
		position: static;
		border: 20px solid blue;
		color:white;
		background-color:blue;
		text-decoration: underline;
}
.test
{
		color:#FF0000;
		font-size:14px;
}
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
.selectBox
{
		width:170px;
}
.newSignupSubmit
{
		position:relative;
		right:-590px;
		top:0px;
}
    
</style>
<body>
 <form:form action="process.jsp" method="post">
<p><div class="static">New Individual Registration Request</div></p>
<table width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td width="16%"height="40"><b>Country of Citizenship</b><label class="test">*</label></td>
    <td colspan="2"><form:select path="country" class="selectBox"><option value="">UAE</option>
    </form:select><img src="Help-icon.png" alt="symbol" height="20" width="20"></td>
    <td width="15%" height="40"><b>Country of Residency</b><label class="test">*</label></td>
    <td width="41%"><form:select path="countryResidency" style="width:170px"><option value="">UAE</option></form:select></td>
  </tr>
  <tr>
    <td height="40"><b>Mobile 1</b><label class="test">*</label></td>
    <td width="3%"><form:select path="mobile1" style="width:40px"><option value="">050</option></form:select></td>
    <td width="25%"><form:input type="text" path="mobile11" style="width:130px"/></td>
    <td height="40"><b>Mobile 2</b></td>
    <td><form:select path="mobile2" style="width:40px"><option value="">050</option></form:select>
      <form:input type="text" path="mobile22" style="width:130px" /></td>
  </tr>
  <tr>
    <td height="40"><b>LandLine</b></td>
    <td><form:select path="landLine" style="width:40px"><option value="">06</option></form:select>    </td>
    <td><form:input type="text" path="landLine1" style="width:130px"/></td>
    <td colspan="2">&nbsp;</td>
    </tr>
  <tr>
    <td height="40"><b>Email Address</b></td>
    <td colspan="4"><form:input type="text" path="emailAddress" style="width:170px"/></td>
    </tr>
  <tr>
    <td height="71" colspan="5"><p><div class="details">Credentials</div></p></td>
    </tr>
  <tr>
    <td height="40"><b>Username</b><label class="test">*</label></td>
    <td colspan="4"><form:input type="text" path="username" /></td>
    </tr>
  <tr>
    <td height="40"><b>Password</b><label class="test">*</label></td>
    <td colspan="2"><form:input type="text" path="password" /></td>
    <td>Confirm Password<label class="test">*</label> </td>
    <td><form:input type="text" path="confirmPassword" /><img src="Help-icon.png" alt="symbol" height="20" width="20"></td>
  </tr>
  <tr>
    <td height="45" colspan="5"><p><div class="details">ID Information</div></p></td>
    </tr>
  <tr>
    <td height="40"><b>Full Name</b>
       <font size="1">(as stated in the emirates ID)<label class="test">*</label></font></td>
    <td colspan="4"><form:input type="text" path="fullname" /><img src="Help-icon.png" alt="symbol" height="20" width="20"></td>
    </tr>
  <tr>
    <td height="40"><b>Emirates ID</b></td>
    <td colspan="2"><form:input type="text" path="emiratesId" /><img src="Help-icon.png" alt="symbol" height="20" width="20"></td>
    <td><b>Emirated ID Expiry Date</b><label class="test">*</label></td>
    <td><form:select path="emiratedIdExpiryDate" style="width:170px"><option value="">01/06/2015</option></form:select>    </td>
  </tr>
  <tr>
    <td height="40"><b>Emirate</b><label class="test">*</label></td>
    <td colspan="4"><form:select path="emirate" style="width:170px"><option value="">Umm Al Quwain</option></form:select>    </td>
    </tr>
  <tr>
    <td height="40"><b>DOB</b><label class="test">*</label></td>
    <td colspan="4"><form:select path="dob" style="width:170px"><option value="">01/06/2014</option></form:select><img src="Help-icon.png" alt="symbol" height="20" width="20"></td>
    </tr>
  <tr>
    <td height="40"><b>Emirate ID Front</b></td>
    <td colspan="2"><form:input type="file" path="file" /></td>
    <td><b>Emirate ID Back</b></td>
    <td><form:input type="file" path="file" /></td>
  </tr>
  <tr>
    <td colspan="5" height="45"><p><div class="details">Passport Information</div><p> </td>
    </tr>
  <tr>
    <td height="40"><b>Passport Number</b><label class="test">*</label></td>
    <td height="40" colspan="4"><form:input type="text" path="passportNumber" /></td>
    </tr>
  <tr>
    <td height="40"><b>Passport First </b>
      <label class="test">*</label></td>
    <td height="40" colspan="2"><form:input type="file" path="file"/></td>
    <td><b>Passport Residency Page</b> <label class="test">*</label></td>
    <td><form:input type="file" path="file"/></td>
  </tr>
</table><br/><br/>
         <%-- <Center>	<%
          //ReCaptcha c = ReCaptchaFactory.newReCaptcha("your_public_key", "your_private_key", false);
          ReCaptcha captcha = ReCaptchaFactory.newReCaptcha("6Lc3zAoTAAAAANKz34tUL_Z4bTXLR5AiRvkxkwgL", "6Lc3zAoTAAAAAJba8wJaf8MU2UZB_P8l7AHYXr4E", false);
          out.print(captcha.createRecaptchaHtml(null, null));
        %>
    </Center> --%>
   <p class="newSignupSubmit"> <form:input type="checkbox" path="newsletter"/><font size="1.5">Subscribe to receive letter</font></p>
         <p><input class="newSignupSubmit" type="submit" value="submit" /><p>
</form:form>
 		
    
</body>
</html>
    