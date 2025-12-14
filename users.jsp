<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
        
        <p>Logged in as: <%=session.getAttribute("currentUser")%></p>
        <a href="viewProfile.action">My Profile</a> | 
        <a href="viewAllUsers.action">View All Users</a> | 
        <a href="logoff.action">Logout</a>
        
        <h2>All Users</h2>
        
        <table border="1">
            <tr>
                <th>User ID</th>
                <th>Username</th>
                <th>Email</th>
            </tr>
            <s:iterator value="usersList">
                <tr>
                    <td><s:property value="userid"/></td>
                    <td><s:property value="username"/></td>
                    <td><s:property value="email"/></td>
                </tr>
            </s:iterator>
        </table>
        
    </body>
</html>
