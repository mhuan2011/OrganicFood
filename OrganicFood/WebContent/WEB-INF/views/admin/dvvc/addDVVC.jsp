<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          
          <div class="col-lg-8 grid-margin stretch-card">
          	<div class="card">
                <div class="card-body">
                  <h4 class="card-title">Thông tin đơn vị vận chuyển chi tiết</h4>
                  <p class="card-description">
                    Add classes like <code>.form-control-lg</code> and <code>.form-control-sm</code>.
                  </p>
                  <form:form action="admin/dvvc/insert-dvvc.html" method="post" modelAttribute="DVVC">
                  	<div class="form-group">
                    <label>Mã đơn vị:</label>
                    <c:if test="${btnStatus == 'btnUpdate'}">  
                    	<form:input path="madv"  type="text" class="form-control form-control-lg" placeholder="Nhập mã đơn vị" aria-label="Username" readonly="true"/>
					</c:if>
					<c:if test="${btnStatus == 'btnAdd'}">  
                    	<form:input path="madv"  type="text" class="form-control form-control-lg" placeholder="Nhập mã đơn vị" aria-label="Username"/>
					</c:if>
                    
	                  </div>
	                  <div class="form-group">
	                    <label>Tên đơn vị</label>
	                    <form:input path="tendv" type="text" class="form-control form-control-lg" placeholder="Nhập tên đơn vị" aria-label="Username"/>
	                  </div>
	                  <div class="form-group">
	                    <label>Giá vận chuyển</label>
	                    <form:input path="giavc" type="text" class="form-control form-control-lg" placeholder="Nhập giá vận chuyển" aria-label="Username"/>
	                  </div>
	                  <div class="form-group">
	                    <label>Thời gian vận chuyển</label>
	                    <form:input path="thoigianvc" type="text" class="form-control form-control-lg" placeholder="Nhập thời gian vận chuyển" aria-label="Username"/>
	                  </div>
	                  <button name="${btnStatus }" class="btn btn-gradient-info btn-fw">Lưu</button>
                  </form:form>
                </div>
              </div>
          </div>
          
          
            
        </div>


        
      <!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>