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
                      	
                            <li class="active"><a href="blog/blogView.html">Blog</a></li>
                            <li><a href="./contact.html">Contact</a></li>
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
                        <h2>Shopping Cart</h2>
                        <div class="breadcrumb__option">
                            <a href="./index.html">Home</a>
                            <span>Shopping Cart</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

	<section class="shoping-cart spad">
        <div class="container">
        <form action="shop/updateCart.html">
            <div class="row">
                <div class="col-lg-12">
	                
	                    <div class="shoping__cart__table">
	                        <table>
	                            <thead>
	                                <tr>
	                                	<th>STT</th>
	                                    <th class="shoping__product">Products</th>
	                                    <th>Price</th>
	                                    <th>Quantity</th>
	                                    <th>Total</th>
	                                    <th></th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                            	<% int count = 1; %>
		                            <c:forEach var="ns" items="${listNS }" varStatus="count">
		                                <tr>
		                                	<td>
		                                		<%=count++%>
		                                	</td>
		                                    <td class="shoping__cart__item">
		                                        <c:if test="${ns.getImage() != null}">
					                      			<img alt="" src="UploadFiles/${ns.getImage() }" style="width: 100px; height: 50px; border-radius: 4px;">
					                      		</c:if>
		                                        <h5>${ns.getName() }</h5>
		                                    </td>
		                                    <td class="shoping__cart__price">
		                                        ${ns.getPrice()-ns.getPrice()*ns.getDiscount() }
		                                    </td>
		                                    <td class="shoping__cart__quantity">
		                                        <div >
		                                            <div >
		                                            	
		                                            	<input id="ip${ns.getId() }" name="ip${ns.getId() }" type="number" value=${listSLNS.get(count.index) } onchange="changeNumber()">
		                                            	
		                                           	</div>
		                                        </div>
		                                    </td>
		                                    
		                                    <td class="shoping__cart__total">
		                                        ${ns.getPrice()*listSLNS.get(count.index)-ns.getPrice()*ns.getDiscount()*listSLNS.get(count.index)}
		                                    </td>
		                                    <td class="shoping__cart__item__close">
		                                        <a href="shop/deleteShoppingCart/<%=count%>.html">
		                                        Xóa
		                                        </a>
		                                    </td>
		                                </tr>
		                                
		                             </c:forEach>
	                                <script>
	                                	function changeNumber() {
											var quantity = document.getElementById("ip").value;
											alert(quantity);
											/*var pricett = quantity* 100; */
										}
	                                </script>
	                                
	                            </tbody>
	                        </table>
	                    </div>
	                    
                    <button type="submit" class="primary-btn cart-btn cart-btn-right"><span class="icon_loading"></span>
                            Update Cart</button>
                    
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__btns">
                        <a href="shop/shop.html" class="primary-btn cart-btn">CONTINUE SHOPPING</a>
                        
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="shoping__continue">
                        <div class="shoping__discount">
                            
                                <input id="discount" name="discount" type="text" placeholder="Enter your coupon code">
                                ${messDiscount }
                            
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="shoping__checkout">
                        <h5>Cart Total</h5>
                        <ul>
                            <li>Subtotal <span>${tongtien}</span></li>
                            <li>Total <span>${tiendagiam }</span></li>
                        </ul>
                        <a href="shop/checkout.html" class="primary-btn">PROCEED TO CHECKOUT</a>
                    </div>
                </div>
            </div>
            </form>
        </div>
    </section>



<%@include file="/WEB-INF/views/includes/frontend/footer.jsp"%>