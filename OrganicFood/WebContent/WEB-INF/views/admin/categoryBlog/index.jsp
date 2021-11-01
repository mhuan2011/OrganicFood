<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
      
          
          <div class="col-lg-12 grid-margin stretch-card">
          		
              <div class="card">
              
                <div class="card-body">
                  <h4 class="card-title">Danh sách danh mục bài viết</h4>
                  <p class="card-description">
                    Danh sách tất cả các danh mục bài viết
                    
                  </p>
 
                  <%-- Hello <b><%= request.getParameter("message") %></b>! --%>
                  <c:set var = "mess"  value = "${message }"/>
                  <c:out value="${mess}"></c:out>
                  <c:if test="${message != null}">  
					<div class="alert alert-success alert-dismissible fade show " role="alert">
					  <strong>${message}</strong>
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
					    <span aria-hidden="true">&times;</span>
					  </button>
					</div>
				</c:if>
                  <table class="table table-bordered">
                    <thead>
                    
                    
                      <tr>
                        <th style="width: 10%; font-weight: bold;">
                          STT
                        </th>
                        <th style="width: 15%">
                          Mã loại
                        </th>
                        <th style="width: 30%">
                          Tên loại
                        </th>
                        <th style="width: 25%">
                          Tag
                        </th >
                        <th style="width: 10%">
                          
                        </th >
                        <th style="width: 10%">
                          
                        </th >
                      </tr>
                    </thead>
                    <tbody>
 
                    <% int count = 1; %>
                   <c:forEach var="s" items="${categoryBlogs}" varStatus="count">
	                    <tr>
	                        <td>
	                          <%=count++%>
	                          
	                        </td>
	                        <td>
	                         	${s.getMaLoai() }
	                        </td>
	                        <td>
	                          	${s.getTenLoai() }
	                        </td>
	                        <td>
	                          	${s.getTag() }
	                        </td>
	                        <td>
	                          <a href="admin/categoryBlog/update/${s.getMaLoai()}.html">Edit</a>
	                        </td>
	                        <td>
	                        <!-- Button trigger modal -->
	                        
	                          <a class="text-danger show-mess" data-toggle="modal" data-target="#${s.getMaLoai() }" href="admin/categoryBlog/delete/${s.getMaLoai()}.html"  >Delete</a>
			                  
	                        
	
								<!-- Modal -->
								<div class="modal fade" id="${s.getMaLoai()}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
								        <a type="button" href="admin/categoryBlog/delete/${s.getMaLoai()}.html" class="btn btn-gradient-danger ">Xóa</a>
								      </div>
								    </div>
								  </div>
								</div>
	                        	
	                        	
	                        
	                        </td>
	                      </tr>
                    
                    
                    </c:forEach>

                    </tbody>
                    
                  </table>
                  <a href="admin/categoryBlog/add.html" class="btn btn-gradient-primary mr-2" style="margin-top: 15px;">Thêm Danh Mục</a>
                </div>
              </div>
            </div>
            
        </div>
		
        
      <!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>