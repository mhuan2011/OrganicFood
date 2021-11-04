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
    <title>OrganicFood - OrderSuccess</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="resources/login/forgotpassword/styleSucces.css">
</head>
<body>
    <div class="container">
        <div class="logo"><img src="resources/images/logoOF.svg" alt=""></div>
        <div class="box">
            <h2>Xin chào, ${name }!</h2>
            <p>
            Chúc mừng bạn đã đặt hàng thành công.
            <br>
            OrganicFood đã gửi thông tin chi tiết đơn hàng về gmail cho bạn.
            </p>
            <div class="center"><a href="login.html">Trang đăng nhập</a></div>
            <p class="link">Nếu bạn có bất kì câu hỏi nào vui lòng liên hệ qua <a class="contact" href="">email và số điện thoại hỗ trợ.</a></p>
            <p>Thân mến,<br>Đội ngũ nhân viên công ty OrganicFood</p>
        </div>
    </div>
</body>
</html>