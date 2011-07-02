<%@ include file="/WEB-INF/views/jsp/includes.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="description" content="This is where help you trace your business processes to make it right on track"/>
<meta name="keywords" content="Biz-Trace, Business, Trace, Document, CPM"/>
<title><spring:message code="welcome"/></title>
<link rel="stylesheet" href="<spring:url value="/static/css/common.css" htmlEscape="true" />" type="text/css"/>
<link rel="stylesheet" href="<spring:url value="/static/css/jquery/redmond/redmond.css" htmlEscape="true" />" type="text/css"/>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/jquery/jquery-1.4.2.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/jquery/jquery-ui-1.8.custom.min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/map.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/biz/base.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/biz/script.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/biz/componentMgr.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/biz/clientDataSource.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/biz/layout.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/biz/module.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/biz/grid.js"/>"></script>

 
<!-- YUI Related -->
<link type="text/css" rel="stylesheet" href="<spring:url value="/static/js/yui/fonts/fonts-min.css" htmlEscape="true" />">
<link type="text/css" rel="stylesheet" href="<spring:url value="/static/js/yui/reset-fonts-grids/reset-fonts-grids.css" htmlEscape="true" />">
<link type="text/css" rel="stylesheet" href="<spring:url value="/static/js/yui/assets/skins/sam/resize.css" htmlEscape="true" />">
<link type="text/css" rel="stylesheet" href="<spring:url value="/static/js/yui/assets/skins/sam/layout.css" htmlEscape="true" />">
<link type="text/css" rel="stylesheet" href="<spring:url value="/static/js/yui/button/assets/skins/sam/button.css" htmlEscape="true" />">
<link type="text/css" rel="stylesheet" href="<spring:url value="/static/js/yui/menu/assets/skins/sam/menu.css" htmlEscape="true" />">
<link type="text/css" rel="stylesheet" href="<spring:url value="/static/js/yui/tabview/assets/skins/sam/tabview.css" htmlEscape="true" />">
<link type="text/css" rel="stylesheet" href="<spring:url value="/static/js/yui/calendar/assets/skins/sam/calendar.css" htmlEscape="true" />">
<link type="text/css" rel="stylesheet" href="<spring:url value="/static/js/yui/datatable/assets/skins/sam/datatable.css" htmlEscape="true" />">
<link type="text/css" rel="stylesheet" href="<spring:url value="/static/js/yui/paginator/assets/skins/sam/paginator.css" htmlEscape="true" />">
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/yahoo/yahoo-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/dom/dom-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/event/event-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/yahoo-dom-event/yahoo-dom-event.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/element/element-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/animation/animation-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/resize/resize-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/layout/layout-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/connection/connection-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/container/container-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/menu/menu-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/button/button-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/tabview/tabview-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/datasource/datasource-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/json/json-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/dragdrop/dragdrop-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/calendar/calendar-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/datatable/datatable-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/yui/paginator/paginator-min.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/jsonpath-0.8.0.js"/>"></script>
<script type="text/javascript" language="Javascript" src="<spring:url value="/static/js/biz/yui_override.js"/>"></script>
<script>
Biz.CONTEXT = '${pageContext.request.contextPath}/';

$(document).ready(function() {
	Biz.Layout.init();
	Biz.Module.buildSystemModuleMenu();
});
</script>
</head>
<body class="yui-skin-sam">
    <div id="header"></div>
    <div id="userMenu">
    </div>
    <div id="conent">
        <div id="moduleTitle"></div>
        <div id="moduleButtons"></div>
        <div id="moduleContent">
        </div>
    </div>
    <div id="footer">Here is footer</div>
</body>
</html>
