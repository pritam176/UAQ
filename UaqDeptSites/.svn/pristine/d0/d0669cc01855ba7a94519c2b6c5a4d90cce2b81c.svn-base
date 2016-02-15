<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<style type="text/css">
.center {
    margin: auto;
    width: 100%;
   background-color: #0099CC;
    padding: 10px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<title>New PRO card</title>
<body>
	<center>
		<h1 class="center">
			<u>Lost Document Replacement</u>
		</h1>
	</center>
	<form:form method="POST" action="newprocardsave.html"
		commandName="newprocard">
		<br>
		<table>
			<tr>
				<td><label>Name of the PRO</label></td>
				<td><form:input type="text" path="proName" id="" /><font
					color="red"> <form:errors path="proName" />
				</font></td>
			</tr>
			<tr>
				<td><label>PRO ID Number</label></td>
				<td><form:input type="text" path="proIdNumber" /><font
					color="red"> <form:errors path="proIdNumber" /></font></td>
			</tr>
			<tr>
				<td><label>PRO ID Expiry Date</label></td>
				<td><form:input type="text" path="proIdExpdate" /><font
					color="red"> <form:errors path="proIdExpdate" /></font></td>
			</tr>
			<tr>
				<td><label>PRO Nationality</label></td>
				<td><form:select path="proNationality">
						<form:option value="">Select Country</form:option>
					</form:select> <font color="red"> <form:errors path="proNationality" /></font></td>
			</tr>
			<tr>
				<td><label><b>Attachments</b></label></td>
			</tr>
			<tr>
				<td><label path="proIdentity">Identity of the PRO (ID
						or Passport Copy)</label></td>
				<td><form:input path="proIdentity" type="file" /> <font
					color="red"> <form:errors path="proIdentity" /></font></td>
			</tr>

			<tr>
				<td><label path="proPhoto">PRO Photograph</label></td>
				<td><form:input path="proPhoto" type="file" /><font
					color="red"> <form:errors path="proPhoto" /></td>
			</tr>

			<tr>
				<td><center>
						<input type="submit" value="Submit" />
					</center></td>
			</tr>
		</table>

	</form:form>
</body>