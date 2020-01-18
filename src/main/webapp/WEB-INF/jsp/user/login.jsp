<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ taglib prefix="captcha" uri="/WEB-INF/captcha.tld" %>
<html>

<c:set var="title" value="Login" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<link rel="stylesheet" href="resources/css/registration-style.css">
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<!--================Login Box Area =================-->
<div>
    <section>
        <div>
            <div id="wrapper">
                <div id="form">
                    <form id="register-form" name="register-form" action="login" method="post">
                        <h1>Login</h1>
                         <p style="color:#ff0000">${holder.errors.error_log_in}</p>
                        <div class="form-group">
                            <input class="form-control input-lg" placeholder="Login" name="login" id="login" type="text"
                                   value=${loginUserDto.login}>
                             <p style="color:#ff0000">${holder.errors.error_user_login}</p>
                        </div>
                        <div class="form-group">
                            <input class="form-control input-lg" placeholder="Password" name="password" id="password"
                                   value="" type="password">
                            <p style="color:#ff0000">${holder.errors.error_user_password}</p>
                        </div>
                        <button class="btn btn-lg btn-primary" type="submit">Login</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
<!--================End Login Box Area =================-->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>