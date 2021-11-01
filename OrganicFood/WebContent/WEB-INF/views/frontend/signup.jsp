<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">

    <!-- Title Page-->
    <title>Au Register Forms by Colorlib</title>
	<base href="${pageContext.servletContext.contextPath}/">
    <!-- Icons font CSS-->
    <link href="resources/frontend/signup/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="resources/frontend/signup/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <!-- Font special for pages-->
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Vendor CSS-->
    <link href="resources/frontend/signup/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="resources/frontend/signup/vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="resources/frontend/signup/css/main.css" rel="stylesheet" media="all">
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>

<body>
    <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
        <div class="wrapper wrapper--w680">
            <div class="card card-4">
                <div class="card-body">
                    <div class="header">
                        <img src="resources/images/logoOF.svg" alt="">
                        <h2 class="title">ĐĂNG KÝ</h2>
                    </div>
                    <form:form method="POST" action="create-account.html" modelAttribute="user">
                    <c:if test="${not empty message}">
                    
                    	<div class="mess-alert">
                    		${message }
                    	</div>
                    </c:if>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Họ và tên</label>
                                    <form:input class="input--style-4" type="text" path="name" placeholder="Nhập họ tên"/>
                                    <form:errors path = "name" style="color: brown;" />
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Số điện thoại</label>
                                    <input name="phone" class="input--style-4" placeholder="Nhập số điện thoại" value=""/>
                                    <form:errors path = "phone" style="color: brown;" />
                                </div>
                            </div>
                        </div>
                        <div class="input-group">
                            <label class="label">Email</label>
                            <div class="rs-select2 js-select-simple select--no-search">
                                    <form:input class="input--style-4" type="text" path="email"  placeholder="Nhập email"/>
                                    <form:errors path = "email" style="color: brown;" />
                            </div>
                        </div>
                        <div class="input-group">
                            <label class="label">Địa chỉ</label>
                            <div class="rs-select2 js-select-simple select--no-search">
                                    <form:input class="input--style-4" type="text" path="address"  placeholder="Nhập địa chỉ"/>
                                    <form:errors path = "address" style="color: brown;" />
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Mật khẩu</label>
                                    <form:input class="input--style-4" type="password" path="password"  placeholder="Nhập mật khẩu"/>
                                    <form:errors path = "password" style="color: brown;" />
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Xác nhận mật khẩu</label>
                                    <input class="input--style-4" type="password" name="repassword" placeholder="Xác nhận mật khẩu">
                                    <div style="color: brown;">${repass }</div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Capcha</label>
                                   	<div class="g-recaptcha" data-sitekey="6Le4CwAdAAAAAMG6mqPUWzPEsrEkrT1p1WsJRZi1"></div>
                                   	<c:if test="${not empty capcha}">
                                   		<div style="color: brown;">${capcha }</div>
                                   	</c:if>
                                </div>
                            </div>
                            
                        </div>
                        
                        
                        <div class="footer">
                            <p href="">Đã có  tài khoản? <a href="login.html">Đăng nhập ngay</a></p>
                            <div >
                                <button class="btn btn--radius-2 btn--blue" type="submit">Đăng ký</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>

    <!-- Jquery JS-->
    <script src="resources/frontend/signup/vendor/jquery/jquery.min.js"></script>
    <!-- Vendor JS-->
    <script src="resources/frontend/signup/vendor/select2/select2.min.js"></script>
    <script src="resources/frontend/signup/vendor/datepicker/moment.min.js"></script>
    <script src="resources/frontend/signup/vendor/datepicker/daterangepicker.js"></script>

    <!-- Main JS-->
    <script src="js/global.js"></script>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->