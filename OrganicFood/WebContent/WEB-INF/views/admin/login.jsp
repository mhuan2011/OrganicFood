<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
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
  
  <link rel="stylesheet" href="resources/css/login_style.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>
<script type="text/javascript">
              	$(function() {
					$("a[data-lang]".click(function() {
						var lang = $(this).attr("data-lang");
						$get("admin/login.html?language="+lang, function() {
							location.reload();
						});
						return false;
					});
				});
              </script>

  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth">
        <div class="row w-100">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left p-5">
              <div class="brand-logo">
                <img src="resources/images/logoOF.svg">
                
                <div class="language-group">
                	<div class="language-item"><a href="admin/login.html?language=vn" data-lang="vn"><img src="resources/images/language/vn-flag.png"> VN</a></div>
                	<div class="language-item"><a href="admin/login.html?language=en" data-lang="en"><img src="resources/images/language/en-flag.png"> EN</a></div>
                </div>
              </div>
              
              
              
              <h4>Hello! let's get started</h4>
              <h6 class="font-weight-light"><s:message code="adminlogin.Sub.title" /> </h6>
              <form:form class="pt-3" action="admin/login.html" method="post" modelAttribute="account">
              	<c:if test="${message != null}">
              		<p class="message">${message }</p>
              	</c:if>
              		
              	
                <div class="form-group">
                  <s:message code="adminlogin.Id.placeholder" var="placeholderId"/> 
                  <form:input path="username" class="form-control form-control-lg" id="exampleInputEmail1" placeholder="${placeholderId }"/>
                  <form:errors class="errorMessage" path="username"/>
                </div>
                <div class="form-group">
                	<s:message code="adminlogin.Password.placeholder" var="placeholderPass"/> 
                  <form:input path ="password"  type="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="${placeholderPass }"/>
                  <form:errors class="errorMessage" path="password"/>
                </div>
                <div class="mt-3">
                  <button type="submit" class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn"><s:message code="adminlogin.Button.title" /></button>
                </div>
                <div class="my-2 d-flex justify-content-between align-items-center">
                  <div class="form-check">
                    <label class="form-check-label text-muted">
                      <input type="checkbox" class="form-check-input">
                      <s:message code="adminlogin.Remember.title" /> 
                    </label>
                  </div>
                  <a href="#" class="auth-link text-black"><s:message code="adminlogin.ForgotPassword.title" /></a>
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
