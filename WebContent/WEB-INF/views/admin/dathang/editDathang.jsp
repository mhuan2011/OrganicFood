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
                  <p class="card-description">
                    Add classes like <code>.form-control-lg</code> and <code>.form-control-sm</code>.
                  </p>
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
	                  <button class="btn btn-gradient-info btn-fw">Lưu</button>
                  </form:form>
                </div>
              </div>
          </div>
          
          
            
        </div>


        
      <!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>