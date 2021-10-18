<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
      
          
          <div class="col-lg-12 grid-margin stretch-card">
          		
              <div class="card">
              
                <div class="card-body">
                  <h4 class="card-title">Danh sách khách hàng</h4>
                  <p class="card-description">
                    Quản lí danh sách khách hàng của công ty
                    ${message}
                  </p>
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
                        <th style="width: 5%">
                          STT
                        </th>
                        <th style="width: 20%">
                          Tên
                        </th>
                        <th >
                          SDT
                        </th>
                        <th >
                          Mật khẩu
                        </th>
                        <th >
                          Địa chỉ
                        </th>
                      </tr>
                    </thead>
                    <tbody>
 
                    <% int count = 1; %>
                   <c:forEach var="s" items="${KhachHang }" varStatus="count">
	                    <tr>
	                        <td>
	                          <%=count++%>
	                          
	                        </td>
	                        <td>
	                         	${s.getName() }
	                        </td>
	                        <td>
	                          	${s.getPhone() }
	                        </td>
	                        <td>
	                          ${s.getPassword()}
	                        </td>
	                        <td>
	                          ${s.getAddress() }
	                        </td>
	                      </tr>
                    
                    
                    </c:forEach>
                      
                    </tbody>
                  </table>
                  <div class="card">
                  <nav aria-label="Page navigation example">
					  <ul class="pagination">
					    <li class="page-item">
					      <a class="page-link" href="#" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					        <span class="sr-only">Previous</span>
					      </a>
					    </li>
					    <li class="page-item"><a class="page-link" href="#">1</a></li>
					    <li class="page-item"><a class="page-link" href="#">2</a></li>
					    <li class="page-item"><a class="page-link" href="#">3</a></li>
					    <li class="page-item">
					      <a class="page-link" href="#" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					        <span class="sr-only">Next</span>
					      </a>
					    </li>
					  </ul>
					</nav>
                  </div>
                </div>
                
              </div>
            </div>
            
        </div>


        
      <!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>