<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="com.trade.Trade" 
    import= "org.springframework.web.servlet.ModelAndView" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body> <table border="1">
<tr><th>Trade Id</th><th>Version</th><th>Counter-Party Id</th><th>Book-Id</th><th>Maturity Date</th><th>Created Date</th><th>Expired</th></tr>
<c:forEach var="trade" items="${obj}">
	<tr><td> ${trade.getTradeId()} </td><td>${trade.getVersion()}</td><td>${trade.getCounterPartyId()}</td><td>${trade.getBookId()}</td>
	<td>${trade.getMaturityDate()}</td><td>${trade.getCreateDate()}</td><td>${trade.getExpired()}</td></tr>
</c:forEach>
</table>
</body>
</html>