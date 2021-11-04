<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
      
          
          <div class="col-lg-12 grid-margin stretch-card">
          		
              <div class="card">
              
                <div class="card-body">
                  <h4 class="card-title">Danh sách khuyến mãi</h4>
                  
                  <p class="card-description">
                    Danh sách khuyến mãi cập nhật trực tiếp
                    
                  </p>
 					<%
					    if (request.getParameter("message") != null) {
					        out.println("<div class='alert alert-success alert-dismissible fade show'> <strong>"+request.getParameter("message")+"</strong> <button type='button' class='close' data-dismiss='alert' aria-label='Close'> <span aria-hidden='true'>&times;</span> </button> </div>");
					    }
					%>
					
					
                  <c:if test="${mess != null}">  
					<div class="alert alert-success alert-dismissible fade show " role="alert">
					  <strong>${mess}</strong>
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
					    <span aria-hidden="true">&times;</span>
					  </button>
					</div>
				</c:if>
                  
				
				<!-- Search -->
				<form class="form-group col-6 float-right m-0 pr-0" action="admin/discount/discount-list.html" method="post">
					<div class="form-group ">
                    <div class="input-group">
                      <input name="search" type="text" value="${searchText }"  class="form-control" placeholder="Nhập tên khuyến mãi cần tìm" aria-label="Recipient's username" aria-describedby="basic-addon2">
                      <div class="input-group-append">
                        <button name="btnsearch" class="btn btn-sm btn-gradient-primary" type="submit">Tìm kiếm</button>
                      </div>
                    </div>
                  </div>
				</form>
				
				
				
				<!-- Pagination -->
				<%-- <div>
					 <jsp:useBean id="pagedListHolder" scope="request"
						type="org.springframework.beans.support.PagedListHolder" />
					<c:url value="admin/product/index.html" var="pagedLink">
						<c:param name="p" value="~" />
					</c:url> 
					
						     --%>
				<!-- Pagination -->
                  <table class="table table-bordered">
                  	<div>
							
						</div>
                    <thead>
                    
                    
                      <tr style="font-weight: bold;">
                        <th style="width: 5%;">
                          STT
                        </th>
                        <th style="width: 10%">
                          Mã KM
                        </th>
                        <th >
                          Tên 
                        </th>
                        <th style="width: 15%">
                          Ngày bắt đầu
                        </th >
                        <th style="width: 15%">
                          Ngày kết thúc
                        </th >
                        <th style="width: 10%">
                          Khuyến mãi
                        </th >
                        <th style="width: 10%">
                          Số lượng
                        </th >
                  		<th style="width: 7%">
                          Show
                        </th >
                        <th style="width: 5%">
                          
                        </th >
                        <th style="width: 5%">
                          
                        </th >
                      </tr>
                    </thead>
                    <tbody>
 
                    <% int count = 1; %>
                  <c:forEach var="s" items="${discounts }" varStatus="count">
                  <%-- <c:forEach var="s" items="${pagedListHolder.pageList}">  --%>
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
	                          	${s.getBegin() }
	                        </td>
	                        <td>
	                          	
	                          	${s.getEnd() }
	                        </td>
	                        <td>	
	                        	<fmt:formatNumber value="${s.getDiscount() }" type="percent" />
	                        </td>
	                        <td>
	                          	${s.getQuantity() }
	                        </td>
	                     	<td style="text-align: center;">
	                          	<a href="admin/discount/show/${s.getId()}.html">${s.getShow()?"<i class='far fa-eye' ></i> Show":" <i class='far fa-eye-slash'></i> Hidden" }</a>
	                        </td>
	                        <td>
	                          <a href="admin/discount/update/${s.getId()}.html">Edit</a>
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
								        <a type="button" href="admin/discount/delete/${s.getId()}.html" class="btn btn-gradient-danger ">Xóa</a>
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
                   <%-- <div class="pt-2">
                   	<tg:paging pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />
                   </div>

		</div>  --%>
				<!-- Pagination -->
                  <a href="admin/discount/add-discount.html" class="btn btn-gradient-primary mr-2" style="margin-top: 15px;">Thêm khuyến mãi</a>
                </div>
              </div>
            </div>
            
        </div>
		
        
      <!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>