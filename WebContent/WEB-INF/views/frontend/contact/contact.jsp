<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/frontend/header.jsp"%>

        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="header__logo">
                        <a href="./index.html"><img src="resources/frontend/img/logo.png" alt=""></a>
                    </div>
                </div>
                <div class="col-lg-6">
                    <nav class="header__menu">
                        <ul>
                            <li><a href="index.html">Home</a></li>
                            <li><a href="shop/shop.html">Shop</a></li>
                      	
                            <li><a href="blog/blogView.html">Blog</a></li>
                            <li class="active"><a href="contact.html">Contact</a></li>
                            <li><a href="ordered.html">Ordered</a></li>
                            
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__cart">
                        <ul>
                            <li><a href="#"><i class="fa fa-heart"></i> <span>1</span></a></li>
                            <li><a href="#"><i class="fa fa-shopping-bag"></i> <span>
                            	<%
                            		if (session.getAttribute("cartNumber") != null){
                            			int n = (int)session.getAttribute("cartNumber");
                            			out.println(n);
                            		}else {
                            			out.println(0);
                            		}
                            	%>
                            </span></a></li>
                        </ul>
                        <div class="header__cart__price">item: <span>$150.00</span></div>
                    </div>
                </div>
            </div>
            
            <div class="humberger__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    <!-- Header Section End -->

   
    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="resources/frontend/img/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Contact</h2>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <div class='container1 mx-auto mt-5 col-md-10 mt-100'>
	    <div class="header1">
	        <div class="title1">Our Expert Team</div>
	        <p><small class="text-muted"></p>
	        <br>
	        <br>
	    </div>
	    <div class="row justify-content-center pb-5">
	        <div class="card1 col-md-3 mt-100">
	            <div class="card-content">
	                <div class="card-body1 p-0">
	                    <div class="profile1"> <img src="https://scontent-sin6-3.xx.fbcdn.net/v/t1.6435-9/126985290_2559977367574738_3223065929717248293_n.jpg?_nc_cat=104&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=wfoC_PuuuhUAX-x7T1X&_nc_ht=scontent-sin6-3.xx&oh=4428d4f4299a722aabcd0ae5f1bf0527&oe=61BD5042"> </div>
	                    <div class="card-title mt-4"> Nguy???n Minh Hu???n<br /> <small>N18DCCN074</small> </div>
	                    <div class="card-subtitle">
	                        <p> <small class="text-muted"> ????y l?? nh???ng g?? t??i mong ?????i ??? m???t ?????i ng?? chuy??n gia. H??? l?? ?????i t???t nh???t t??? ??????tr?????c ?????n nay! </small> </p>
	                    </div>
	                    <div>
                        <a href="https://www.facebook.com/mhuan2011"><i class="fa fa-facebook"></i></a>
    
	                    </div>
	                    
	                </div>
	            </div>
	        </div>
	        <div class="card1 col-md-3 mt-100">
	            <div class="card-content">
	                <div class="card-body1 p-0">
	                    <div class="profile1"> <img src="https://scontent.fsgn5-5.fna.fbcdn.net/v/t1.6435-9/84269815_258867015083529_2724798931291602944_n.jpg?_nc_cat=100&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=KjjOjbY37MUAX8REDyX&_nc_ht=scontent.fsgn5-5.fna&oh=3ec3db9dc04ac3da573026005553e467&oe=61BB9BAF"> </div>
	                    <div class="card-title mt-4"> Nguy???n H???u Nh??n<br /> <small>N18DCCN137</small> </div>
	                    <div class="card-subtitle">
	                        <p> <small class="text-muted"> T??i th???c s??? r???t th??ch l??m vi???c v???i h???, h??? l?? m???t nh??m chuy??n gia v?? h??? bi???t h??? ??ang l??m g??! </small> </p>
	                    </div>
	                    <div>
                        <a href="https://www.facebook.com/huunhan28"><i class="fa fa-facebook"></i></a>
    
	                    </div>
	                </div>
	            </div>
	        </div>
	        <div class="card1 col-md-3 mt-100">
	            <div class="card-content">
	                <div class="card-body1 p-0">
	                    <div class="profile1"> <img src="https://scontent.fvca1-3.fna.fbcdn.net/v/t1.6435-1/s320x320/81423533_1052212905115311_8561418607609774080_n.jpg?_nc_cat=103&ccb=1-5&_nc_sid=7206a8&_nc_ohc=KuBoHwDAs4MAX-Ihh6e&_nc_ht=scontent.fvca1-3.fna&oh=4ba017722015d3cc905ba5220dc3ed87&oe=61BA423A"> </div>
	                    <div class="card-title mt-4"> Tr????ng Nguy???n Trung Hi???n<br /> <small>N18DCCN062</small> </div>
	                    <div class="card-subtitle">
	                        <p> <small class="text-muted"> C??c th??nh vi??n c?? tr??ch nhi???m r???t c??? th??? v?? c??c ?? t?????ng c???a h??? r???t tuy???t v???i v?? c?? ch???t ri??ng! </small> </p>
	                    </div>
	                    <a href="https://www.facebook.com/profile.php?id=100009798345337"><i class="fa fa-facebook"></i></a>
    
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>


  <%@include file="/WEB-INF/views/includes/frontend/footer.jsp"%>