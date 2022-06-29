<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="row">
	<div class="col-12">
		<h1>${ pageTitle }</h1>
	</div>
</div>
<hr>
<c:if test="${ !empty sessionScope.message }">
	<div class="alert alert-primary" role="alert">${ sessionScope.message }
	</div>
	<c:remove var="message" scope="session" />
</c:if>
<c:if test="${ !empty sessionScope.error }">
	<div class="alert alert-danger" role="alert">${ sessionScope.error }
	</div>
	<c:remove var="error" scope="session" />
</c:if>
<table class="table table-bordered table-hover border-dark">
		<thead class="bg-primary text-center text-light">
			<tr>
				<th scope="col">#</th>
				<th scope="col">Tổng giá</th>
				<th scope="col">Trạng thái</th>
				<th scope="col">Ngày đặt hàng</th>
				<th scope="col">Người nhận</th>
				<th scope="col">Địa chỉ</th>
				<th scope="col">Email</th>
				<th scope="col">Số điện thoại</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach var="o" items="${ listOrders }" varStatus="s">
				<tr class="text-center">

					<th>${ s.index + 1}</th>

					<td>${ o.total }</td>

					<td>${ o.status }</td>

					<td>
						<fmt:formatDate value="${ o.orderat }" pattern="dd/MM/yyyy"/>
					</td>

					<td>
						${ o.shipname }
					</td>
					<td>
						${ o.shipaddress }
					</td>
					<td>
						${ o.shipemail }
					</td>
					${ o.shipphone }
				</tr>
			</c:forEach>
		</tbody>
	</table>