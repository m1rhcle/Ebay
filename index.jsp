

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EBAY</title>
    </head>
    <body>

        <s:form action ="register">
             <s:textfield name="email" label = "Enter Email"/>
             <s:textfield name="username" label = "Enter Username"/>
			 <s:textfield name="password" label ="Enter Password"/>
             <s:submit></s:submit>
        </s:form>
		
    </body>
</html>










