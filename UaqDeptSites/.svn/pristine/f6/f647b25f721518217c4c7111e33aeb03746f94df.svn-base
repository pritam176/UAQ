<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Submit Noc Letter</title>
<style>
#body{
width:1024px;
}
#landRequest{
background-color:#0099CC;
width:100%;

text-align:center;

padding-top:40px;
padding-bottom:40px;

}button{
border-radius: 7px;
    padding: 3px;
    font-size: 15px;
    color: black;
    background-color: white;
	}
	#userInfo{
	margin-top:10px;
	}.lable{
	   background-color: #00CED1;
    /* width: 100px; */
    padding: 7px;
    margin: 18px 0;
	}.content{
	margin-left:20px;
	
	}
	
</style>
</head>
<body>
<div id="body">
<div id="container">
  <div id="landRequest"><a name="AddLandRequest" id="AddLandRequest" href="#">AddLandRequest</a></div>
  <div id="userInfo">
    <table width="100%"  cellspacing="0" cellpadding="0">
      <tr>
        <td>Request Number</td>
        <td>PS-002-15-0005</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td>My Detail</td>
        <td>Muhamd</td>
        <td><button>More Info ...</button></td>
      </tr>
    </table>
  </div>
  <div id="requestDetail">
  <div class ="lable"><span>Request Detail</span></div>
  	<div class="content">
  <b>Land Location</b>
  <table width="100%" cellspacing="0" cellpadding="0">
  <tr>
    <td>Sector: 5</td>
    <td>Block : 3</td>
    <td>Sub Sector :3</td>
    <td>Plot No:48</td>
  </tr>
</table>
	<span><b>Land Usage:</b>Residental</span>
	</div>
	<div id="coment">
	<div class="lable"><span>Comments</span></div>
	<div class="content">
	<p>You are Requested to submit "No objection certificate" from FEWA</p>
	<span>Request NOC Letter &nbsp;&nbsp;&nbsp;&nbsp;</span> <button>Download the Letter</button>
	</div>
	</div>
	
	<div id="action">
	<div class="lable"><span>Actions</span></div>
	<div class="content">
	<form:form  commandName="pssubmitnocletter" action="psnoclettersubmit.html" 
	modelAttribute="pssubmitnocletter"
	enctype="multipart/form-data" method="post">
	<span>Submit NOC &nbsp; &nbsp; &nbsp; <form:input type="file" id="noc" path ="file" value="Browse" /></span>
		<input type="submit" value="submit" />
	</form:form>
	</div>
	</div>
	
	</div>
	
  </div>
</div>
</body>
</html>
