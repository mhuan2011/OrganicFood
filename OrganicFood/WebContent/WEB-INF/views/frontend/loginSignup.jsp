<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="resources/login/fonts/icomoon/style.css">

    <link rel="stylesheet" href="resources/login/css/owl.carousel.min.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="resources/login/css/bootstrap.min.css">
    
    <!-- Style -->
    <link rel="stylesheet" href="resources/login/css/style.css">
	<base href="${pageContext.servletContext.contextPath}/">
    <title>Login</title>
  </head>
  <body>
  

  <div class="d-md-flex half">
    <div class="bg" style="background-image: url('resources/login/images/bg.jpg');"></div>
    <div class="contents">

      <div class="container">
        <div class="row align-items-center justify-content-center">
          <div class="col-md-12">
            <div class="form-block mx-auto">
              <div class="text-center mb-1">
              	<img alt="" src="resources/images/logoOF.svg">
              </div>
              <!-- <h3 class="text-uppercase"><strong>Đăng nhập</strong></h3> -->
              <c:if test="${not empty message}">
                    
                    	<div class="mess-alert">
                    		${message }
                    	</div>
                    </c:if>
              <form action="login.html" method="post" modelAttribute="user">
                <div class="form-group first">
                  <label for="username">Số điện thoại</label>
                  <input type="text" class="form-control" placeholder="Nhập số điện thoại" id="username" name="username" value="${phone }">
                  <p class="error">${phoneError }</p>
                </div>
                <div class="form-group last mb-3">
                  <label for="password">Mật khẩu</label>
                  <input type="password" class="form-control" placeholder="Nhập mật khẩu" id="password" name="password" value="${password }">
                  <p class="error">${passwordError }</p>
                </div>
                
                <div class="d-sm-flex mb-5 align-items-center">
                  <label class="control control--checkbox mb-3 mb-sm-0"><span class="caption">Ghi nhớ đăng nhập</span>
                    <input name="rememberMe" type="checkbox" checked="checked"/>
                    <div class="control__indicator"></div>
                  </label>
                  <span class="ml-auto"><a href="#" class="forgot-pass">Quên mật khẩu</a></span> 
                </div>

                <input type="submit" value="Đăng nhập" class="btn btn-block py-2 btn-success">

                <!-- <span class="text-center my-3 d-block">hoặc</span> -->
                
                
                
                <div class="signup">Chưa có tài khoản <a href="signup.html">Đăng ký ngay</a></div>
                <div class="">
                <a href="#" class="btn btn-block py-2 btn-facebook">
                  <span class="icon-facebook mr-3"></span> Đăng nhập với facebook
                </a>
                <a href="#" class="btn btn-block py-2 btn-google"><span class="icon-google mr-3"></span> Đăng nhập với Google</a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

    
  </div>
    
    

    <script src="resources/login/js/jquery-3.3.1.min.js"></script>
    <script src="resources/login/js/popper.min.js"></script>
    <script src="resources/login/js/bootstrap.min.js"></script>
    <script src="resources/login/js/main.js"></script>
  </body>
</html>
