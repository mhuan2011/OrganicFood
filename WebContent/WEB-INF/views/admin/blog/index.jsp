<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
      
          
          <div class="col-lg-12 grid-margin stretch-card">
          		
              <div class="card">
              
                <div class="card-body">
                  <h4 class="card-title">Danh sách blog</h4>
                  <p class="card-description">
                    Danh sách tất cả các blog
                    
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
                        <th style="width: 5%; font-weight: bold;">
                          STT
                        </th>
             
                        <th style="width: 15%">
                          Tiêu đề
                        </th>
                        <th style="width: 15%">
                          Trích dẫn
                        </th>
                         <th style="width: 15%">
                          Ngày
                        </th>
                        <th style="width: 10%">
                          Hình ảnh
                        </th >
                        <th style="width: 20%">
                          Nội dung
                        </th >
                        <th style="width: 5%">
                          Loại
                        </th >
                        <th style="width: 5%">
                          Tác giả
                        </th >
                        <th style="width: 5%">
                          
                        </th >
                        <th style="width: 5%">
                          
                        </th >
                      </tr>
                    </thead>
                    <tbody>
 
                    <% int count = 1; %>
                   <c:forEach var="s" items="${blogs }" varStatus="count">
	                    <tr>
	                        <td>
	                          <%=count++%>
	                          
	                        </td>
	                        
	                        <td>
	                          	${s.getTieuDe2() }
	                        </td>
	                        <td>
	                         	${s.getTrichDan2() }
	                        </td>
	                        <td>
	                         	${s.getNgay() }
	                        </td>
	                        <td>
	                      		<c:if test="${s.getHinhAnh() != null}">
	                      			<img alt="" src="UploadFiles/${s.getHinhAnh() }" style="width: 100px; height: 50px; border-radius: 4px;">
	                      		</c:if>
	                          	
	                        </td>
	                        <td>
	                          	${s.getNoiDung2() }
	                        </td>
	                        <td>
	                          	${s.getLoaiBV().getTenLoai()}
	                        </td>
	                        
	                        <td>
	                          	${s.getNV().getFullName() }
	                        </td>
	                        <td>
	                          <a href="admin/blog/update/${s.getMaBV()}.html">Edit</a>
	                        </td>
	                        <td>
	                        <!-- Button trigger modal -->
	                        
	                          <a class="text-danger show-mess" data-toggle="modal" data-target="#${s.getMaBV() }" href="admin/blog/delete/${s.getMaBV()}.html"  >Delete</a>
			                  
	                        
	
								<!-- Modal -->
								<div class="modal fade" id="${s.getMaBV() }" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
								        <a type="button" href="admin/blog/delete/${s.getMaBV()}.html" class="btn btn-gradient-danger ">Xóa</a>
								      </div>
								    </div>
								  </div>
								</div>
	                        	
	                        
	                        
	                        </td>
	                        
	                      </tr>
                    
                    
                    </c:forEach>

                    </tbody>
                    
                  </table>
                  <a href="admin/blog/addBlog.html" class="btn btn-gradient-primary mr-2" style="margin-top: 15px;">Thêm sản phẩm</a>
                </div>
              </div>
            </div>
            
        </div>
		
        
      <!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>