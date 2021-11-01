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
                            <li><a href="index.html">Home</a></li>
                            <li><a href="shop.html">Shop</a></li>
                      	
                            <li class="active"><a href="blog/index.html">Blog</a></li>
                            <li><a href="./contact.html">Contact</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__cart">
                        <ul>
                            <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                            <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
                        </ul>
                        <div class="header__cart__price">item: <span>$150.00</span></div>
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
    <section class="hero hero-normal">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="hero__categories">
                        <div class="hero__categories__all">
                            <i class="fa fa-bars"></i>
                            <span>All departments</span>
                        </div>
                        <ul>
                            <li><a href="#">Fresh Meat</a></li>
                            <li><a href="#">Vegetables</a></li>
                            <li><a href="#">Fruit & Nut Gifts</a></li>
                            <li><a href="#">Fresh Berries</a></li>
                            <li><a href="#">Ocean Foods</a></li>
                            <li><a href="#">Butter & Eggs</a></li>
                            <li><a href="#">Fastfood</a></li>
                            <li><a href="#">Fresh Onion</a></li>
                            <li><a href="#">Papayaya & Crisps</a></li>
                            <li><a href="#">Oatmeal</a></li>
                            <li><a href="#">Fresh Bananas</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                            <form action="#">
                                <div class="hero__search__categories">
                                    All Categories
                                    <span class="arrow_carrot-down"></span>
                                </div>
                                <input type="text" placeholder="What do yo u need?">
                                <button type="submit" class="site-btn">SEARCH</button>
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
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="resources/frontend/img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Blog</h2>
                        <div class="breadcrumb__option">
                            <a href="./index.html">Home</a>
                            <span>Blog</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Blog Section Begin -->
    <section class="blog spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-5">
                    <div class="blog__sidebar">
                        <div class="blog__sidebar__search">
                            <form action="blog/searchByWord.html" method="POST">
                                <input name="search" type="text" placeholder="Search..."/>
                                <button type="submit"><span class="icon_search"></span></button>
                            </form>
                        </div>
                        
                        
                        
 <!-- load dữ liệu categoryBlog lên -->
                        
                         <div class="blog__sidebar__item">
                         <h4>Categories</h4>
                         <ul>
                         <li ><a href="blog/blogView.html">All</a></li>
                         <c:forEach var="s" items="${categoryBlogs }" varStatus="count">
                         	 <li><a href="blog/searchByCategory/${s.getMaLoai() }.html">${s.getTenLoai()}</a></li>
                         </c:forEach>
                         </ul>
                       </div>
                      
                            
 <!-- ========================Load recent blog lên================================================================================= -->  
                     
                        <div class="blog__sidebar__item">
                            <h4>Recent News</h4>
                            <div class="blog__sidebar__recent">
                            	 <c:forEach var="s" items="${blogs }" varStatus="count">
                         	 <a href="blog/blogDetail/${s.getMaBV() }.html" class="blog__sidebar__recent__item">
                                    <div class="blog__sidebar__recent__item__pic">
                                        <img style="width:50px; height: 50px" src="UploadFiles/${s.getHinhAnh() }" alt="">
                                    </div>
                                    <div class="blog__sidebar__recent__item__text">
                                        <h6>${s.getTieuDeXuongDong() }</h6>
                                        <span>${s.getNgay() }</span>
                                    </div>
                                </a>
                         </c:forEach>
                            </div>
                        </div>
                        </div>
                        </div>
                       
                
                                        
 <!--=========================================load bài viết lên=============================  -->
                <div class="col-lg-8 col-md-7">
                    <div class="row">
                    <c:forEach var="s" items="${blogs }">
  <div class="col-lg-6 col-md-6 col-sm-6">
       <div class="blog__item">
           <div class="blog__item__pic">
               <img style="width:200px; height: 200px"src="UploadFiles/${s.getHinhAnh() }" alt="">
           </div>
           <div class="blog__item__text">
               <ul>
                   <li><i class="fa fa-calendar-o"></i> ${ s.getNgay()}</li>
                   <li><i class="fa fa-comment-o"></i> 5</li>
               </ul>
               <h5><a href="#">${s.getTieuDe() }</a></h5>
               <p>${s.getTrichDan() }</p>
               <a href="#" class="blog__btn">READ MORE <span class="arrow_right"></span></a>
           </div>
       </div>
   </div>
   
   </c:forEach>
                        <div class="col-lg-12">
                            <div class="product__pagination blog__pagination">
                                <a href="#">1</a>
                                <a href="#">2</a>
                                <a href="#">3</a>
                                <a href="#"><i class="fa fa-long-arrow-right"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Section End -->


  <%@include file="/WEB-INF/views/includes/frontend/footer.jsp"%>