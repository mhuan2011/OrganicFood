<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "icon" href = "resources/images/icon.svg" type = "image/x-icon">
    <base href="${pageContext.servletContext.contextPath}/">
    <title>OrganicFood - Forgot Password</title>
    <link rel="stylesheet" href="resources/login/forgotpassword/styleChangePass.css">
</head>
<body>
    <div class="container">
        <h3>Thay đổi mật khẩu</h3>
         			<c:if test="${not empty message}">
                    
                    	<div class="mess-alert">
                    		${message }
                    	</div>
                    </c:if>
        <form action="forgot-password/reset.html" method="post" > 
        
            <div class="form-control">
                <label for="">Mật khẩu mới<span>*</span></label>
                <input type="password" name="password">
                <c:if test="${not empty passworder}">
                    
                    	<div class="error">
                    		${passworder }
                    	</div>
                    </c:if>
            </div>
            <div class="form-control">
                <label for="">Xác nhận mật khẩu<span>*</span></label>
                <input type="password" name="confirm-password">
                <c:if test="${not empty confirmpasworder}">
                    
                    	<div class="error">
                    		${confirmpasworder}
                    	</div>
                    </c:if>
            </div>
            <button type="submit">Đặt lại mật khẩu</button>

             
        </form>
    </div>
</body>
</html>