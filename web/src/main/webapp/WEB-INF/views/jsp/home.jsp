<%@ include file="/WEB-INF/views/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="description" content="This is where help you trace your business processes to make it right on track"/>
<meta name="keywords" content="Biz-Trace, Business, Trace, Document, CPM"/>
<title><spring:message code="welcome"/></title>
<link rel="stylesheet" href="<spring:url value="/static/css/common.css" htmlEscape="true" />" type="text/css"/>
<link rel="stylesheet" href="<spring:url value="/static/css/login.css" htmlEscape="true" />" type="text/css"/>
<link rel="stylesheet" href="<spring:url value="/static/css/jquery/redmond/redmond.css" htmlEscape="true" />" type="text/css"/>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/jquery/jquery-1.4.2.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/jquery/jquery-ui-1.8.custom.min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/jquery/jquery-ui-themeswitcher.js"/>"></script>
<script>
  $(document).ready(function(){
    $('#switcher').themeswitcher();
    $('#login').button();
  });
  </script>
</head>
<body>
    <div id="switcher"></div>
    <form:form modelAttribute="loginUser" method="post" action="login">
        <form:errors path="*" cssClass="errorBox" />
		<table id="loginTable">
		   <tr>
		       <td>Organization Code</td>
		       <td><form:input path="orgCode"/></td>
		       <td><form:errors path="orgCode" /></td>
		   </tr>
	       <tr>
	           <td>User Login Id</td>
	           <td><form:input path="loginId"/></td>
               <td><form:errors path="loginId" /></td>
	       </tr>
	       <tr>
	           <td>Password</td>
	           <td><form:password path="password"/></td>
               <td><form:errors path="password" /></td>
	       </tr>
	       <tr>
	            <td><input type="submit" id="login" value="Login"/></td>
	            <td><a id="register" href="register/init">Register Organization</a></td>
	       </tr>
		</table>
	</form:form>
</body>
</html>
