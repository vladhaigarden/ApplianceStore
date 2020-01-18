<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@taglib prefix="ownTag" tagdir="/WEB-INF/tags" %>
<html>

<c:set var="title" value="Registration" scope="page"/>
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
                    <form id="register-form" name="register-form" action="register" method="post" enctype="multipart/form-data">
                        <h1>Sign-up</h1>
                        <div class="form-group">
                            <input class="form-control input-lg" placeholder="Login" name="login" id="login" type="text"
                                   value=${dto.login}>
                            <p style="color:#ff0000">${holder.errors.error_user_login}</p>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="form-group">
                            <input class="form-control input-lg" placeholder="E-mail Address" name="email" id='email'
                                   type="text" value=${dto.email}>
                            <p style="color:#ff0000">${holder.errors.error_user_email}</p>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="form-group">
                            <input class="form-control input-lg" placeholder="Password" name="password" id="password"
                                   value="" type="password">
                            <p style="color:#ff0000">${holder.errors.error_user_password}</p>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="form-group">
                            <input class="form-control input-lg" placeholder="Confirm Password" name="confirmPassword"
                                   id='confirmPassword' type="password">
                            <p style="color:#ff0000">${holder.errors.error_user_confirm_password}</p>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="form-group">
                            <input class="form-control input-lg" placeholder="First name" name="firstName"
                                   id='firstName' type="text" value=${dto.firstName}>
                            <p style="color:#ff0000">${holder.errors.error_user_first_name}</p>
                            <div class="invalid-feedback"></div>
                        </div>
                        <div class="form-group">
                            <input class="form-control input-lg" placeholder="Last name" name="lastName" id='lastName'
                                   type="text" value=${dto.lastName}>
                            <p style="color:#ff0000">${holder.errors.error_user_last_name}</p>
                            <div class="invalid-feedback"></div>
                        </div>

                        <div class="form-group">
                         <ownTag:captcha/>
                         <input class="form-control input-lg" placeholder="Enter the code from the image"
                         name="captchaCode" id="captchaCode" type="text"/>
                         <p style="color:#ff0000">${holder.errors.error_user_code}</p>
                        </div>

                        <div class="form-group">
                            <div class="form-check">
                                <input class="form-check-input" name="newsletters" id="newsletters" value="news"
                                       type="checkbox">
                                <label class="form-check-label">Subscribe to news</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-check">
                                <input class="form-check-input" name="newsletters" id="newsletters"
                                       value="renewal of assortment" type="checkbox">
                                <label class="form-check-label">Subscribe to renewal of assortment</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-check">
                                <input class="form-check-input" name="newsletters" id="newsletters" value="shares"
                                       type="checkbox">
                                <label class="form-check-label">Subscribe to shares</label>
                            </div>
                        </div>

                       <div class="fl_upld">
                       <label><input  id="fl_inp" type="file"  name="file">Upload avatar</label>
                       <div id="fl_nm">${holder.errors.error_user_avatar}</div>
                       </div>

                        <button class="btn btn-lg btn-primary" type="submit">Register</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
<!--================End Login Box Area =================-->
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="resources/js/registration-validation-query.js"></script>
<script>
$(document).ready( function() {
    $("#fl_inp").change(function(){
         var filename = $(this).val().replace(/.*\\/, "");
         $("#fl_nm").html(filename);
    });
});
</script>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>