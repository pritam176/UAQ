<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>  
<head>  
    <title>Blue Rays</title>  
    <script type="text/javascript" src="<c:url value="/resources/js/main.js"/>"></script>
    <link  rel="stylesheet" href = "<c:url value="/resources/css/main.css"/>"></link>
</head>  
<body>  
<h1 class="p">Welcome to Blue Rays</h1>  
    <p>Message is: ${message}</p>  
    
    <p>mess<spring:message code="lable.test"  /></p>  
    Current Locale : ${pageContext.response.locale}
</body>  
</html>  