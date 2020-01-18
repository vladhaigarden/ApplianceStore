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

<!-- ================ Start modal component ================= -->

 <!-- The Modal -->
<div class="modal" id="myModal" style="margin-top: 10%">
    <div class="modal-dialog">
      <div class="modal-content">
  
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Successful result</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
       
        <!-- Modal body -->
        <div class="modal-body">
            Product successfully added to cart!</div>
  
        <!-- Modal footer -->
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
        </div>
  
      </div>
    </div>
  </div>

    <!-- ================ End modal component ================= -->

  <!-- ================ category section start ================= -->
  <section class="section-margin--small mb-5">
    <div class="container">
      <div class="row">
        <div class="col-xl-3 col-lg-4 col-md-5">
          <form action="products" id="filter-form">
            <div class="sidebar-filter">
              <div class="top-filter-head">Product Filters</div>
              <div class="common-filter">
                <div class="head">Name</div>
                <input value="${filterBean.name}" placeholder="Enter name..." name="name" id="name" type="text"
                  style=" margin-left: 12%">
              </div>
              <div class="common-filter">
                <div class="head">Categories</div>
                <ul>
                  <c:forEach var="category" items="${categories}">
                    <c:if test="${filterBean.category == category.value}">
                      <c:set var="currentCategory">${category.key}</c:set>
                      <li class="filter-list"><input class="pixel-radio" type="radio" name="category"
                          value="${category.key}" checked><label>${category.value}</label></li>
                    </c:if>
                    <c:if test="${filterBean.category != category.value}">
                      <li class="filter-list"><input class="pixel-radio" type="radio" name="category"
                          value="${category.key}"><label>${category.value}</label></li>
                    </c:if>
                  </c:forEach>
                </ul>
              </div>
              <div class="common-filter">
                <div class="head">Manufacturers</div>
                <ul>
                  <c:forEach var="manufacturer" items="${manufacturers}">
                    <c:if test="${filterBean.manufacturer == manufacturer.value}">
                      <c:set var="currentManufacturer">${manufacturer.key}</c:set>
                      <li class="filter-list"><input class="pixel-radio" type="radio" name="manufacturer"
                          value="${manufacturer.key}" checked><label>${manufacturer.value}</label></li>
                    </c:if>
                    <c:if test="${filterBean.manufacturer != manufacturer.value}">
                      <li class="filter-list"><input class="pixel-radio" type="radio" name="manufacturer"
                          value="${manufacturer.key}"><label>${manufacturer.value}</label></li>
                    </c:if>
                  </c:forEach>
                </ul>
              </div>
              <input type="hidden" name="minPrice" value="${filterBean.minPrice}">
              <input type="hidden" name="maxPrice" value="${filterBean.maxPrice}">
              <div class="common-filter">
                <div class="head">Price</div>
                <div class="price-range-area">
                  <div id="price-range"></div>
                  <div class="value-wrapper d-flex">
                    <div class="price">Price:</div>
                    <span>$</span>
                    <div id="lower-value"></div>
                    <div class="to">to</div>
                    <span>$</span>
                    <div id="upper-value"></div>
                  </div>
                </div>
              </div>
            </div>

            <input type="hidden" name="sorter" id="sorter">
            <input type="hidden" name="numberItems" id="numberItems" value="${filterBean.numberItems}">
            <button class="btn btn-lg btn-primary" type="submit" style="width: 100%">Search</button>
          </form>
        </div>
        <div class="col-xl-9 col-lg-8 col-md-7">
          <!-- Start Filter Bar -->
          <div class="filter-bar d-flex flex-wrap align-items-center">
            <div class="sorting mr-auto">
              Sorting by name
              <button id="sorterNameAsc" value="products.name">&#8657</button>
              <button id="sorterNameDesc" value="products.name DESC">&#8659</button>
              Sorting by price
              <button id="sorterPriceAsc" value="price">&#8657</button>
              <button id="sorterPriceDesc" value="price DESC">&#8659</button>

              Number of products
              <button id="numberItems5" value="5">5</button>
              <button id="numberItems10" value="15">15</button>
              <button id="numberItems15" value="30">30</button>
            </div>

            <c:set var="pathPage">
              http://localhost:8888/products?name=${filterBean.name}&category=${currentCategory}&manufacturer=${currentManufacturer}&minPrice=${filterBean.minPrice}&maxPrice=${filterBean.maxPrice}&sorter=${filterBean.sorter}&numberItems=${filterBean.numberItems}
            </c:set>

            <ownTag:pagination pathPage="${pathPage}" name="${filterBean.name}" numberProducts="${numberProducts}"
              numberItems="${filterBean.numberItems}" numberPage="${filterBean.numberPage}" />

          </div>
          <!-- End Filter Bar -->

          <!-- Start Best Seller -->
          <section class="lattest-product-area pb-40 category-list">
            <div class="row">
              <c:choose>
                <c:when test="${not empty products}">
                  <c:set var="k" value="0" />
                  <c:forEach var="product" items="${products}">
                    <c:set var="k" value="${k+1}" />
                    <div class="col-md-6 col-lg-4">
                      <div class="card text-center card-product">
                        <div class="card-product__img">
                          <img class="card-img" src="resources/img/products/${product.id}.jpg" height="240" width="140"
                            alt="">
                          <ul class="card-product__imgOverlay">
                            <li>
                              <!-- --------------------------------------------------------------------------------------------------------------------------------------- -->
                                <button value="${product.id}" onclick="addProduct(this)"><i class="ti-shopping-cart"></i></button>
                             
                            </li>
                          </ul>
                        </div>
                        <div class="card-body">
                          <c:out value="${manufacturers[(product.manufacturerId).longValue()]}" />
                          <h4 class="card-product__title"><a href="#">${product.name}</a></h4>
                          <p class="card-product__price">${product.price}$</p>
                        </div>
                      </div>
                    </div>
                  </c:forEach>
                </c:when>
                <c:otherwise>
                  <h2 style="margin-left: 17%">There are no products by selected filter</h2>
                </c:otherwise>
              </c:choose>
            </div>
          </section>
          <!-- End Best Seller -->

          <div class="filter-bar d-flex flex-wrap align-items-center">
            <ownTag:pagination pathPage="${pathPage}" name="${filterBean.name}" numberProducts="${numberProducts}"
              numberItems="${filterBean.numberItems}" numberPage="${filterBean.numberPage}" />
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- ================ category section end ================= -->
  <%@ include file="/WEB-INF/jspf/footer.jspf" %>
  <script src="resources/js/updateCart.js"></script>
  <script src="resources/vendors/nouislider/nouislider.min.js"></script>
  <script src="resources/js/listener.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>