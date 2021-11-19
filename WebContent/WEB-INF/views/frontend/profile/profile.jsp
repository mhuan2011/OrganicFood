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
    <!-- Header Section End -->

   
    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="resources/frontend/img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Profile</h2>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <div class="container rounded bg-white mt-5 mb-5">
	    
	    <form:form action="profile/edit-profile.html" method="post" modelAttribute="Profile">
	    <div class="row">
	        <div class="col-md-3 border-right">
	            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">${Profile.name }</span><span class="text-black-50">${Profile.phone }</span><span> </span></div>
	        </div>
	        <div class="col-md-5 border-right">
	            <div class="p-3 py-5">
	                <div class="d-flex justify-content-between align-items-center mb-3">
	                    <h4 class="text-right">Profile Settings</h4>
	                </div>

	                <div class="row mt-3">
	                    <div class="col-md-12">
		                    <label class="labels">Họ tên</label>
		                    <form:input path="name" type="text" class="form-control" placeholder="enter phone number" value=""/>
	                    </div>
	                    <div class="col-md-12">
		                    <label class="labels">Địa chỉ</label>
		                    <form:input path="address" type="text" class="form-control" placeholder="enter address line 1" value=""/>
	                    </div>
	                    <div class="col-md-12">
		                    <label class="labels">Mật khẩu</label>
		                    <form:input path="password" type="text" class="form-control" placeholder="enter address line 2" value=""/>
	                    </div>
	                    <div class="col-md-12">
		                    <label class="labels">Email</label>
		                    <form:input path="email" type="text" class="form-control" placeholder="enter address line 2" value=""/>
	                    </div>
	                    
	                </div>
	                <div class="mt-5 text-center"><button class="btn btn-primary profile-button">Save</button></div>
	            </div>
	        </div>
	       </div> 
		</form:form>
	    
	</div>


  <%@include file="/WEB-INF/views/includes/frontend/footer.jsp"%>