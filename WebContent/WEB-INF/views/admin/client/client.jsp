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
				<!-- Search -->
				<form class="form-group col-6 float-right m-0 pr-0" action="admin/search-client.html" method="post">
					<div class="form-group ">
                    <div class="input-group">
                      <input name="search" type="text" value="${searchText }"  class="form-control" placeholder="Nhập tên khách hàng cần tìm" aria-label="Recipient's username" aria-describedby="basic-addon2">
                      <div class="input-group-append">
                        <button name="btnsearch" class="btn btn-sm btn-gradient-primary" type="submit">Tìm kiếm</button>
                      </div>
                    </div>
                  </div>
				</form>
				
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
                        <th >
                          Email
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
	                        <td>
	                          ${s.getEmail() }
	                        </td>
	                      </tr>
                    
                    
                    </c:forEach>
                      
                    </tbody>
                  </table>
                  
                </div>
                
              </div>
            </div>
            
        </div>


        
      <!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>