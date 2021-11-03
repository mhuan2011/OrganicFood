<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel = "icon" href = "resources/images/icon.svg" type = "image/x-icon">
    <base href="${pageContext.servletContext.contextPath}/">
    <title>OrganicFood - Forgot Password</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="resources/login/forgotpassword/styleSucces.css">
</head>
<body>
    <div class="container">
        <div class="logo"><img src="resources/images/logoOF.svg" alt=""></div>
        <div class="box">
            <h2>Xin chào, ${name }!</h2>
            <p>Chúng tôi đã nhận được yêu cầu đặt lại mật khẩu của bạn, email chứa đường link thay đổi mật khẩu đã được gửi vào email của bạn kiểm tra và thay đổi ngay. <strong>Không chia sẻ email này để đảm bảo tính bảo mật cho tài khoản của bạn.</strong></p>
            <div class="center"><a href="https://www.google.com/intl/vi/gmail/about/"><i class="far fa-envelope"></i>Mở email</a></div>
            <p class="link">Nếu bạn có bất kì câu hỏi nào vui lòng liên hệ qua <a class="contact" href="">email và số điện thoại hỗ trợ.</a></p>
            <p>Thân mến,<br>Đội ngũ nhân viên công ty OrganicFood</p>
        </div>
    </div>
</body>
</html>