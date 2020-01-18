<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@taglib prefix="ownTag" tagdir="/WEB-INF/tags" %>
<html>
<c:set var="title" value="Product categories" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<link rel="stylesheet" href="resources/vendors/linericon/style.css">
<link rel="stylesheet" href="resources/vendors/nouislider/nouislider.min.css">
</head>

<body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <c:choose>
<c:when test="${not empty user}">
<!--================Checkout Area =================-->
<section class="checkout_area section-margin--small">
    <div class="container">
        <div class="billing_details">
            <div class="row">
                <div class="col-lg-8">
                    <h3>Billing Details</h3>
                    <form class="row contact_form" action="#" method="post" novalidate="novalidate">
                        <div class="col-md-6 form-group p_star">
                            <input type="text" class="form-control" id="first" name="name">
                            <span class="placeholder" data-placeholder="First name"></span>
                        </div>
                        <div class="col-md-6 form-group p_star">
                            <input type="text" class="form-control" id="last" name="name">
                            <span class="placeholder" data-placeholder="Last name"></span>
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="company" name="company"
                                placeholder="Company name">
                        </div>
                        <div class="col-md-6 form-group p_star">
                            <input type="text" class="form-control" id="number" name="number">
                            <span class="placeholder" data-placeholder="Phone number"></span>
                        </div>
                        <div class="col-md-6 form-group p_star">
                            <input type="text" class="form-control" id="email" name="compemailany">
                            <span class="placeholder" data-placeholder="Email Address"></span>
                        </div>
                        <div class="col-md-12 form-group p_star">
                            <select class="country_select">
                                <option value="1">Country</option>
                                <option value="2">Country</option>
                                <option value="4">Country</option>
                            </select>
                        </div>
                        <div class="col-md-12 form-group p_star">
                            <input type="text" class="form-control" id="add1" name="add1">
                            <span class="placeholder" data-placeholder="Address line 01"></span>
                        </div>
                        <div class="col-md-12 form-group p_star">
                            <input type="text" class="form-control" id="add2" name="add2">
                            <span class="placeholder" data-placeholder="Address line 02"></span>
                        </div>
                        <div class="col-md-12 form-group p_star">
                            <input type="text" class="form-control" id="city" name="city">
                            <span class="placeholder" data-placeholder="Town/City"></span>
                        </div>
                        <div class="col-md-12 form-group p_star">
                            <select class="country_select">
                                <option value="1">District</option>
                                <option value="2">District</option>
                                <option value="4">District</option>
                            </select>
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="zip" name="zip" placeholder="Postcode/ZIP">
                        </div>
                        <div class="col-md-12 form-group">
                            <div class="creat_account">
                                <input type="checkbox" id="f-option2" name="selector">
                                <label for="f-option2">Create an account?</label>
                            </div>
                        </div>
                        <div class="col-md-12 form-group mb-0">
                            <div class="creat_account">
                                <h3>Shipping Details</h3>
                                <input type="checkbox" id="f-option3" name="selector">
                                <label for="f-option3">Ship to a different address?</label>
                            </div>
                            <textarea class="form-control" name="message" id="message" rows="1"
                                placeholder="Order Notes"></textarea>
                        </div>
                    </form>
                </div>
                
                <div class="col-lg-4">
                        
                    <div class="order_box">
                        <h2>Your Order</h2>
                        <ul class="list">
                            <c:forEach var="basket" items="${cart.basket}">
                                <c:set var="product" value="${basket.key}"></c:set>
                                <c:set var="amount" value="${basket.value}"></c:set>

                                <li><a href="#">
                                        <h4>Product <span>Total</span></h4>
                                    </a></li>
                                <li><a href="#">Fresh Blackberry <span class="middle">x ${amount}</span> <span
                                            class="last">${product.price}$</span></a></li>
                          </c:forEach>
                        </ul>
                        <ul class="list list_2">
                            <li><a href="#">Total <span>${cart.totalPrice}$</span></a></li>
                        </ul>
                        <div class="payment_item">
                            <div class="radion_btn">
                                <input type="radio" id="f-option5" name="selector">
                                <label for="f-option5">Check payments</label>
                                <div class="check"></div>
                            </div>
                            <p>Please send a check to Store Name, Store Street, Store Town, Store State / County,
                                Store Postcode.</p>
                        </div>
                        <div class="payment_item active">
                            <div class="radion_btn">
                                <input type="radio" id="f-option6" name="selector">
                                <label for="f-option6">Paypal </label>
                                <img src="resources/img/product/card.jpg" alt="">
                                <div class="check"></div>
                            </div>
                            <p>Pay via PayPal; you can pay with your credit card if you do not have a PayPalaccount.
                            </p>
                        </div>
                        <div class="creat_account">
                            <input type="checkbox" id="f-option4" name="selector">
                            <label for="f-option4">I have read and accept the </label>
                            <a href="#">terms & conditions*</a>
                        </div>

                        <div class="text-center">

                              <div class="form-group">
                                <label for="cc-number" class="control-label">Card number<small class="text-muted"></small></label>
                                <input id="cc-number" type="tel" class="input-lg form-control cc-number amex identified" maxlength="16" pattern="\d[0-9]" autocomplete="cc-number" placeholder="•••• •••• •••• ••••">
                              </div>

                              <div class="form-group">
                                <label for="cc-exp" class="control-label">Card expiry</label>
                                <input id="cc-exp" type="tel" class="input-lg form-control cc-exp" maxlength="4"autocomplete="cc-exp" placeholder="•• / ••">
                              </div>

                              <div class="form-group">
                                <label for="cc-cvc" class="control-label">Card CVC</label>
                                <input id="cc-cvc" type="tel" class="input-lg form-control cc-cvc" autocomplete="off" maxlength="3" placeholder="•••">
                              </div>

                              <c:if test="${cart.size>0}">
                             <form action="order" method="post">
                              <button type="submit" class="btn btn-lg btn-primary">Proceed to Paypal</button>
                              </form>
                              </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!--================End Checkout Area =================-->
</c:when>
<c:otherwise>
    <div class="returning_customer" style="margin-left: 30%; margin-top: 5%; margin-bottom: 10%">
        <div class="check_title">
        </div>
        <p>If you with to make order, please login.</p>
        <form class="row contact_form" action="login" method="post">
            <div class="col-md-6 form-group p_star">
                <input type="text" class="form-control" placeholder="Login*" onfocus="this.placeholder=''" onblur="this.placeholder = 'Login*'" id="name" name="login">
            </div>
            <div class="col-md-6 form-group p_star">
                <input type="password" class="form-control" placeholder="Password*" onfocus="this.placeholder=''" onblur="this.placeholder = 'Password*'" id="password" name="password">
            </div>
            <div class="col-md-12 form-group">
                <button type="submit" value="submit" class="button button-login">login</button>
            </div>
        </form>
    </div>
</c:otherwise>
</c:choose>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    <script src="resources/vendors/nouislider/nouislider.min.js"></script>
    <script src="resources/js/listener.js"></script>
</body>
</html>