<%@ include file="/WEB-INF/views/jsp/includes.jsp" %>
<%@ include file="/WEB-INF/views/jsp/header.jsp" %>

<%
Exception ex = (Exception) request.getAttribute("exception");
%>

<h2>Hi. Page not found: <%= ex.getMessage() %></h2>
<p/>

<%
ex.printStackTrace(new java.io.PrintWriter(out));
%>

<p/>
<br/>
<a href="<spring:url value="/" htmlEscape="true" />">Home</a>

<%@ include file="/WEB-INF/views/jsp/footer.jsp" %>
