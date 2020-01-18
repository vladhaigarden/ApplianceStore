<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<c:set var="title" value="ApplianceSmart Shop" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
    <body>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
          <main class="site-main">
            <!--================ Hero banner start =================-->
            <section class="hero-banner">
              <div class="container">
                <div class="row no-gutters align-items-center pt-60px">
                  <div class="col-5 d-none d-sm-block">
                    <div class="hero-banner__img">
                      <img class="img-fluid" src="resources/img/home/hero-banner.png" alt="">
                    </div>
                  </div>
                  <div class="col-sm-7 col-lg-6 offset-lg-1 pl-4 pl-md-5 pl-lg-0">
                    <div class="hero-banner__content">
                      <h4><fmt:message key="main_tagline"/></h4>
                      <h1><fmt:message key="secondary_tagline"/></h1>
                    </div>
                  </div>
                </div>
              </div>
            </section>
            <!--================ Hero banner start =================-->

            <!--================ Hero Carousel start =================-->
            <section class="section-margin mt-0">
              <div class="owl-carousel owl-theme hero-carousel">
                <div class="hero-carousel__slide">
                  <img src="resources/img/home/hero-slide1.png" alt="" class="img-fluid">
                  <a href="#" class="hero-carousel__slideOverlay">
                    <h3>Wireless Headphone</h3>
                    <p>Accessories Item</p>
                  </a>
                </div>
                <div class="hero-carousel__slide">
                  <img src="resources/img/home/hero-slide2.png" alt="" class="img-fluid">
                  <a href="#" class="hero-carousel__slideOverlay">
                    <h3>Wireless Headphone</h3>
                    <p>Accessories Item</p>
                  </a>
                </div>
                <div class="hero-carousel__slide">
                  <img src="resources/img/home/hero-slide3.png" alt="" class="img-fluid">
                  <a href="#" class="hero-carousel__slideOverlay">
                    <h3>Wireless Headphone</h3>
                    <p>Accessories Item</p>
                  </a>
                </div>
              </div>
            </section>
            <!--================ Hero Carousel end =================-->


            <!-- ================ trending product section start ================= -->
            <section class="section-margin calc-60px">
              <div class="container">
                <div class="section-intro pb-60px">
                  <p><fmt:message key="popular_item"/></p>
                  <h2>Trending <span class="section-intro__style">Product</span></h2>
                </div>
                <div class="row">
                  <div class="col-md-6 col-lg-4 col-xl-3">
                    <div class="card text-center card-product">
                      <div class="card-product__img">
                        <img class="card-img" width="100%" src="resources/img/product/product1.jpeg" alt="">
                        <ul class="card-product__imgOverlay">
                          <li><button><i class="ti-search"></i></button></li>
                          <li><button><i class="ti-shopping-cart"></i></button></li>
                          <li><button><i class="ti-heart"></i></button></li>
                        </ul>
                      </div>
                      <div class="card-body">
                        <p>Washer</p>
                        <h4 class="card-product__title"><a href="single-product.html">Equator All-in-One 13 lb Compact Combo Washer Dryer, White</a></h4>
                        <p class="card-product__price">$959.00</p>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6 col-lg-4 col-xl-3">
                    <div class="card text-center card-product">
                      <div class="card-product__img">
                        <img class="card-img" width="100%" src="resources/img/product/product2.jpeg" alt="">
                        <ul class="card-product__imgOverlay">
                          <li><button><i class="ti-search"></i></button></li>
                          <li><button><i class="ti-shopping-cart"></i></button></li>
                          <li><button><i class="ti-heart"></i></button></li>
                        </ul>
                      </div>
                      <div class="card-body">
                        <p>Washer</p>
                        <h4 class="card-product__title"><a href="single-product.html">Magic Chef 2.0 Cu. Ft. Ventless Washer/Dryer Combo in White</a></h4>
                        <p class="card-product__price">$759.99</p>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6 col-lg-4 col-xl-3">
                    <div class="card text-center card-product">
                      <div class="card-product__img">
                        <img class="card-img" width="100%" src="resources/img/product/product3.jpeg" alt="">
                        <ul class="card-product__imgOverlay">
                          <li><button><i class="ti-search"></i></button></li>
                          <li><button><i class="ti-shopping-cart"></i></button></li>
                          <li><button><i class="ti-heart"></i></button></li>
                        </ul>
                      </div>
                      <div class="card-body">
                        <p>Electric shaver</p>
                        <h4 class="card-product__title"><a href="single-product.html">RBraun Series Men's Electric Foil Shaver, Wet and Dry Razor with Clean</a></h4>
                        <p class="card-product__price">$249.99</p>
                      </div>
                    </div>
                  </div>
                  <div class="col-md-6 col-lg-4 col-xl-3">
                    <div class="card text-center card-product">
                      <div class="card-product__img">
                        <img class="card-img" width="100%" src="resources/img/product/product4.jpeg" alt="">
                        <ul class="card-product__imgOverlay">
                          <li><button><i class="ti-search"></i></button></li>
                          <li><button><i class="ti-shopping-cart"></i></button></li>
                          <li><button><i class="ti-heart"></i></button></li>
                        </ul>
                      </div>
                      <div class="card-body">
                        <p>Fridge</p>
                        <h4 class="card-product__title"><a href="single-product.html">Arctic King 3.2 Cu Ft Two Door Mini Fridge with Freezer, Stainless Steel</a></h4>
                        <p class="card-product__price">$139.00</p>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </section>
            <!-- ================ trending product section end ================= -->
          </main>
          <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>