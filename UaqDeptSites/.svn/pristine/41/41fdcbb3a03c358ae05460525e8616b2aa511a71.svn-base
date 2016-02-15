<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%-- <%@page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<%@page import="net.tanesha.recaptcha.ReCaptcha"%> --%>
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
		<u> New Establishment Registration Request </u>
	</center>
</h1>
</head>
<body>
	<form:form>
		<br>

		<table width="100%" bordercolor="#333333" bgcolor="#0099CC">
			<tr>
				<td><label>Account Details</label></td>
			</tr>
		</table>
		<br>
		<table width="62%" cellspacing="5">

			<tr>
				<td width="21%"><label>Establishment Name</label></td>
				<td><form:input path="" type="text" name="fullName"
						disabled="disabled" style="width: 150px;" /></td>

			</tr>
			<tr>
				<td width="21%"><label>Trade License</label></td>
				<td><form:input path="" style="width: 150px;" type="text"
						disabled="disabled" name="tradeLic" /></td>

				<td><label>Trade License Expiry Date </label></td>
				<td width="19%"><form:select path="" disabled="disabled"
						style="width: 150px;">
						<option value="1/05/2020">1/05/2020</option>
					</form:select></td>
			</tr>

			<tr>
				<td width="21%"><label>Mobile Number</label></td>
				<td width="28%"><form:select path="">
						<option value="060">060</option>
					</form:select> &nbsp; <form:input path="" type="text" value=""
						style="width: 90px;" /></td>


				<td width="30%"><label>Office Phone</label></td>
				<td width="19%"><form:select path="">
						<option value="060">060</option>
					</form:select> &nbsp; <form:input path="" type="text" value=""
						style="width: 80px;" /></td>
				<td width="2%"></td>
			</tr>
			<tr>
				<td width="21%"><label>Email Address</label></td>
				<td width="28%"><form:input path="" type="text"
						name="emailAddress" style="width: 150px;" /></td>
			</tr>

			<tr>
				<td width="21%"><label>Address</label></td>
				<td width="28%"><form:input path="" type="text" value=""
						style="width: 150px;" /></td>
				<td width="30%"><label>Website</label></td>
				<td width="19%"><form:input path="" type="text" value=""
						style="width: 150px;" /></td>
				<td width="2%"></td>
			</tr>


			<tr>
				<td width="21%"><label>Emirate</label></td>
				<td width="28%"><form:select path="" disabled="disabled"
						style="width: 150px;">
						<option value="uaq">Umm al Quwain</option>
					</form:select></td>
				<td width="30%"><label>Post Box</label></td>
				<td width="19%"><form:input path="" type="text" value=""
						style="width: 150px;" /></td>
				<td width="2%"></td>
			</tr>


		</table>
		<br>

		<table width="100%" bordercolor="#333333" bgcolor="#0099CC">
			<tr>
				<td><label>Credentials</label></td>
			</tr>
		</table>
		<br>
		<table width="62%" cellspacing="5">
			<tr>
				<td width="21%"><label>Username</label></td>
				<td width="28%"><form:input path="" type="text" name="uname"
						style="width: 150px;" />
			</tr>

			<tr>
				<td width="21%"><label>Password</label></td>
				<td width="28%"><form:input path="" type="password"
						name="pword" style="width: 150px;" /></td>

				<td width="30%"><label>confirm Password</label></td>
				<td><form:input path="" type="password" name="pword"
						style="width: 150px;" /></td>
			</tr>

			<tr>
				<td colspan="4"><center>
						<%-- <%
							ReCaptcha captcha = ReCaptchaFactory.newReCaptcha(
										"6Lc3zAoTAAAAANKz34tUL_Z4bTXLR5AiRvkxkwgL",
										"6Lc3zAoTAAAAAJba8wJaf8MU2UZB_P8l7AHYXr4E", false);
								out.print(captcha.createRecaptchaHtml(null, null));
						%> --%><br> <input type="submit" value="Submit" />
					</center></td>
			</tr>
		</table>
		<br>
	</form:form>
</body>
</html>
