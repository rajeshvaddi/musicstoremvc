<%@ page language="java" import="java.util.*" pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    <base href="<%=path %>">
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<c:url value="/resources/js/jquery_1.11_min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.tablesorter.min.js" />"></script>
	<script type="text/javascript">
		jQuery(document).ready(function(){
  			$('#keywords').tablesorter();}
  		);
	</script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/musicstore.css" />" >
	<style>
	.error {
    		color: #ff0000;
    		font-style: italic;
    		font-weight: bold;
	}
	</style>
  </head>
  
  <body>
  	<div id="wrapper">
    <h1>${Message}</h1>
    <table >
            <tr>
     <form:form method="POST" action="search/search" commandName="searchCriteria" >
   		<td><form:input path="searchString" /></td><td><input type="submit" value="Search Song"></td><td><form:errors path="searchString" cssClass="error" /></td>
    </form:form>
    </tr>
    <tr>
    <td colspan="2">
      <form:form method="POST" action="add">
   			<input type="submit" value="Add Song">
    </form:form>
    </td>
        <td>
      <form:form method="POST" action="home">
   			<input type="submit" value="Refresh">
    </form:form>
    </td>
    </tr>
     </table>
     <table id="keywords" cellspacing="0" cellpadding="0" class="tablesorter">
     <thead>
     <tr>
     <th>Song Name</th>
     <th>Album Name</th>
     <th>Artist Name</th>
     <th>Unit Price</th>
     <th>Quantity</th>
     <th>Genre</th>
     <th>Total Price</th>
     <th></th>
     <th></th>
     </tr>
     </thead>
     <tbody>
     <c:forEach items="${songList}" var="song" varStatus="myIndex">
     <tr>
     	<td class="lalign"><c:out value="${song.songName}"/></td>
     	<td><c:out value="${song.albumName}"/></td>
     	<td><c:out value="${song.artistName}"/></td>
     	<td><c:out value="${song.unitPrice}"/></td>
     	<td><c:out value="${song.quantity}"/></td>
     	<td><select disabled="true">
     			
    			<c:forEach items="${dropDownList}" var="item" varStatus="status">
        			<c:choose>
            			<c:when test="${item.code eq song.genre}">
                			<option value="${item.code}" selected="true">${item.description}</option>
            			</c:when>
            			<c:otherwise>
                			<option value="${item.code}" >${item.description}</option>
            			</c:otherwise>
        			</c:choose> 
    			</c:forEach>
			<select>
     	</td>
     	<td>
     		<c:set var="total" value="${song.quantity*song.unitPrice }"></c:set>
     		<!--<c:out value="${total}"/>-->
     		<fmt:formatNumber value="${total}" maxFractionDigits="2" type="CURRENCY"/>
     	</td>
     	<td><a href="edit?id=${songList[myIndex.index].id}"><img title="Edit" alt="Edit" src='<c:url value="/resources/images/edit.png"/>'/></a></td>
     	<td><a href="delete?id=${songList[myIndex.index].id}" onclick="return confirm('do you really want to delete ${songList[myIndex.index].songName} in  ${songList[myIndex.index].albumName}?')">
     	<img title="Delete" alt="Edit" src='<c:url value="/resources/images/delete.png"/>'/></a></td>
     </tr>
     </c:forEach>
    
     </tbody>
     </table>
     </div>
  </body>
</html>
