<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="Result" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<link rel="stylesheet" href="resources/css/result.css">
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div id="placement" style="margin-bottom: 10%">
<p ><img src="resources/img/result.png"></p>
    <h2>${result}</h2>
    </div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>