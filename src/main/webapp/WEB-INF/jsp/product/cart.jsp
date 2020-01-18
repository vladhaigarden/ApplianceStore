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
    <!--================Cart Area =================-->
    <section class="cart_area">
        <div class="container">
            <div class="cart_inner">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Product</th>
                                <th scope="col">Name</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Delete</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach var="basket" items="${cart.basket}">
                                <c:set var="product" value="${basket.key}"></c:set>
                                <c:set var="amount" value="${basket.value}"></c:set>

                                <c:if test="${amount > 0}">
                                    <tr class="tableProducts">
                                        <td>
                                            <div class="media">
                                                <div class="d-flex">
                                                    <img src="resources/img/products/${product.id}.jpg" alt=""
                                                        width="30%">
                                                </div>

                                            </div>
                                        </td>
                                        <td>
                                            <h5>${basket.key.name}</h5>
                                        </td>
                                        <td>
                                            <input id="priceProduct" type="hidden" value="${product.price}">
                                            <h5 id="pr">${product.price}$</h5>
                                        </td>
                                        <td>
                                            <div class="product_count">

                                                <input type="text" name="qty" id="sst" maxlength="12" value="${amount}"
                                                    title="Quantity:" class="input-text qty">
                                                <button value="${product.id}" onclick="increaseCart(this)"
                                                    class="increase items-count" type="button"><i
                                                        class="lnr lnr-chevron-up"></i></button>

                                                <button value="${product.id}" onclick="decreaseCart(this)"
                                                    class="reduced items-count" type="button"><i
                                                        class="lnr lnr-chevron-down"></i></button>

                                            </div>
                                        </td>
                                        <td>
                                                <input type="hidden"  value="${amount}">
                                          <button value="${product.id}" onclick="deleteProduct(this)">X</button>
                                        </td>
                                    </tr>
                                </c:if>

                            </c:forEach>
                           

                            <tr class="bottom_button">
                                <c:if test="${cart.size>0}">
                                    <td>
                                        <button class="button" onclick="clearCart(this)">Clear cart</button>
                                    
                                    </td>
                                </c:if>
                                <td>

                                </td>
                                <td>
                                </td>
                            </tr>

                            <tr>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <h5>Subtotal</h5>
                                </td>
                                <td>
                                    <input id="valueTotal" type="hidden" value="${cart.totalPrice}">
                                    <h5 id="total">${cart.totalPrice}$</h5>
                                </td>
                            </tr>
                            <tr class="out_button_area">
                                <td class="d-none-l">

                                </td>
                                <td class="">

                                </td>
                                <td>

                                </td>
                                <td>
                                    <div class="checkout_btn_inner d-flex align-items-center">
                                        <a class="gray_btn" href="products">Continue Shopping</a>
                                        <a class="primary-btn ml-2" href="order">Proceed to checkout</a>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
    <!--================End Cart Area =================-->

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    <script src="resources/vendors/nouislider/nouislider.min.js"></script>
    <script src="resources/js/updateCart.js"></script>
</body>

</html>