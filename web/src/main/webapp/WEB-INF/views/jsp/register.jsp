<%@ include file="/WEB-INF/views/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title><spring:message code="welcome"/></title>
<link rel="stylesheet" href="<spring:url value="/static/css/common.css" htmlEscape="true" />" type="text/css"/>
<link rel="stylesheet" href="<spring:url value="/static/css/register.css" htmlEscape="true" />" type="text/css"/>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/jquery/jquery-1.4.2.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/json.min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/string_util.js"/>"></script>
</head>
<body>
<form:form modelAttribute="user" method="post" action="submit">
	<ul class="registerSection">
	   <li class="registerTitle">
	       Organization Section
	   </li>
	   <li class="registerInfo">
	       <ul>
	           <li class="label">Organization Code</li>
	           <li><form:input path="orgCode"/></li>
	           <li class="error" id="orgCodeError"><form:errors path="orgCode"/></li>
	       </ul>
           <ul>
               <li class="label">Organization Name</li>
               <li><form:input path="org.name"/></li>
               <li class="error"><form:errors path="org.name"/></li>
           </ul>
	   </li>
	</ul>
	<ul class="registerSection">
       <li class="registerTitle">
           Administrator Section
       </li>
       <li class="registerInfo">
           <ul>
               <li class="label">Login Id</li>
               <li><form:input path="loginId"/></li>
               <li class="error"><form:errors path="loginId" /></li>
           </ul>
           <ul>
               <li class="label">User Name</li>
               <li><form:input path="name"/></li>
               <li class="error"><form:errors path="name" /></li>
           </ul>
           <ul>
               <li class="label">Email</li>
               <li><form:input path="email"/></li>
               <li class="error"><form:errors path="email" /></li>
           </ul>
       </li>
	</ul>
	<ul class="registerSection">
	   <li class="registerInfo">
	       <ul>
			   <li class="label"></li>
			   <li><a id="register" href="#">Register</a></li>
	       </ul>
	   </li>
	</ul>
	<input type="submit"/>
</form:form>
</body>
<script type="text/javascript">
$(document).ready(function() {
    $("#orgCode").blur(function() {
        var orgCode = $('#orgCode').val();
        if (isEmptyString(orgCode)) {
            $('#orgCodeError').text('Organization Code cannot be empty.');
            return;
        }

        //$.ajaxSetup({global : false, contentType : 'application/json'});
        $.get("/Biz-Trace/uniquenessValidate",
            {property       : 'code',
             propertyValue  : orgCode,
             entityName     : 'Organization'
            },
            function(data) {
                if (data.isUnique == true) {
                	$('#orgCodeError').text('Organization Code can be used.');
                } else {
                	$('#orgCodeError').text('Organization Code has been used, please choose another one.');
                }
            }, 'json');
    });
/**
    $("#register").click(function() {
        var usrObj = $('#user').serializeObject();
        usrObj['org.code'] = usrObj.orgCod;
        //console.debug(usrObj);
        //usrObj.org = {};
        //usrObj.org.code = usrObj.orgCode;
        //usrObj.org.name = usrObj['org.name'];
        //delete usrObj['org.name'];
        //console.debug(usrObj);
        //console.debug(JSON.stringify(usrObj));
        //return;
        $.post('submit',
        	usrObj,
       		function(data) {
                console.debug(data);
        }, 'json');
    });
    */
});
</script>
</html>