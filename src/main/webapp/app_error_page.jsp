<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Error" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<link rel="stylesheet" href="resources/css/result.css">
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="placement">
    <h2>${appError}</h2>
    <p ><img src="resources/img/error.png"></p>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>