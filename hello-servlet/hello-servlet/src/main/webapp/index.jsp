<%@page import="org.apache.commons.lang3.SystemUtils"%>
<%@page import="java.util.Date"%>
<html>
<body>
<h2>Hello <%= SystemUtils.USER_NAME %>, from a Java webapp!</h2>
<%=new Date() %>
</body>
</html>
