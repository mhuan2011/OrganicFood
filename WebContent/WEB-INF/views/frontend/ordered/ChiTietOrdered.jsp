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
                      	
                            <li><a href="blog/blogView.html">Blog</a></li>
                            <li><a href="contact.html">Contact</a></li>
                            <li class="active"><a href="ordered.html">Ordered</a></li>
                            
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

   
    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="resources/frontend/img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Chi tiết đơn hàng</h2>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->
	<div class='container1 mx-auto mt-5 col-md-10 mt-100'>
    <table class="table table-borderless">
	                    <thead>
	                      <tr>
	                        <th style="width: 5%">
	                          STT
	                        </th>
	                        <th style="width: 12%">
	                          Mã số DDH
	                        </th>
	                        <th >
	                          Nông sản
	                        </th>
	                        <th >
	                          Số lượng
	                        </th>
	                        <th >
	                          Đơn giá
	                        </th>
	                        <th >
	                          Tổng tiền
	                        </th>
	                      </tr>
	                    </thead>
	                    <tbody>
	 
	                    <% int count = 1; %>
	                   <c:forEach var="s" items="${CTDDH }" varStatus="count">
		                    <tr>
		                        <td>
		                          <%=count++%>
		                          
		                        </td>
		                        <td>
		                         	${s.getMasoddh().getMasoddh() }
		                        </td>
		                        <td>
		                          	${s.getNongsan().getName() }
		                        </td>
		                        <td>
		                          	${s.getSoluong() }
		                        </td>
		                        <td>
		                          	${s.getDongia() }
		                        </td>
		                        <td>
		                          	${s.getSoluong()*s.getDongia() }
		                        </td>
		                      </tr>
	                    </c:forEach>
	                    </tbody>
	                  </table>
	</div>
  <%@include file="/WEB-INF/views/includes/frontend/footer.jsp"%>