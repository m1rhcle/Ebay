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
        <title>Items for Auction</title>
    </head>
    <body>
        
        <p>Logged in as: <%=session.getAttribute("currentUser")%></p>
        <a href="viewProfile.action">My Profile</a> | 
        <a href="viewAllUsers.action">View All Users</a> | 
        <a href="addItem.jsp">Add Item</a> | 
        <a href="viewMyBids.action">My Bids</a> | 
        <a href="logoff.action">Logout</a>
        
        <h2>Active Items for Auction</h2>
        
        <table border="1">
            <tr>
        <th>Item Name</th>
        <th>Owner</th>
        <th>Starting Bid</th>
        <th>Current Bid</th>
        <th>Bid Now</th>
        </tr>
        <s:iterator value="itemsList">
        <tr>
            <td><a href="viewItemDetails.action?itemid=<s:property value='itemid'/>"><s:property value="itemname"/></a></td>
            <td><s:property value="owner"/></td>
            <td><s:property value="startingbid"/></td>
            <td><s:property value="currentbid"/></td>
            <td>
        <s:form action="placeBid">
            <s:hidden name="itemid" value="%{itemid}"/>
            <s:textfield name="bidamount" size="8"/>
            <s:submit></s:submit>
        </s:form>
            </td>
        </tr>
    </s:iterator>
</table>

    </body>
</html>
