<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          
          <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Thêm bài viết mới</h4>
    
                  
                  <form:form action="admin/blog/insertBlog.html" class="form-sample"
                    modelAttribute="blog" method="post"   enctype="multipart/form-data"> <!-- enctype="multipart/form-data" -->
	                  <p class="card-description">
	                      Thông tin bài viết	
	                    </p>
                  		<div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Tiêu đề</label>
                          
                          <div class="col-sm-9">
		                    	<form:input path="tieuDe" type="text" class="form-control" />
                          </div>
                        </div>
                      </div> 
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Trích dẫn</label>
                          <div class="col-sm-9">
                            <form:input path="trichDan" type="text" class="form-control"/>
                            
                          </div>
                        </div>
                      </div>
                    </div>
                    
                    <div class="row">
                     <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Hình ảnh:</label>
                          <div class="col-sm-9" >
                 
                            	<input name="hinhanh" type="file" class="form-control" id="imgChoosen" value="${imgLink }">
                           
                            
                            
                            <form:input path="hinhAnh" type="text" class="form-control" id="fullImgPath"/>
                          </div>
                        </div>
                      </div> 
                      
                      <div class="col-md-6">
                    		<div class="form-group row">
                    			<label class="col-sm-3 col-form-label">Preview: </label>
                    		<div class="col-sm-9">
                    			<c:if test="${imageLink != null }">
                    					<img alt="preview img" src="${imageLink }" id="showImg" style="width: 200px; height: 120px;">
                    			</c:if>
                    			<c:if test="${imageLink == null }">
                    					<img alt="preview img" src="resources/images/vector/uploadfile.jpg" id="showImg" style="width: 200px; height: 120px;">
                    			</c:if>
                    			
                    		</div>
                    		</div>
                    		
                    	</div>
                    	
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Loại bài viết</label>
                          
                          
                          <div class="col-sm-9">
                            <form:select  path="loaiBV.maLoai" items="${loaiBV }" itemValue="maLoai" itemLabel="tenLoai" class="form-control" />
                          </div>
                        </div>
                      </div>
                     
                    </div>

                 
                    <div class="row">
                      <div class="col-md-12">
                        <div class="form-group row">
                          <label class="col-sm-2 col-form-label">Mô tả:</label>
                          <div class="col-sm-10">
                            <form:textarea id="descriptionBlog" path="noiDung" rows="10" cols="100" class="form-control"/>
                          </div>
                        </div>
                      </div>
                      
                    </div>  
                  		<button name="${btnStatus }" type="submit" class="btn btn-gradient-info btn-fw">Lưu</button>
                  </form:form > 
                  
                  
                </div>
              </div>
            </div>   
        </div>
	
		 <script type="text/javascript">
		var ckeditor=CKEDITOR.replace('descriptionBlog');
		CKFinder.setupCKEditor(ckeditor,'${pageContext.request.contextPath}/resources/ckfinder/');
		</script>

<!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>