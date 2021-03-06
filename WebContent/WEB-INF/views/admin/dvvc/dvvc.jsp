<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
      
          
          <div class="col-lg-12 grid-margin stretch-card">
          		
              <div class="card">
              
                <div class="card-body">
                  <h4 class="card-title">Danh sách đơn vị vận chuyển</h4>
                  <p class="card-description">
                    Quản lí danh sách đơn vị vận chuyển của công ty
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
                          Mã đơn vị
                        </th>
                        <th >
                          Tên đơn vị
                        </th>
                        <th >
                          Giá vận chuyển
                        </th>
                        <th >
                          Thời gian vận chuyển
                        </th>
                        <th style="width: 10%">
                          
                        </th >
                        <th style="width: 10%">
                          
                        </th >
                      </tr>
                    </thead>
                    <tbody>
 
                    <% int count = 1; %>
                   <c:forEach var="s" items="${DonViVanChuyen }" varStatus="count">
	                    <tr>
	                        <td>
	                          <%=count++%>
	                          
	                        </td>
	                        <td>
	                         	${s.getMadv() }
	                        </td>
	                        <td>
	                          	${s.getTendv() }
	                        </td>
	                        <td>
	                          	${s.getGiavc() }
	                        </td>
	                        <td>
	                          	${s.getThoigianvc() }
	                        </td>
	                        <td>
	                          <a href="admin/dvvc/update/${s.getMadv()}.html">Edit</a>
	                        </td>
	                        <td>
	                          <%-- <a class="text-danger" href="admin/product/category/delete/${s.getId()}.html">Delete</a> --%>
	                        </td>
	                      </tr>
                    
                    
                    </c:forEach>
                      <!-- <tr>
	                        <td>
	                          1
	                        </td>
	                        <td>
	                         	MNS
	                        </td>
	                        <td>
	                          	TAM
	                        </td>
	                        <td>
	                          Delete
	                        </td>
	                        <td>
	                          Edit
	                        </td>
	                      </tr>
                       -->
                    </tbody>
                  </table>
                  <a href="admin/dvvc/add-dvvc.html" class="btn btn-gradient-primary mr-2" style="margin-top: 15px;">Thêm danh mục</a>
                </div>
              </div>
            </div>
            
        </div>


        
      <!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>