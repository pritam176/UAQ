<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<form:form action="" commandName="">
	<div class="static">Issue To Whom it May Concern Certificate</div><br /><br /><br />
		<table width="52%">
  			<tr>
    			<td width="24%" height="38"><b>Addressed To</b></td>
    			<td width="32%"><form:select path="select" style="width:170px"><option value="SheikZayedHousingProgram">SheikZayedHousingProgram</option></form:select></td>
   			  <td width="44%"><form:input type="text" path="selectOthers"/></td>
			</tr>
  			<tr>
    			<td height="34"><b>Family Book Number</b></td>
    			<td colspan="2"><label for="label"></label><form:input type="text" path="familyBookNumber"/></td>
    		</tr>
  			<tr>
    			<td height="34"><b>Spouse ID Number</b></td>
    			<td colspan="2"><label for="label2"></label><form:input type="text" path="spouseIdNumber" /></td>
 			</tr>
  </table><br>
  	<table width="51%">
  			<tr>
    			<td height="35" colspan="2"><strong>Attachements</strong></td>
    		</tr>
  			<tr>
    			<td width="31%" height="39">Scan of Family Book<font size="1.5">(if addressed to sheik zaid housing programming)</font></td>
   			  <td width="69%"><form:input type="file" path="file[0]" accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword" /></td>
  			</tr>
  			<tr>
    			<td height="38">Spouse's Emirates ID<font size="1.5">(If the letter is directed to Zaid Programming</font></td>
    			<td><form:input type="file" path="file[1]"  accept="image/jpg, image/JPG,image/JPEG, image/jpeg,image/jpeg,image/gif,image/png, application/pdf,image/x-eps, application/msword"/></td>
    		</tr>
  </table><br><br>
  <p id="newSignUpSubmit"><input type="submit" value="submit" /></p>

</form:form>
</body>
</html>