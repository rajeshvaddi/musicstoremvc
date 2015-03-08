<%@ page language="java" import="java.util.*" pageEncoding="US-ASCII"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Add new Song</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
  	<h1>${addNewSongMessage}</h1>
    <form:form method="POST" commandName="newSong"
        action="saveSong">
        <table>
            <tr>
                <td>Name:</td>
                <td><form:input path="songName" /></td>
                <td><form:errors path="songName" cssClass="error" /></td>
            </tr>
            <tr>
                <td>Artist Name:</td>
                <td><form:input path="artistName" /></td>
                <td><form:errors path="artistName" cssClass="error" /></td>
            </tr>
            <tr>
                <td>Album Name:</td>
                <td><form:input path="albumName" /></td>
                <td><form:errors path="albumName" cssClass="error" /></td>
            </tr>
             <tr>
                <td>Quantity:</td>
                <td><form:input path="quantity"/></td>
                <td><form:errors path="quantity" cssClass="error" /></td>
            </tr>
            <tr>
                <td>Unit Price:</td>
                <td><form:input path="unitPrice"/></td>
                <td><form:errors path="unitPrice" cssClass="error" /></td>
            </tr>
            <tr>
                <td>Genre:</td>
                <td>
                       <form:select path="genre">
                       	<form:options items="${dropDownList}" itemLabel="description" itemValue="code" />
                       </form:select>
                </td>
                <td><form:errors path="genre" cssClass="error" /></td>
            </tr>
    
            <tr>
                <td colspan="2"><input type="submit" value="Save Song"></td>
                </form:form>
       			<td>
      				<form:form method="POST" action="home">
   						<input type="submit" value="Go Home">
    				</form:form>
    			</td>
            </tr>
        </table>
   </div>     
  </body>
</html>
