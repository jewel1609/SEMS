<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/backend/resources/css/layout.css" />
<!-- JAVASCRIPTS --> 
<script type="text/javascript" src="/backend/scripts/jquery.min.js"></script>
<script type="text/javascript" src="/backend/scripts/jquery.backtop.js"></script>
<script type="text/javascript" src="/backend/scripts/jquery.mobilemenu.js"></script>
<script type="text/javascript" src="/backend/scripts/jquery.flexslider-min.js"></script>

<decorator:head />
<title><decorator:title default="sems" /></title>
</head>
<body>
   <div id="template">
      <div id="templateHeader">
         <jsp:include page="header.jsp" />
      </div>
      <div id="templateBody" class="row2">
         <decorator:body />
      </div>
      <div id="templateFooter">
         <jsp:include page="footer.jsp" />
      </div>
      <!--    <div style="clear:both;"></div>    -->
   </div>

</body>
</html>