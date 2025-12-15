<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Item Details</title>
</head>
<body>
    <% 
        String currentUser = (String) session.getAttribute("currentUser");
        if(currentUser != null) {
    %>


        <p>Logged in as: <%=currentUser%></p>
        <a href="viewProfile.action">My Profile</a> | 
        <a href="viewAllUsers.action">View All Users</a> | 
        <a href="addItem.jsp">Add Item</a> | 
        <a href="viewItems.action">View Items</a> | 
        <a href="viewMyBids.action">My Bids</a> | 
        <a href="logoff.action">Logout</a>

    <% } else { %>
        <a href="login.jsp">Login</a> | 
        <a href="index.jsp">Register</a>
    <% }    %>
    
    <h2><s:property value="itemname"/></h2>
    
    <table border="6">
        <tr>
            <th>Owner</th>
            <th>Starting Bid</th>
            <th>Current Bid</th>
        </tr>
        <tr>
            <td><s:property value="owner"/></td>
            <td><s:property value="startingbid"/></td>
            <td><s:property value="currentbid"/></td>
        </tr>
    </table>
    
    <% if(currentUser != null) { %>
    <h3>Place Your Bid</h3>
    <s:form action="placeBid">
        <s:hidden name="itemid" value="%{itemid}"/>
        <s:textfield name="bidamount" label="Bid Amount" required="true"/>
        <s:submit value="Place Bid"/>
    </s:form>
    <% } %>
    
    <h2>Bid History</h2>
    <s:if test="bidsList.size() > 0">
        <table border="6">
            <tr>
                <th>Bidder</th>
                <th>Bid Amount</th>
            </tr>
            <s:iterator value="bidsList">
                <tr>
                    <td><s:property value="username"/></td>
                    <td><s:property value="bidamount"/></td>
                </tr>
            </s:iterator>
        </table>
    </s:if>
    
    
</body>
</html>
