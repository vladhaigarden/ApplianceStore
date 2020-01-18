<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>

<c:set var="title" value="User account" scope="page"/>

<%@ include file="/WEB-INF/jspf/head.jspf" %>
<link rel="stylesheet" href="resources/css/registration-style.css">
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<h1>${message}</h1>
<h2>Welcome,${user.firstName} ${user.lastName}!</h2>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>