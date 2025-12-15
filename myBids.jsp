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
        <title>My Bids</title>
    </head>
    <body>
        
        <p>Logged in as: <%=session.getAttribute("currentUser")%></p>
        <a href="viewProfile.action">My Profile</a> | 
        <a href="viewAllUsers.action">View All Users</a> | 
        <a href="viewItems.action">View Items</a> | 
        <a href="addItem.jsp">Add Item</a> | 
        <a href="logoff.action">Logout</a>
        
        <h2>My Bids</h2>
        
        <table border="6">
        <tr>
            <th>Item Name</th>
            <th>My Bid</th>
        </tr>
        <s:iterator value="myBidsList">
            <tr>
                <td><s:property value="itemname"/></td>
                <td><s:property value="bidamount"/></td>
            </tr>
        </s:iterator>
        </table>
        
    </body>
</html>
