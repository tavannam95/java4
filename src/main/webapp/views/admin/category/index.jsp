<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="row">
	<div class="col-8">
		<h1>Quản lý danh mục</h1>
	</div>
	<div class="col-4 mt-3 d-flex flex-row-reverse">
		<a class="btn btn-primary" href="/NikaShop/admin/category/create">
			Thêm mới </a>
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
			<th scope="col">Tên danh mục</th>
			<th scope="col">Ngày tạo</th>
			<th scope="col">Ngày cập nhật</th>
			<th scope="col">Trạng thái</th>
			<th scope="col">Chức năng</th>
		</tr>
	</thead>
	<tbody class="text-center">
			<c:forEach var="c" items="${ listCategory }" varStatus="s">
				<tr>
					<th>${ s.index+1 }</th>
					<td>${ c.name }</td>
					<td>
						<fmt:formatDate value="${ c.createat }" pattern="dd/MM/yyyy"/>
					</td>
					<td>
						<fmt:formatDate value="${ c.updateat }" pattern="dd/MM/yyyy"/>
					</td>
					<td>
						<c:if test="${ c.status==0 }">
							Ngừng hoạt động
						</c:if>
						<c:if test="${ c.status ==1 }">
							Hoạt động
						</c:if>
						<c:if test="${ c.status != 0 && c.status != 1 }">
							Không xác định
						</c:if>
					</td>
					<td>
						<a href="/NikaShop/admin/category/edit?id=${ c.id }">
							<i class="fas fa-edit"></i>
						</a>
					</td>
					
				</tr>
			</c:forEach>
	</tbody>
</table>
