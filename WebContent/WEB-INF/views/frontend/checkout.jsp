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
                            <li ><a href="index.html">Home</a></li>
                            <li class="active"><a href="shop/shop.html">Shop</a></li>
                            
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
                            <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>
                            	<%
                            		if (session.getAttribute("cartNumber") != null){
                            			int n = (int)session.getAttribute("cartNumber");
                            			out.println(n);
                            		}else {
                            			out.println(0);
                            		}
                            	%>
                            </span></a></li>
                        </ul>
                        <div class="header__cart__price">item: <span>$150.00</span></div>
                    </div>
                </div>
            </div>
            <div class="humberger__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
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
                            <li><a href="#">Fruit &amp; Nut Gifts</a></li>
                            <li><a href="#">Fresh Berries</a></li>
                            <li><a href="#">Ocean Foods</a></li>
                            <li><a href="#">Butter &amp; Eggs</a></li>
                            <li><a href="#">Fastfood</a></li>
                            <li><a href="#">Fresh Onion</a></li>
                            <li><a href="#">Papayaya &amp; Crisps</a></li>
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

<section class="breadcrumb-section set-bg" data-setbg="resources/frontend/img/breadcrumb.jpg" style="background-image: url(&quot;img/breadcrumb.jpg&quot;);">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Check out</h2>
                        <div class="breadcrumb__option">
                            <a href="./index.html">Home</a>
                            <span>Check out</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

	<section class="checkout spad">
        <div class="container">
			<div class="checkout__form">
                <h4>Billing Details</h4>
                
                <form:form action="shop/insertOrder.html" method="post" modelAttribute="DatHang">
                    <div class="row">
                        <div class="col-lg-8 col-md-6">
                            	  <div class="form-group">
			                      	<label>M?? s??? ????n ?????t h??ng:</label> 
			                    	<form:input path="masoddh"  type="text" class="form-control form-control-lg" readonly="true"/>
				                  </div>
			                  	  <div class="form-group">
			                      	<label>S??? ??i???n tho???i:</label> 
			                    	<form:input path="khachhang.phone"  type="text" class="form-control form-control-lg" readonly="true"/>
				                  </div>
				                  <div class="form-group">
			                      	<label>Ng??y:</label> 
			                    	<form:input path="ngay"  type="text" class="form-control form-control-lg" readonly="true"/>
				                  </div>
					              <div class="form-group">
			                      	<label>M?? NV:</label> 
			                    	<form:input path="nhanvien.id"  type="text" class="form-control form-control-lg" readonly="true"/>
				                  </div>
				                  <div class="form-group">
									<label>M?? DV:</label>
									<br>
									<form:select path="dvvc.madv" class="form-control form-control-lg" items="${dsDonVi }">
									</form:select>
									<br>
								  </div>
				                  <div class="form-group">
			                      	<label>M?? KM:</label> 
			                    	<form:input path="makm.id"  type="text" class="form-control form-control-lg" readonly="true"/>
				                  </div>
                        </div>
                        <div class="col-lg-4 col-md-6">
                            <div class="checkout__order">
                                <h4>Your Order</h4>
                                <div class="checkout__order__products">Products <span>Total</span></div>
                                
                                <ul>
                                	<c:forEach var="v" items="${listNS }" varStatus="status">
                                		<li>${v.getName() } 
                                		<span>
                                		<fmt:formatNumber type = "number" 
         										maxFractionDigits = "0" value = "${v.getPrice()*listSLNS.get(status.index)-v.getPrice()*listSLNS.get(status.index)*v.getDiscount() }" />
                                		
                                		</span>
                                		</li>
                                	</c:forEach>
                                </ul>
                                <div class="checkout__order__subtotal">Subtotal <span>${tongtien }</span></div>
                                <div class="checkout__order__total">Total <span>${tiendagiam }</span></div>
                                
                                
                                <button type="submit" class="site-btn">PLACE ORDER</button>
                            </div>
                        </div>
                    </div>
                </form:form>
                
            </div>
         </div>
      </section>



<%@include file="/WEB-INF/views/includes/frontend/footer.jsp"%>