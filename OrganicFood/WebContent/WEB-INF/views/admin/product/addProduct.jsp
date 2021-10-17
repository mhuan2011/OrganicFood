<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          
          <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Thêm sản phẩm mới</h4>
                  <form:form action="admin/product/add-product.html" class="form-sample" modelAttribute="product" method="post" enctype="multipart/form-data">
                    <p class="card-description">
                      Thông tin sản phẩm	
                    </p>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Mã sản phẩm</label>
                          <div class="col-sm-9">
                            <form:input path="id" type="text" class="form-control"/>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Tên sản phẩm</label>
                          <div class="col-sm-9">
                            <form:input path="name" type="text" class="form-control"/>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Đơn vị tính</label>
                          <div class="col-sm-9">
       
                              <form:input path="Unit" type="text" class="form-control"/>
            
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Số lượng tồn</label>
                          <div class="col-sm-9">
                            <form:input type="number" path="number" class="form-control"/>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Loại sản phẩm</label>
                          
                          
                          <div class="col-sm-9">
                            <form:select  path="category.id" items="${category }" itemValue="id" itemLabel="name" class="form-control" />
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Giá</label>
                          <div class="col-sm-9">
                            <form:input path="price" type="money" class="form-control" />
                          </div>
                          
                        </div>
                      </div>
                    </div>

                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Hình ảnh:</label>
                          <div class="col-sm-9" >
                            <form:input path="image" type="file" class="form-control" />
                          </div>
                        </div>
                      </div> 
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Khuyến mãi:</label>
                          <div class="col-sm-9">
                          
                            <form:input id="imgInp" path="discount" type="number" class="form-control" onchange="loadFile(event)"/>
                          </div>
                        </div>
                      </div> 
                    </div>
                    
                    <div class="row">
                      <div class="col-md-12">
                        <div class="form-group row">
                          <label class="col-sm-2 col-form-label">Mô tả:</label>
                          <div class="col-sm-10">
                            <form:textarea id="descriptionProduct" path="description" rows="10" cols="100" class="form-control"/>
                          </div>
                        </div>
                      </div>
                      
                    </div>
                   <button name="${btnStatus }" class="btn btn-gradient-info btn-fw">Lưu</button>
                   
                  </form:form>
                </div>
              </div>
            </div>   
        </div>
		<!-- <script type="text/javascript">
			CKEDITOR.replace('descriptionProduct');
		</script> -->
		<script type="text/javascript">
		var ckeditor=CKEDITOR.replace('descriptionProduct');
		CKFinder.setupCKEditor(ckeditor,'${pageContext.request.contextPath}/resources/ckfinder/');
		</script>

<!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>