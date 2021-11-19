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
                            <li><a href="shop/shop.html">Shop</a></li>
                            <li class="active"><a href="./blog.html">Blog</a></li>
                            <li><a href="./contact.html">Contact</a></li>
                            <li><a href="ordered.html">Ordered</a></li>
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
   

    <!-- Blog Details Hero Begin -->
    <section class="blog-details-hero set-bg" data-setbg="resources/frontend/img/blog/details/details-hero.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="blog__details__hero__text">
                        <h2>${blog.getTieuDe() }</h2>
                        <ul>
                            <li>${blog.getNV().getFullName() }</li>
                            <li>${blog.getNgay() }</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Details Hero End -->

    <!-- Blog Details Section Begin -->
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
                           <div class="blog__sidebar__item">
                         <h4>Categories</h4>
                         <ul>
                         <li ><a href="blog/blogView.html">All</a></li>
                         <c:forEach var="s" items="${categoryBlogs }" varStatus="count">
                         	 <li><a href="blog/searchByCategory/${s.getMaLoai() }.html">${s.getTenLoai()}</a></li>
                         </c:forEach>
                         </ul>
                       </div>
                        <div class="blog__sidebar__item">
                            <h4>Recent News</h4>
                            <div class="blog__sidebar__recent">
                            	 <c:forEach var="s" items="${recentBlogs }">
                         	 <a href="blog/blogDetails/${s.getMaBV() }.html" class="blog__sidebar__recent__item">
                                    <div class="blog__sidebar__recent__item__pic">
                                        <img style="width:50px; height: 50px" src="UploadFiles/${s.getHinhAnh()}" alt="">
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
                <div class="col-lg-8 col-md-7 order-md-1 order-1">
                    <div class="blog__details__text">
                        ${blog.getNoiDung() }
                    </div>
                    <div class="blog__details__content">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="blog__details__author">
                                    <div class="blog__details__author__pic">
                                        <img src="UploadFiles/NhanVien.png" alt="">
                                    </div>
                                    <div class="blog__details__author__text">
                                        <h6>${blog.getNV().getFullName() }</h6>
                                        <span>Writer</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="blog__details__widget">
                                    <ul>
                                        <li><span>Categories:</span> ${blog.getLoaiBV().getTenLoai() }</li>
                                       
                                    </ul>
                                  
                                </div>
                                 <div class="blog__details__social">
                                        <a href="#"><i class="fa fa-facebook"></i></a>
                                        <a href="#"><i class="fa fa-twitter"></i></a>
                                        <a href="#"><i class="fa fa-google-plus"></i></a>
                                        <a href="#"><i class="fa fa-linkedin"></i></a>
                                        <a href="#"><i class="fa fa-envelope"></i></a>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Details Section End -->

    <!-- Related Blog Section Begin -->
    <section class="related-blog spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title related-blog-title">
                        <h2>Post You May Like</h2>
                    </div>
                </div>
            </div>
            <div class="row">
            <c:forEach var="s" items="${relatedBlogs }">
            	 <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="UploadFiles/${s.getHinhAnh()}" alt="">
                        </div>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i> ${s.getNgay() }</li>
                            </ul>
                            <h5><a href="blog/blogDetails/${s.getMaBV()}.html">${s.getTieuDe()}</a></h5>
                            <p>${s.getTrichDan() }</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
               
                
            </div>
        </div>
    </section>
    <!-- Related Blog Section End -->

      <%@include file="/WEB-INF/views/includes/frontend/footer.jsp"%>