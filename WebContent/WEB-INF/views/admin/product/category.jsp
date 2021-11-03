<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
      
          
          <div class="col-lg-12 grid-margin stretch-card">
          		
              <div class="card">
              
                <div class="card-body">
                  <h4 class="card-title">Danh mục nông sản</h4>
                  <p class="card-description">
                    Quản lí danh mục nông sản của công ty
                    
                  </p>
 
                  <%-- Hello <b><%= request.getParameter("message") %></b>! --%>
                  <c:set var = "mess"  value = "${message }"/>
                  <c:out value="${mess}"></c:out>
                  <%
					    if (request.getParameter("message") != null) {
					        out.println("<div class='alert alert-success alert-dismissible fade show'> <strong>"+request.getParameter("message")+"</strong> <button type='button' class='close' data-dismiss='alert' aria-label='Close'> <span aria-hidden='true'>&times;</span> </button> </div>");
					    }
					%>
                  <table class="table table-bordered">
                    <thead>
                    
                    
                      <tr>
                        <th style="width: 5%; font-weight: bold;">
                          STT
                        </th>
                        <th style="width: 20%">
                          Mã số
                        </th>
                        <th >
                          Tên
                        </th>
                        <th >
                          Hình ảnh
                        </th>
                        <th style="width: 10%">
                          
                        </th >
                        <th style="width: 10%">
                          
                        </th >
                      </tr>
                    </thead>
                    <tbody>
 
                    <% int count = 1; %>
                   <c:forEach var="s" items="${LoaiNongSan }" varStatus="count">
	                    <tr>
	                        <td>
	                          <%=count++%>
	                          
	                        </td>
	                        <td>
	                         	${s.getId() }
	                        </td>
	                        <td>
	                          	${s.getName() }
	                        </td>
	                        <td>
	                      		<c:if test="${s.getImage() != null}">
	                      			<img alt="" src="UploadFiles/${s.getImage() }" style="width: 100px; height: 50px; border-radius: 4px;">
	                      		</c:if>
	                          	
	                        </td>
	                        <td>
	                          <a href="admin/product/category/update/${s.getId()}.html">Edit</a>
	                        </td>
	                        <td>
	                        <!-- Button trigger modal -->
	                        
	                          <a class="text-danger show-mess" data-toggle="modal" data-target="#${s.getId() }" href="admin/product/category/delete/${s.getId()}.html"  >Delete</a>
			                  
	                        
	
								<!-- Modal -->
								<div class="modal fade" id="${s.getId() }" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
								  <div class="modal-dialog" role="document">
								    <div class="modal-content">
								      <div class="modal-header">
								        <h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
								        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
								          <span aria-hidden="true">&times;</span>
								        </button>
								      </div>
								      <div class="modal-body">
								       Mục này sẽ được xóa
								      </div>
								      <div class="modal-footer">
								        <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
								        <a type="button" href="admin/product/category/delete/${s.getId()}.html" class="btn btn-gradient-danger ">Xóa</a>
								      </div>
								    </div>
								  </div>
								</div>
	                        	
	                        	
	                        
	                        </td>
	                      </tr>
                    
                    
                    </c:forEach>

                    </tbody>
                    
                  </table>
                  <a href="admin/product/add-category.html" class="btn btn-gradient-primary mr-2" style="margin-top: 15px;">Thêm danh mục</a>
                </div>
              </div>
            </div>
            
        </div>
		
        
      <!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>