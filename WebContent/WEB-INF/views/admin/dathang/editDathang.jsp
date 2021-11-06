<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          
          <div class="col-lg-8 grid-margin stretch-card">
          	<div class="card">
                <div class="card-body">
                  <h4 class="card-title">Thông tin đơn đặt hàng chi tiết</h4>
                  <form:form action="admin/dathang/edit-dathang.html" method="post" modelAttribute="DatHang">
                  	  <div class="form-group">
                      	<label>Mã số đơn đặt hàng:</label> 
                    	<form:input path="masoddh"  type="text" class="form-control form-control-lg" placeholder="Nhập mã số đơn đặt hàng" aria-label="Username" readonly="true"/>
	                  </div>
	                  <div class="form-group">
	                    <label>Ngày đặt hàng</label>
	                    <form:input path="ngay" type="text" class="form-control form-control-lg" placeholder="Nhập ngày đặt hàng" aria-label="Username" readonly="true"/>
	                  </div>
	                  <div class="form-group">
	                    <label>Mã nhân viên</label>
	                    <form:input path="nhanvien.id" type="text" class="form-control form-control-lg" placeholder="Nhập mã nhân viên" aria-label="Username" readonly="true"/>
	                  </div>
	                  <div class="form-group">
	                    <label>Mã khách hàng</label>
	                    <form:input path="khachhang.phone" type="text" class="form-control form-control-lg" placeholder="Nhập mã khách hàng" aria-label="Username" readonly="true"/>
	                  </div>
	                  <div class="form-group">
	                    <label>Mã đơn vị</label>
	                    <form:input path="dvvc.madv" type="text" class="form-control form-control-lg" placeholder="Nhập mã đơn vị" aria-label="Username" readonly="true"/>
	                  </div>
	                  <div class="form-group">
	                    <label>Mã khuyến mãi</label>
	                    <form:input path="makm" type="text" class="form-control form-control-lg" placeholder="Nhập mã khuyến mãi" aria-label="Username" readonly="true"/>
	                  </div>
	                    
						<div class="form-group">
							<label>Trạng thái</label>
						  <form:select path="trangthai" class="form-control form-control-lg" items="${dstrangthai }">
						  </form:select>
						</div>
					  
					  <table class="table table-bordered">
	                    <thead>
	                      <tr>
	                        <th style="width: 5%">
	                          STT
	                        </th>
	                        <th style="width: 12%">
	                          Mã số DDH
	                        </th>
	                        <th >
	                          Nông sản
	                        </th>
	                        <th >
	                          Số lượng
	                        </th>
	                        <th >
	                          Đơn giá
	                        </th>
	                        <th >
	                          Tổng tiền
	                        </th>
	                      </tr>
	                    </thead>
	                    <tbody>
	 
	                    <% int count = 1; %>
	                   <c:forEach var="s" items="${CTDDH }" varStatus="count">
		                    <tr>
		                        <td>
		                          <%=count++%>
		                          
		                        </td>
		                        <td>
		                         	${s.getMasoddh() }
		                        </td>
		                        <td>
		                          	${s.getNongsan().getName() }
		                        </td>
		                        <td>
		                          	${s.getSoluong() }
		                        </td>
		                        <td>
		                          	${s.getDongia() }
		                        </td>
		                        <td>
		                          	${s.getSoluong()*s.getDongia() }
		                        </td>
		                      </tr>
	                    </c:forEach>
	                    </tbody>
	                  </table>
					  
					  <br>
	                  <button class="btn btn-gradient-info btn-fw">Lưu</button>
                  </form:form>
                </div>
              </div>
          </div>
          
          
            
        </div>


        
      <!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>