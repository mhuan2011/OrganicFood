<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
      
          
          <div class="col-lg-12 grid-margin stretch-card">
          		
              <div class="card">
              
                <div class="card-body">
                  <h4 class="card-title">Danh sách đơn đặt hàng</h4>
                  <p class="card-description">
                    Quản lí danh sách đơn đặt hàng của công ty
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
                        <th style="width: 12%">
                          Mã số DDH
                        </th>
                        <th >
                          Ngày
                        </th>
                        <th >
                          Mã NV
                        </th>
                        <th >
                          Mã KH
                        </th>
                        <th >
                          Mã DV
                        </th>
                        <th >
                          Mã KM
                        </th>
                        <th >
                          Trạng thái
                        </th>
                        <th style="width: 10%">
                          
                        </th >
                      </tr>
                    </thead>
                    <tbody>
 
                    <% int count = 1; %>
                   <c:forEach var="s" items="${DatHang }" varStatus="count">
	                    <tr>
	                        <td>
	                          <%=count++%>
	                          
	                        </td>
	                        <td>
	                         	${s.getMasoddh() }
	                        </td>
	                        <td>
	                          	${s.getNgay() }
	                        </td>
	                        <td>
	                          	${s.getManv() }
	                        </td>
	                        <td>
	                          	${s.getMakh() }
	                        </td>
	                        <td>
	                          	${s.getMadv() }
	                        </td>
	                        <td>
	                          	${s.getMakm() }
	                        </td>
	                        <td>
	                          	${s.getTrangthai() }
	                        </td>
	                        <td>
	                          <a href="admin/dathang/edit/${s.getMasoddh()}.html">Chi tiết</a>
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
                </div>
              </div>
            </div>
            
        </div>


        
      <!-- main-panel ends -->
<%@include file="/WEB-INF/views/includes/footer.jsp"%>