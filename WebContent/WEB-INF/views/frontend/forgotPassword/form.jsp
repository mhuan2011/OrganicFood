<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="${pageContext.servletContext.contextPath}/">
    <link rel = "icon" href = "resources/images/icon.svg" type = "image/x-icon">
    <title>OrganicFood - Forgot Password</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/login/forgotpassword/style.css">

</head>
<body>
    <div class="container padding-bottom-3x mb-2 mt-5">
	    <div class="row justify-content-center">
	        <div class="col-lg-8 col-md-10">
	            <div class="forgot">
	                <div class="header-form">
	                	<h2><s:message code="forgotpass.title" /></h2>
	                	<div class="language-group">
		                	<div class="language-item"><a href="forgot-password/form.html?language=vi" ><img src="resources/images/language/vn-flag.png"> VN</a></div>
		                	<div class="language-item"><a href="forgot-password/form.html?language=en" ><img src="resources/images/language/en-flag.png"> EN</a></div>
		                </div>
	                </div>
	                <p><s:message code="forgotpass.subtitle" /></p>
	                <ol class="list-unstyled">
	                    <li><span class="text-primary text-medium">1. </span><s:message code="forgotpass.step1" /></li>
	                    <li><span class="text-primary text-medium">2. </span><s:message code="forgotpass.step2" /></li>
	                    <li><span class="text-primary text-medium">3. </span><s:message code="forgotpass.step3" /></li>
	                </ol>
	            </div>
	            <form class="card mt-4" action="forgot-password/send-email-verify.html" method="post"  onsubmit="return validateForm()">
	                <div class="card-body">
	                <%-- <c:if test="${not empty message }">
	        
						<div class="alert alert-warning alert-dismissible fade show" role="alert">
						  <strong> ${message }!</strong> 
						  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    <span aria-hidden="true">&times;</span>
						  </button>
						</div>
	                </c:if> --%>
	                <%
					    if (request.getParameter("message") != null) {
					        out.println("<div class='alert alert-warning alert-dismissible fade show'> <strong>"+request.getParameter("message")+"</strong> <button type='button' class='close' data-dismiss='alert' aria-label='Close'> <span aria-hidden='true'>&times;</span> </button> </div>");
					    }
					%>
	                
	                    <div class="form-group"> <label for="email-for-pass"><s:message code="forgotpass.label" /></label>
	                     <input class="form-control" name="phone" type="text" id="phonenumber" required="" value="<% if(request.getParameter("phone") != null) {out.print(request.getParameter("phone"));} %>" onkeyup="validateInput()" onfocusout="checkEmpty()">
	                    	<p class="validate-phone" id="validate"></p>
	                    <small class="form-text text-muted"><s:message code="forgotpass.small" /></small> </div>
	                </div>
	                <div class="card-footer"> <button class="btn btn-success" type="submit"><s:message code="forgotpass.button.get" /></button> <a class="btn btn-danger" href="login.html" type="submit"><s:message code="forgotpass.button.return" /></a> </div>
	            </form>
	        </div>
	    </div>
	</div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	<script  src="resources/login/forgotpassword/script.js"></script>
</body>
</html>