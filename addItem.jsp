<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<%
    String currentUser = (String) session.getAttribute("currentUser");
    if(currentUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Item</title>
    </head>
    <body>
        
        <p>Logged in as: <%=session.getAttribute("currentUser")%></p>
        <a href="viewProfile.action">My Profile</a> | 
        <a href="viewAllUsers.action">View All Users</a> | 
        <a href="viewItems.action">View Items</a> | 
        <a href="viewMyBids.action">My Bids</a> | 
        <a href="logoff.action">Logout</a>
        
        <h2>Add New Item for Auction</h2>
        
        <s:form action="addItem">
            <s:textfield name="itemname" label="Item Name"/>
            <s:textfield name="startingbid" label="Starting Bid"/>
            <s:submit></s:submit>
        </s:form>
        
    </body>
</html>
