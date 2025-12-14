<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Profile</title>
    </head>
    <body>
        
        <p>Logged in as: <s:property value="username"/></p>
        <a href="viewProfile.action">My Profile</a> | 
        <a href="viewAllUsers.action">View All Users</a> | 
        <a href="logoff.action">Logout</a>
        
        <h2>My Profile</h2>
        
        <p>User ID: <s:property value="userid"/>
        <p>Username: <s:property value="username"/>
        <p>Email: <s:property value="email"/>
            
        
    </body>
</html>
