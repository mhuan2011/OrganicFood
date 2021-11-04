<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          
          <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Thêm khuyến mãi</h4>
    
                  
                  <form:form action="admin/discount/add-discount.html" class="form-sample" modelAttribute="discount" method="post" enctype="multipart/form-data">  <%--  --%> <!-- enctype="multipart/form-data" -->
	                  <p class="card-description">
	                      Thông tin khuyến mãi	
	                    </p>
	                    <%
					    if (request.getParameter("message") != null) {
					        out.println("<div class='alert alert-success alert-dismissible fade show'> <strong>"+request.getParameter("message")+"</strong> <button type='button' class='close' data-dismiss='alert' aria-label='Close'> <span aria-hidden='true'>&times;</span> </button> </div>");
					    }
					%>
	                    
                  		<div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Mã khuyến mãi</label>
                          
                          
                          
                          <div class="col-sm-9">
                           
                            
                            <c:if test="${btnStatus == 'btnUpdate'}">  
		                    	<form:input path="id" type="text" class="form-control" readonly="true" />
							</c:if>
							<c:if test="${btnStatus == 'btnAdd'}">  
		                    	<form:input path="id" type="text" class="form-control check-input-ok" placeholder="Nhập mã khuyến mãi"/>
							</c:if>
							<form:errors path = "id" class="text-danger mess-error-validate" />
                          </div>
                          
                        </div>
                      </div> 
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Tên </label>
                          <div class="col-sm-9">
                            <form:input path="name" type="text" class="form-control" placeholder="Nhập tên khuyến mãi"/>
                            <form:errors path = "name" class="text-danger  mess-error-validate" />
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Ngày bắt đầu</label>
                          <div class="col-sm-9">
       							
                              <form:input path="begin" type="date" class="form-control"/>
            				<form:errors path = "begin" class="text-danger  mess-error-validate" />
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Ngày kết thúc</label>
                          <div class="col-sm-9">
                            <form:input type="date" path="end" class="form-control"/>
                            <form:errors path = "end" class="text-danger  mess-error-validate" />
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Khuyến mãi</label>
                          
                          
                          <div class="col-sm-9">
                            <form:input type="text" path="discount" class="form-control" step="0.01"/>
                            <form:errors path = "discount" class="text-danger  mess-error-validate" />
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Số lượng</label>
                          <div class="col-sm-9">
                            <form:input path="quantity" type="text" class="form-control" />
                           <form:errors path = "quantity" class="text-danger mess-error-validate" />
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
                           
                            
                            
                            	<form:input path="image" type="text" class="form-control" id="fullImgPath" readonly="true"/>
                          </div>
                        </div>
                      </div> 
                       <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Hiển thị:</label>
                          <div class="col-sm-9">
                          	<div class="container-show">
                          		<form:checkbox path="show" value="true" checked="${discount.getShow() }"/>
                          		
                          	</div>
                          	
                            
                            <script type="text/javascript">
                            	imgChoosen.onchange = evt => {
                            	  const [file] = imgChoosen.files
                            	  if (file) {
                            		  showImg.src = URL.createObjectURL(file);
                            		  fullImgPath.value =  URL.createObjectURL(file)
                            	  }
                            	}
                            	
                            </script>
                          </div>
                        </div>
                      </div> 
                    </div>
                    <div class="row">
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
                      <div class="col-md-12">
                        <div class="form-group row">
                          <label class="col-sm-2 col-form-label">Mô tả:</label>
                          <div class="col-sm-10">
                            <form:textarea id="descriptionProduct" path="description" rows="200" cols="100" class="form-control"/>
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
		var ckeditor=CKEDITOR.replace('descriptionProduct');
		/* CKFinder.setupCKEditor(ckeditor,'${pageContext.request.contextPath}/resources/ckfinder/'); */
		</script>

<!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>