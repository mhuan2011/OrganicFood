<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>

      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          
          <div class="page-header">
            <h3 class="page-title">
              <span class="page-title-icon bg-gradient-primary text-white mr-2">
                <i class="mdi mdi-home"></i>                 
              </span>
              Tổng quan
            </h3>
           
            <nav aria-label="breadcrumb">
              <ul class="breadcrumb">
                <li class="breadcrumb-item active" aria-current="page">
                  <span></span>Overview
                  <i class="mdi mdi-alert-circle-outline icon-sm text-primary align-middle"></i>
                </li>
                
                 
                
              </ul>
            </nav>
            
            
          </div>
         <form action="admin/home/thongKe.html" class="form-sample"  method="POST">
          <div class="row">
            <div class="col-md-4 stretch-card grid-margin">
              <div class="card bg-gradient-danger card-img-holder text-white" >
                <div class="card-body">
                  <h4 class="font-weight-normal mb-3">Từ ngày (MM/dd/yyyy)
                  </h4>
                  <input name="fromDate" type="date" value="${fromDate }"  class="form-control"  aria-label="Recipient's username" aria-describedby="basic-addon2">
                </div>
              </div>
            </div>
            <div class="col-md-4 stretch-card grid-margin">
              <div class="card bg-gradient-info card-img-holder text-white">
                 <div class="card-body">
                  <h4 class="font-weight-normal mb-3">Dến ngày (MM/dd/yyyy)
                  </h4>
                  <input name="toDate" type="date" value="${toDate }"  class="form-control"  aria-label="Recipient's username" aria-describedby="basic-addon2">
                	</div>
              </div>
            </div>
            <div class="col-md-4 stretch-card grid-margin">
              <div class="card bg-gradient-success card-img-holder text-white " style="margin: 0px auto;">
                <div class="card-body">
                <h4 class="font-weight-normal mb-3"> Chức năng
                  </h4>
                   <button   name="btnThongKe" type="submit" class="btn btn-gradient-info btn-fw">Thống kê</button>	
                	</div>
                
               
              </div>
            </div>
          </div>
          </form>
          <%-- <div class="row"  style="margin-left:10px; margin-bottom:50px; display:  inline-block;" >
          <form action="admin/home/thongKe.html" class="form-sample"  method="POST">
             
                          <div class="hero__search__form">
                             <form action="shop/search.html" method="post">
                             From <input name="ngay" type="date" value="${today }"  class="form-control"  aria-label="Recipient's username" aria-describedby="basic-addon2">	
                                To<input name="ngay" type="date" value="${today }"  class="form-control"  aria-label="Recipient's username" aria-describedby="basic-addon2">
                                  <button name="btnThongKe" type="submit" class="btn btn-gradient-info btn-fw">Thống kê</button>
                            </form>
                        </div>
						
						 <label>Thời gian (MM/dd/yyyy):</label>
						 
						<input style="display: inline;" name="thoiGian" type="date"  class="form-control" value="${today }"/>
						
						
						 <label>Thời gian (MM/dd/yyyy):</label>
						 
						<input style="display: inline;" name="thoiGian" type="date"  class="form-control" value="${today }"/>
                         
                              <input name="thoiGian" type="date" class="form-control" value="<%= new java.util.Date(System.currentTimeMillis()) %>"/>
                   <br>
                       
             
           
             </form>
             
          </div> --%>
          <div class="row">
            <div class="col-md-4 stretch-card grid-margin">
              <div class="card bg-gradient-danger card-img-holder text-white">
                <div class="card-body">
                  <img src="resources/images/dashboard/circle.svg" class="card-img-absolute" alt="circle-image"/>
                  <h4 class="font-weight-normal mb-3">Doanh số
                    <i class="mdi mdi-chart-line mdi-24px float-right"></i>
                  </h4>
                  <h2>${doanhSo }</h2>
                  <h6 class="card-text">Increased by 60%</h6>
                </div>
              </div>
            </div>
            <div class="col-md-4 stretch-card grid-margin">
              <div class="card bg-gradient-info card-img-holder text-white">
                <div class="card-body">
                  <img src="resources/images/dashboard/circle.svg" class="card-img-absolute" alt="circle-image"/>                  
                  <h4 class="font-weight-normal mb-3">Số đơn đặt hàng
                    <i class="mdi mdi-bookmark-outline mdi-24px float-right"></i>
                  </h4>
                  <h2 class="mb-5">${donDatHang }</h2>
                  <h6 class="card-text">Decreased by 10%</h6>
                </div>
              </div>
            </div>
            <div class="col-md-4 stretch-card grid-margin">
              <div class="card bg-gradient-success card-img-holder text-white">
                <div class="card-body">
                  <img src="resources/images/dashboard/circle.svg" class="card-img-absolute" alt="circle-image"/>                                    
                  <h4 class="font-weight-normal mb-3">Tổng số khách hàng
                    <i class="mdi mdi-diamond mdi-24px float-right"></i>
                  </h4>
                  <h2 class="mb-5">${khachHang }</h2>
                  <h6 class="card-text">Increased by 5%</h6>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-7 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <div class="clearfix">
                    <h4 class="card-title float-left">Biểu đồ doanh thu sản phẩm</h4>
                    <div id="visit-sale-chart-legend" class="rounded-legend legend-horizontal legend-top-right float-right"></div>                                     
                  </div>
                  <canvas id="barChart" ></canvas>
                </div>
              </div>
            </div>
            
            
           
            <div class="col-md-5 grid-margin stretch-card">
              <div class="card">
               <h4 style="text-align:center; margin-top:30px"class="card-title">Trạng thái đơn hàng</h4>	
               <br>
               <br>
               <br>
              <canvas id="traffic-chart2"></canvas>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Doanh thu từng sản phẩm</h4>
                  <div class="table-responsive">
                  
                    <table class="table">
                      <thead>
                        <tr>
                        
                         <th>
                            STT
                          </th>
                          <th>
                            Mã sản phẩm
                          </th>
                          <th>
                            Tên sản phẩm
                          </th>
                          <th>
                            Hình ảnh
                          </th>
                          <th>
                           Doanh thu
                          </th>
                        </tr>
                      </thead>
                      
                      <tbody>
                        <% int count = 1; %>
                      <c:forEach var="trangThai" items="${sanPham }">
                        <tr>
                        	
                        		 <td>
	                          <%=count++%>
	                          
	                        </td>
                          <td>
                            ${trangThai.key.getId()}
                          </td>
                           <td>
                            ${trangThai.key.getName()}
                          </td>
                           <td>
                            <img src="UploadFiles/${trangThai.key.getImage()}"/>
                          </td>
                          <td>
                             ${trangThai.value}
                          </td>
                        
                        </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
         
          
        </div>
 
    		<script type="text/javascript">
    		
    		window.addEventListener("load",function() {
    			let cateLabels=[], cateInfos=[];
    			<c:forEach var="o" items="${trangThaiDonHang}">
        			cateLabels.push('${o[0]}')
        			cateInfos.push('${o[1]}')
        		</c:forEach>
    			cateChart("traffic-chart2", cateLabels, cateInfos)
    		},false);
    		 window.addEventListener("load",function() {
    			let cateLabels=[], cateInfos=[];
    			<c:forEach var="i" items="${sanPham}">
        			cateLabels.push('${i.key.getName()}')
        			cateInfos.push('${i.value}')
        		</c:forEach>
    			barChart("barChart", cateLabels, cateInfos)
    		},false); 
   			</script>
   			
   			
   			

<%@include file="/WEB-INF/views/includes/footer.jsp"%>