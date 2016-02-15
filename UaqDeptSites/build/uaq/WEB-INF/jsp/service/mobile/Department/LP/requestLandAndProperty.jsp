
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
 <!DOCTYPE html>
<html lang="en">
<meta charset="utf-8"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Land and Property Valuation-Review</title>
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
		right:-200px;
		top:0px;
}
</style>
</head>
<body>
 <form:form action="a.jsp" commandName=""> 
<div class="static"><b>Land And Property Valuation</b></div>
	<p><b>Please indicate your position</b></p>
	   <form:radiobutton path="heir" value="owner"/>Owner<br />
	   <form:radiobutton path="heir"  value="heir" checked/>Heir<br /><br>
<table width="69%">
  <tr>
    <td width="31%" height="35"><strong>Land Status</strong> </td>
    <td width="9%">&nbsp;</td>
    <td width="25%"><strong>Land Type</strong> </td>
    <td width="35%">&nbsp;</td>
  </tr>
  <tr>
    <td height="35"><form:select path="landStatus" style="width:170px"></form:select></td>
    <td>&nbsp;</td>
    <td height="40"><form:select path="landType" style="width:170px"></form:select></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="40"><strong>Site Plan Document Number</strong><font size="1.5">(required only if the land is owned by applicant)</font></td>
    <td colspan="3" height="40"><form:input type="text" path="documentNumber" /></td>
    </tr>
</table>
<p><b>Location(Choose one of the three options below)</b></p>
<table>
  <tr>
   			 <td height="34" colspan="5"><form:radiobutton path="sector"  value="sector" />Sector</td>
  </tr>
  <tr>
    		<td height="28">&nbsp;</td>
    		<td>Sector</td>
   			 <td><form:select path="sectorSelect">
           		 <option value="">select sector</option>
      			</form:select></td>
   			 <td width="16%">Block</td>
    		<td width="48%"><form:input type="text" path="sectorBlock"  maxlength="20"/></td>
 </tr>
 <tr>
		    <td height="25">&nbsp;</td>
		    <td>Sub-Sector</td>
		    <td><form:input type="text" path="subsector"  maxlength="20"/></td>
		    <td>Plot Number</td>
		    <td><form:input type="text" path="sectorPlotNumber"  maxlength="20"/></td>
		 </tr>
 <tr>
    		<td height="34" colspan="5"><form:radiobutton path="sector"  value="area"/>Area</td>
  </tr>
  <tr>
		    <td height="28">&nbsp;</td>
		    <td>Area</td>
		    <td><form:select path="areaSelect">
		      	<option value="">select area</option>
		      	</form:select></td>
		    <td>Block</td>
		    <td><form:input type="text" path="areaBlock"  maxlength="20"/></td>
 </tr>
 <tr>
		    <td height="28">&nbsp;</td>
		    <td>Sub-Area</td>
		    <td><form:input type="text" path="subarea"  maxlength="20" /></td>
		    <td>Plot Number </td>
		    <td><form:input type="text" path="areaPlotNumber"  maxlength="20" /></td>
 </tr>
 <tr>
    		<td height="34" colspan="5"><b>Owner Details</b></td>
</tr>
<tr>
		    <td height="28">&nbsp;</td>
		    <td>Name</td>
		    <td><form:input type="text" path="name"  maxlength="100" /></td>
		    <td>ID Number</td>
		    <td><form:input type="text" path="idNumber"  maxlength="20" /></td>
 </tr>
<tr>
		    <td>&nbsp;</td>
		    <td>Nationality</td>
		    <td><form:select path="nationality">
		      		<option value="">select nationality</option>
		     	 </form:select> </td>
		    <td>Family Book Id</td>
		    <td><form:input type="text" path="familyBookId" maxlength="20"/></td>
  </tr>
  </table><br /><br />
 <table>
<tr>
    		<td height="47" colspan="3"><b>Attachements<b></td>
</tr>
  <tr>
		    <td width="4%" height="34">&nbsp;</td>
		    <td width="20%">Identity(ID or Passport)</td>
		    <td width="76%"><form:input type="file" path="file[0]"/></td>
 </tr>
<tr>
		    <td height="30">&nbsp;</td>
		    <td>Family Book</td>
		    <td><form:input type="file" path="file[1]" /></td>
</tr>
<tr>
		    <td height="31">&nbsp;</td>
		    <td>Ownership Certificate</td>
		    <td><form:input type="file" path="file[2]"  /></td>
</tr>
<tr>
		    <td height="30">&nbsp;</td>
		    <td>Site Plan Document</td>
		    <td><form:input type="file" path="file[3]"  /></td>
</tr>
  <tr>
		    <td height="32">&nbsp;</td>
		    <td>Owner's Death Certificate</td>
		    <td><form:input type="file" path="file[4]"  /></td>
</tr>
 <tr>
		    <td height="32">&nbsp;</td>
		    <td>Inheritance Inventory</td>
		    <td><form:input type="file" path="file[5]"  /></td>
 </tr>
 <tr>
		    <td height="30">&nbsp;</td>
		    <td>Heir(s) Authorization</td>
		    <td><form:input type="file" path="file[6]"  /></td>
 </tr>
 <tr>
		    <td height="32">&nbsp;</td>
		    <td>Court Evaluation Request Letter</td>
		    <td><form:input type="file" path="file[7]" /></td>
 </tr>
 <tr>
    <td colspan="3">&nbsp;</td>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="2" id="newSignUpSubmit"><input  type="submit" value="submit"/></td>
    </tr>
</table> 
</form:form>
</body>
</html>