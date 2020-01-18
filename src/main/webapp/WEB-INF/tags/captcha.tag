<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<c:choose>
<c:when test="${not empty captcha}">
<input type="hidden" value="${captchaId}" name="captchaId">
<img alt="captcha" src="captcha.png"><br>
</c:when>
<c:otherwise>
<img alt="captcha" src="captcha.png"><br>
</c:otherwise>
</c:choose>