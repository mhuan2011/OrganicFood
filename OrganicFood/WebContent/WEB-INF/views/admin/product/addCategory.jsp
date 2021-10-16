<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          
          <div class="col-lg-8 grid-margin stretch-card">
          	<div class="card">
                <div class="card-body">
                  <h4 class="card-title">Thông tin danh mục chi tiết</h4>
                  <p class="card-description">
                    Add classes like <code>.form-control-lg</code> and <code>.form-control-sm</code>.
                  </p>
                  <form:form action="admin/product/insert-category.html" method="post" modelAttribute="CategoryProduct">
                  	<div class="form-group">
                    <label>Mã danh mục:</label>
                    <c:if test="${btnStatus == 'btnUpdate'}">  
                    	<form:input path="id"  type="text" class="form-control form-control-lg" placeholder="Nhập mã danh mục" aria-label="Username" readonly="true"/>
					</c:if>
					<c:if test="${btnStatus == 'btnAdd'}">  
                    	<form:input path="id"  type="text" class="form-control form-control-lg" placeholder="Nhập mã danh mục" aria-label="Username"/>
					</c:if>
                    
	                  </div>
	                  <div class="form-group">
	                    <label>Tên danh mục</label>
	                    <form:input path="name" type="text" class="form-control form-control-lg" placeholder="Nhập tên danh mục" aria-label="Username"/>
	                  </div>
	                  
	                  <button name="${btnStatus }" class="btn btn-gradient-info btn-fw">Lưu</button>
                  </form:form>
                </div>
              </div>
          </div>
          
          
            
        </div>


        
      <!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>