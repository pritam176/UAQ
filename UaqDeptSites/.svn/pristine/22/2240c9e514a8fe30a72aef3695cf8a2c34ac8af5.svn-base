<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
<style type="text/css">
.center {
    margin: auto;
    width: 100%;
   background-color: #0099CC;
    padding: 10px;
} 
</style>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Untitled Document</title>
</head>
<h1 class="center">
	<center>
		<u>Renew PRO Card</u>
	</center>
</h1>
</head>
<body>
	<form:form method="" action="" commandName="renewProcard">
	<br>
		<table width="400">
			<tr>
				<td width="200"><label>Select the PRO</label></td>
				<td><form:select path="selectPro">
						<option value="syed">syed</option>
					</form:select></td>
			</tr>
		</table>
		<table width="100%" bordercolor="#333333" bgcolor="#0099CC">
			<tr>
				<td><label>Request Details</label></td>
			</tr>
		</table>
		<table width="400">
			<tr>
				<td width="200"><label>Name of the PRO</label></td>
				<td width="100"><form:input path="proName" type="text"
						readonly="readonly" value="Sayed" name="Name" /></td>
			</tr>
			<tr>
				<td width="200"><label>PRO ID Number</label></td>
				<td width="100"><form:input path="proId" type="text"
						readonly="readonly" value="784-xxx-xxxxx-xxx" name="proNo" /></td>
			</tr>
			<tr>
				<td width="200"><label>PRO ID Expiry Date</label></td>
				<td width="100"><form:select path="proExpDate">
						<option value="1512">15/12/2015</option>
					</form:select></td>
			</tr>

			<tr>
				<td width="200"><label>PRO Nationality</label></td>
				<td width="100"><form:input path="proNationality" type="text"
						readonly="readonly" value="Indian" /></td>
			</tr>

			<tr>
				<td><label>Attachments :</label></td>
			</tr>

			<tr>
				<td><label>Identity of the PRO(ID or Passport Copy)</label></td>
				<td><form:input path="proIdentity" type="file" /></td>
			</tr>
			<tr>
				<td><label>PRO Photograph</label></td>
				<td><form:input path="proPhoto" type="file" /></td>
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<td width="400"><center>
						<input type="submit" value="Submit" />
					</center></td>
			</tr>
		</table>
	</form:form>
</body>

