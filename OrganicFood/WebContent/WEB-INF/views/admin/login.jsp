<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Organic Food Admin Login</title>
	<base href="${pageContext.servletContext.contextPath}/">
  <link rel="stylesheet" href="resources/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
  <link rel="stylesheet" href="resources/vendors/css/vendor.bundle.base.css">

  <link rel="stylesheet" href="resources/css/style.css">

  <link rel="shortcut icon" href="resources/images/favicon.png" />
  
  <style type="text/css">
  	.errorMessage {
  		font-size: 13px;
  		font-weight: 400;
  		opacity: 0.8;
  		font-style: italic;
  		margin-top: 10px;
  		padding-top: 20px;
  		margin-left: 2px;
  		color: #eb4f34;
  	}
  	.message {
  		text-align: center;
  		background: #ff6161;
  		color: #fff;
  		font-weight: 400;
  		padding: 5px 0;
  		border-radius: 3px;
  		opacity: 0.7;
  	}
	.form-check .form-check-label input[type="checkbox"] + .input-helper:before {
		border: solid #38b76c;
	}
	.form-check .form-check-label input[type="checkbox"]:checked + .input-helper:after{
		background: #38b76c;
	}
	.form-check .form-check-label input[type="checkbox"]:checked + .input-helper:before {
		background: linear-gradient(to right, #38ef7d, #11998e);
	}
  </style>
</head>

<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth">
        <div class="row w-100">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left p-5">
              <div class="brand-logo">
                <img src="resources/images/logoOF.svg">
              </div>
              <h4>Hello! let's get started</h4>
              <h6 class="font-weight-light">Đăng nhập để tiếp tục</h6>
              <form:form class="pt-3" action="admin/login.html" method="post" modelAttribute="account">
              	<c:if test="${message != null}">
              		<p class="message">${message }</p>
              	</c:if>
              		
              	
                <div class="form-group">
                  
                  <form:input path="username" class="form-control form-control-lg" id="exampleInputEmail1" placeholder="Enter ID"/>
                  <form:errors class="errorMessage" path="username"/>
                </div>
                <div class="form-group">
                  <form:input path ="password"  type="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="Password"/>
                  <form:errors class="errorMessage" path="password"/>
                </div>
                <div class="mt-3">
                  <button type="submit" class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn">Đăng nhập</button>
                </div>
                <div class="my-2 d-flex justify-content-between align-items-center">
                  <div class="form-check">
                    <label class="form-check-label text-muted">
                      <input type="checkbox" class="form-check-input">
                      Giữ tài khoản đăng nhập
                    </label>
                  </div>
                  <a href="#" class="auth-link text-black">Quên mật khẩu?</a>
                </div>

              </form:form>
            </div>
          </div>
        </div>
      </div>
      <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>

  <script src="resources/vendors/js/vendor.bundle.base.js"></script>
  <script src="resources/vendors/js/vendor.bundle.addons.js"></script>

  <script src="resources/js/off-canvas.js"></script>
  <script src="resources/js/misc.js"></script>

</body>

</html>
