<%@page import="organicfood.bean.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="zxx">

<head>
	
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OrganicFood</title>
	<base href="${pageContext.servletContext.contextPath}/">
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">
	<link rel = "icon" href = "resources/images/icon.svg" type = "image/x-icon">
    <!-- Css Styles -->
    <link rel="stylesheet" href="resources/frontend/css/contact.css" type="text/css">
    <link rel="stylesheet" href="resources/frontend/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="resources/frontend/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="resources/frontend/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="resources/frontend/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="resources/frontend/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="resources/frontend/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="resources/frontend/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="resources/frontend/css/style.css" type="text/css">
    
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Humberger Begin -->
    <div class="humberger__menu__overlay"></div>
    <div class="humberger__menu__wrapper">
        <div class="humberger__menu__logo">
            <a href="#"><img src="resources/frontend/img/logo.png" alt=""></a>
        </div>
        <div class="humberger__menu__cart">
            <ul>
                <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                <li><a href="cart.html"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
            </ul>
            <span>0</span>
            <div class="header__cart__price">item: <span>120,300 VND</span></div>
        </div>
        <div class="humberger__menu__widget">
            <div class="header__top__right__language">
                <img src="resources/frontend/img/language.png" alt="">
                <div>English</div>
                <span class="arrow_carrot-down"></span>
                <ul>
                    <li><a href="#">Spanis</a></li>
                    <li><a href="#">English</a></li>
                </ul>
            </div>
            <div class="header__top__right__auth">
                <a href="#"><i class="fa fa-user"></i> Login</a>
            </div>
        </div>
        <nav class="humberger__menu__nav mobile-menu">
            <ul>
                <li class="active"><a href="index.html">Home</a></li>
                <li><a href="shop/shop.html">Shop</a></li>
               
                <li><a href="blog/blogView.html">Blog</a></li>
                <li><a href="contact.html">Contact</a></li>
                <li><a href="ordered.html">Ordered</a></li>
            </ul>
        </nav>
        <div id="mobile-menu-wrap"></div>
        <div class="header__top__right__social">
            <a href="https://www.facebook.com/huunhan28"><i class="fa fa-facebook"></i></a>
            <a href="https://www.instagram.com/huu.nhan.28/"><i class="fa fa-instagram"></i></a>
            <a href="https://twitter.com/huunhan28"><i class="fa fa-twitter"></i></a>
            <a href="https://pinterest.com/huu_nhan_28"><i class="fa fa-pinterest"></i></a>
        </div>
        <div class="humberger__menu__contact">
            <ul>
                <li><i class="fa fa-envelope"></i> phongthietbiptit@gmail.com</li>
                <li>Free Shipping for all Order of $99</li>
            </ul>
        </div>
    </div>
    <!-- Humberger End -->

    <!-- Header Section Begin -->
    <header class="header">
        <div class="header__top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <div class="header__top__left">
                            <ul>
                                <li><i class="fa fa-envelope"></i> phongthietbiptit@gmail.com</li>
                                <li>Free Shipping for all Order of $99</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <div class="header__top__right">
                            <div class="header__top__right__social">
                                <a href="https://www.facebook.com/huunhan28"><i class="fa fa-facebook"></i></a>
	                            <a href="https://www.instagram.com/huu.nhan.28/"><i class="fa fa-instagram"></i></a>
	                            <a href="https://twitter.com/huunhan28"><i class="fa fa-twitter"></i></a>
	                            <a href="https://pinterest.com/huu_nhan_28"><i class="fa fa-pinterest"></i></a>
                            </div>
                            <div class="header__top__right__language">
                                <img src="resources/frontend/img/language.png" alt="">
                                <div>English</div>
                                <span class="arrow_carrot-down"></span>
                                <ul>
                                    <li><a href="#">VietNam</a></li>
                                    <li><a href="#">English</a></li>
                                </ul>
                            </div>
                            <div class="header__top__right__auth">
                                <%
								    if (session.getAttribute("user") != null) {
								    	Account ac = (Account)session.getAttribute("user");
								        out.println("<a href='profile.html'><i class='fa fa-user'></i>"+ac.getUsername() +"</a> <a href='logout.html'> <i class='fas fa-sign-out-alt'></i>Logout</a>");
								    }else {
								    	out.println("<a href='login.html'><i class='fa fa-user'></i>Login</a>");
								    }
								%>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        