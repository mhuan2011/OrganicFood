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
	<link rel = "icon" href = "resources/images/icon.svg" type = "image/x-icon">
    <!-- Title Page-->
    <title>OrganicFood-	Signup</title>
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
    <link href="resources/css/substyle.css" rel="stylesheet" media="all">
</head>

<body>
    <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
        <div class="wrapper wrapper--w680">
            <div class="card card-4">
                <div class="card-body">
                    <div class="header">
                        <img src="resources/images/logoOF.svg" alt="">
                        <h2 class="title">????NG K??</h2>
                    </div>
                    <form:form method="POST" action="create-account.html" modelAttribute="user">
                    <c:if test="${not empty message}">
	                    <div class="alert">
						  <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
						  ${message }
						</div>
                    	
                    </c:if>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">H??? v?? t??n</label>
                                    <form:input class="input--style-4" type="text" path="name" placeholder="Nh???p h??? t??n"/>
                                    <form:errors path = "name" style="color: brown;" />
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">S??? ??i???n tho???i</label>
                                    <input name="phone" class="input--style-4" placeholder="Nh???p s??? ??i???n tho???i" value="${phone }"/>
                                    <form:errors path = "phone" style="color: brown;" />
                                </div>
                            </div>
                        </div>
                        <div class="input-group">
                            <label class="label">Email</label>
                            <div class="rs-select2 js-select-simple select--no-search">
                                    <form:input class="input--style-4" type="email" path="email"  placeholder="Nh???p email"/>
                                    <form:errors path = "email" style="color: brown;" />
                            </div>
                        </div>
                        <div class="input-group">
                            <label class="label">?????a ch???</label>
                            <div class="rs-select2 js-select-simple select--no-search">
                                    <form:input class="input--style-4" type="text" path="address"  placeholder="Nh???p ?????a ch???"/>
                                    <form:errors path = "address" style="color: brown;" />
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">M???t kh???u</label>
                                    <form:input class="input--style-4" type="password" path="password"  placeholder="Nh???p m???t kh???u"/>
                                    <form:errors path = "password" style="color: brown;" />
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">X??c nh???n m???t kh???u</label>
                                    <input class="input--style-4" type="password" name="repassword" placeholder="X??c nh???n m???t kh???u">
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
                            <p href="">???? c??  t??i kho???n? <a href="login.html">????ng nh???p ngay</a></p>
                            <div >
                                <button class="btn btn--radius-2 btn--blue" type="submit">????ng k??</button>
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