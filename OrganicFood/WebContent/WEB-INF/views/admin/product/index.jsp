<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
      
          
          <div class="col-lg-12 grid-margin stretch-card">
          		
              <div class="card">
              
                <div class="card-body">
                  <h4 class="card-title">Danh sách nông sản</h4>
                  <p class="card-description">
                    Danh sách tất cả các nông sản
                    
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
				<!-- Pagination -->
				<%-- <div>
					 <jsp:useBean id="pagedListHolder" scope="request"
						type="org.springframework.beans.support.PagedListHolder" />
					<c:url value="admin/product/" var="pagedLink">
						<c:param name="p" value="~" />
					</c:url> 
					
					<div class="d-flex flex-row justify-content-between">
						
						 <div>
							<tg:paging pagedListHolder="${pagedListHolder}"
								pagedLink="${pagedLink}" />
						</div>  
		
		
		
					</div>  --%>
				<!-- Pagination -->
                  <table class="table table-bordered">
                    <thead>
                    
                    
                      <tr style="font-weight: bold;">
                        <th style="width: 5%;">
                          STT
                        </th>
                        <th style="width: 10%">
                          Mã số
                        </th>
                        <th >
                          Tên
                        </th>
                        <th style="width: 10%">
                          Đơn vị tính
                        </th >
                        <th style="width: 10%">
                          số lượng
                        </th >
                        <th style="width: 10%">
                          Giá 
                        </th >
                        <th style="width: 10%">
                          Hình ảnh
                        </th >
                        <th style="width: 10%">
                          
                        </th >
                        <th style="width: 10%">
                          
                        </th >
                      </tr>
                    </thead>
                    <tbody>
 
                    <% int count = 1; %>
                   <c:forEach var="s" items="${product }" varStatus="count"> 
                   <%-- <c:forEach var="s" items="${pagedListHolder.pageList}">   --%>
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
	                          	${s.getUnit() }
	                        </td>
	                        <td>
	                          	${s.getNumber() }
	                        </td>
	                        <td>
	                          	${s.getPrice() }
	                        </td>
	                        
	                        <td>
	                      		<c:if test="${s.getImage() != null}">
	                      			<img alt="" src="UploadFiles/${s.getImage() }" style="width: 100px; height: 50px; border-radius: 4px;">
	                      		</c:if>
	                          	
	                        </td>
	                        <td>
	                          <a href="admin/product/update/${s.getId()}.html">Edit</a>
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
								        <a type="button" href="admin/product/delete/${s.getId()}.html" class="btn btn-gradient-danger ">Xóa</a>
								      </div>
								    </div>
								  </div>
								</div>
	                        	
	                        	
	                        
	                        </td>
	                      </tr>
                    
                    
                    </c:forEach>

                    </tbody>
                    
                  </table>
                  <!-- Pagination -->
                  <%-- <tg:paging pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />

		</div>  --%>
				<!-- Pagination -->
                  <a href="admin/product/add-product.html" class="btn btn-gradient-primary mr-2" style="margin-top: 15px;">Thêm sản phẩm</a>
                </div>
              </div>
            </div>
            
        </div>
		
        
      <!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>