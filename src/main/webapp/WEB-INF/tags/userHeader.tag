<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<div style="margin-right: 5%">
<ul class="nav-shop">
<li class="nav-item"><button><i class="ti-shopping-cart"></i><span class="nav-shop__circle" id="sizeCart">${cart.amountProduct}</span></button> </li>
<li class="nav-item"><a class="button button-header" href="order"><fmt:message key="head_buy_now"/></a></li>
</ul>
</div>
<c:choose>
<c:when test="${not empty user}">
<img class="round" alt="avatar" src="avatar.png"><br>
<p style="margin-bottom: 0%">${user.login}</p>
<form action="logout">
<input type="submit" value="Logout" style="font-weight: bolder;width: 4.5em;   height: 2.1em;  color: #fafafa; background: #4169E1; margin-top: 16%; margin-left: 10%">
</form>
</c:when>
<c:otherwise>
<div class="userHead">
<form action="login" method="post">
<input type="text" name="login" placeholder="<fmt:message key="input_login"/>">
<input type="password" name="password" placeholder="<fmt:message key="input_password"/>">
<input type="submit" value="Log" style="font-weight: bolder;width: 2.9em; height: 2.1em; color: #fafafa; background: #4169E1; margin-top: 4%\">
</form>
</div>
</c:otherwise>
</c:choose>