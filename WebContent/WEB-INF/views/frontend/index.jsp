<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/frontend/header.jsp"%>
<div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="header__logo">
                        <a href="./index.html"><img src="resources/frontend/img/logo.png" alt=""></a>
                    </div>
                </div>
                <div class="col-lg-6">
                    <nav class="header__menu">
                        <ul>
                            <li class="active"><a href="./index.html">Home</a></li>
                            <li><a href="shop/shop.html">Shop</a></li>
                            
                            <li><a href="blog/blogView.html">Blog</a></li>
                            <li><a href="contact.html">Contact</a></li>
                            <li><a href="ordered.html">Ordered</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__cart">
                        <ul>
                            <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                            <li><a href="cart.html"><i class="fa fa-shopping-bag"></i> <span>0</span></a></li>
                        </ul>
                        
            			<div class="header__cart__price">item: <span>0</span></div>
                    </div>
                </div>
            </div>
            <div class="humberger__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    <!-- Header Section End -->

    <!-- Hero Section Begin -->
    <section class="hero">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="hero__categories">
                        <div class="hero__categories__all">
                            <i class="fa fa-bars"></i>
                            <span>All departments</span>
                        </div>
                        <ul>
                        <c:forEach var="s" items="${categoryProducts }">
                        	<li> <a href="shop/shop-category/${s.getId() }.html"> ${s.getName()}</a> </li>
                        </c:forEach>
                	</ul>
                       
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                             <form action="shop/search.html" method="post">
                                <input name="search" type="text" value="${searchText }"  class="form-control" placeholder="Nhập tên sản phẩm cần tìm" aria-label="Recipient's username" aria-describedby="basic-addon2">
                                <button name="btnsearch" type="submit" class="site-btn">SEARCH</button>
                            </form>
                        </div>
                        <div class="hero__search__phone">
                            <div class="hero__search__phone__icon">
                                <i class="fa fa-phone"></i>
                            </div>
                            <div class="hero__search__phone__text">
                                <h5>+65 11.188.888</h5>
                                <span>support 24/7 time</span>
                            </div>
                        </div>
                    </div>
                    <!-- <div class="hero__item set-bg" data-setbg="resources/frontend/img/hero/banner.jpg">
                        <div class="hero__text">
                            <span>FRUIT FRESH</span>
                            <h2>Vegetable <br />100% Organic</h2>
                            <p>Free Pickup and Delivery Available</p>
                            <a href="shop/shop.html" class="primary-btn">SHOP NOW</a>
                        </div>
                    </div> -->
                    
                    <div class="slide-discount" >  
                        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" data-interval="2500">
							  <ol class="carousel-indicators">
							    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
							    <c:forEach var="s" items="${discountList }">
							    	<li data-target="#carouselExampleIndicators" ></li>
							    </c:forEach>
							    
		
							  </ol>
							  <div class="carousel-inner" style="height: 400px;">
							  		<div class="carousel-item active">
								      <!-- <img class="d-block w-100" src="UploadFiles/cat-1.jpg" alt="First slide" style="height: 500px"> -->
								      <div class="hero__item set-bg" data-setbg="resources/frontend/img/hero/banner.jpg">
								      <div class="hero__text">
                            <span>FRUIT FRESH</span>
                            <h2>Vegetable <br />100% Organic</h2>
                            <p>Free Pickup and Delivery Available</p>
                            <a href="shop/shop.html" class="primary-btn">SHOP NOW</a>
                        </div> 
								      </div>
								  </div>
							  	<c:forEach var="s" items="${discountList }">
                        		  <div class="carousel-item ">
								      <a href="discount/${s.getId() }.html"><img class="d-block w-100" src="UploadFiles/Discount/${s.getImage() }" alt="First slide" style="height: 500px">
								      	
								      </a>
								 		
								  </div>
                        		</c:forEach>
							    
							    
							  </div>
							  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
							    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
							    <span class="sr-only">Previous</span>
							  </a>
							  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
							    <span class="carousel-control-next-icon" aria-hidden="true"></span>
							    <span class="sr-only">Next</span>
							  </a>
							</div>
                        
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->
    <!-- Categories Section Begin -->
    <section class="categories">
        <div class="container">
            <div class="row">
                <div class="categories__slider owl-carousel">
                <c:forEach var="s" items="${categoryProducts }">
                	 <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="UploadFiles/${s.getImage() }">
                            <h5><a href="shop/shop-category/${s.getId() }.html"> ${s.getName() }</a></h5>
                        </div>
                    </div>
                </c:forEach>
                   
                </div>
            </div>
        </div>
    </section>
    <!-- Categories Section End -->

    <!-- Featured Section Begin -->
    <section class="featured spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>Featured Product</h2>
                    </div>
                    <div class="featured__controls">
                        <ul>
                        	<li class="active" data-filter="*">All</li>
                        	<c:forEach var="s" items="${featuredCategory }">
                        		  <li data-filter=".${s.getId() }">${s.getName() }</li>
                        	</c:forEach>
                            
                       
                        </ul>
                    </div>
                </div>
            </div>
           <div class="row featured__filter">
            <c:forEach var="s" items="${featuredCategory }">
            	<c:forEach var="p" items="${s.getProduct() }">
            	 <div class="col-lg-3 col-md-4 col-sm-6 mix ${s.getId()}">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="UploadFiles/${p.getImage() }">
                            <ul class="featured__item__pic__hover">
                                <li><a href="shop/shop-detail/${p.getId() }.html"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="shop/shop-detail/${p.getId() }.html">${p.getName() }</a></h6>
                            <h5>${p.getPrice() }</h5>
                        </div>
                    </div>
                </div>
                </c:forEach>
            </c:forEach>
              
              
            </div>
        </div>
    </section>
    <!-- Featured Section End -->



   
 <!-- Blog Section Begin -->
    <section class="from-blog spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title from-blog__title">
                        <h2>From The Blog</h2>
                    </div>
                </div>
            </div>
            <div class="row">
            <c:forEach var="s" items="${recentBlogs }">
            	  <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img  src="UploadFiles/${s.getHinhAnh() }" alt="">
                        </div>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i> ${s.getNgay() }</li>
                            </ul>
                            <h5><a href="#">${s.getTieuDe() }</a></h5>
                            <p>${s.getTrichDan() }</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
              
               
            </div>
        </div>
    </section>
    <!-- Blog Section End -->


  <%@include file="/WEB-INF/views/includes/frontend/footer.jsp"%>