<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="row">
	<div class="col-8">
		<h1>Quản lý sản phẩm</h1>
	</div>
	<div class="col-4 mt-3 d-flex flex-row-reverse">
		<a class="btn btn-primary" href="/NikaShop/admin/product/create">
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
			<th scope="col">Tên sản phẩm</th>
			<th scope="col">Giá</th>
			<th scope="col">Số lượng</th>
			<th scope="col">Ngày tạo</th>
			<th scope="col">Ngày cập nhật</th>
			<th scope="col">Hình ảnh</th>
			<th scope="col">Trạng thái</th>
			<th scope="col">Chức năng</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="u" items="${ listProduct }" varStatus="s">
			<tr class="text-center">
				<th>${ s.index + 1}</th>
				<td>${ u.name }</td>
				<td><fmt:formatNumber value="${ u.price }" pattern="#,###"></fmt:formatNumber>
					₫</td>
				<td>${ u.quantity }</td>
				<td><fmt:formatDate value="${ u.createat }"
						pattern="dd/MM/yyyy" /></td>
				<td><fmt:formatDate value="${ u.updateat }"
						pattern="dd/MM/yyyy" /></td>
				<td class="d-flex flex-column">
					<!-- Image --> <img alt="" src="/NikaShop/images/${ u.image }"
					height="200px" class="mb-3"> <!-- Button trigger modal -->
					<button type="button" class="btn btn-primary"
						data-bs-toggle="modal" data-bs-target="#image_${ u.id }">Đổi ảnh</button> <!-- Modal -->
					<div class="modal fade" id="image_${ u.id }" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Đổi ảnh</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">
									<form
										action="/NikaShop/admin/product/update?idpro=${ u.id }"
										method="post" enctype="multipart/form-data">
										<!-- Name -->

										<input class="form-control mb-3 d-none" type="text"
											name="name" value="${ u.name }" />

										<!-- Quantity -->

										<input type="number" class="form-control mb-3 d-none"
											name="quantity" value="${ u.quantity }" disabled />

										<!-- Price -->

										<input type="text" class="form-control mb-3 d-none"
											name="price"
											value="<fmt:formatNumber value="${ u.price }" pattern="####"></fmt:formatNumber>" />
										<label class="form-label fw-bold">Ảnh sản phẩm </label> <input
											class="form-control mb-3" type="file" name="images"
											accept="image/*" />
										<button class="btn btn-primary btn-lg rounded-pill col-12"
											name="btn-update" value="image">Đồng ý</button>
									</form>
								</div>
								
							</div>
						</div>
					</div>
				</td>
				<td>${ u.status==1? "Đang kinh doanh":"Dừng kinh doanh" }</td>
				<td><a class=""
					href="/NikaShop/admin/product/edit?id=${ u.id }"> <i
						class="fas fa-edit"></i>
				</a></td>
		</c:forEach>
	</tbody>
</table>
