<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="row">
	<div class="col-8">
		<h1>${ pageTitle }</h1>
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
			<c:forEach var="c" items="${ listCarts }" varStatus="s">
				<tr class="text-center">

					<th>${ s.index + 1}</th>

					<td>${ c.product.name }</td>

					<td><fmt:formatNumber value="${ c.product.price }"
							pattern="#,###"></fmt:formatNumber> ₫</td>

					<td>${ c.product.quantity }</td>

					<td>
						<!-- Image --> <img alt=""
						src="/NikaShop/images/${ c.product.image }" height="200px"
						class="mb-3"> <!-- Button trigger modal -->
					</td>
					<td>${ c.quantity }</td>
					<td>
						<!-- Button trigger modal -->
						<button type="button" class="btn btn-outline-primary"
							data-bs-toggle="modal" data-bs-target="#cart">Thay đổi
							số lượng</button> <!-- Modal -->
						<div class="modal fade" id="cart" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Mời bạn
											nhập số lượng</h5>
										<a class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></a>
									</div>
									<div class="modal-body">
										<form action="/NikaShop/admin/carts/edit?iduser=${ sessionScope.user.id }&idpro=${ c.product.id }" method="post">
											<label class="form-label fw-bold"><h3>Số lượng</h3> </label> <input
												class="form-control mb-3" type="number" name="quantity" />
											<a class="btn btn-secondary"
												data-bs-dismiss="modal">Hủy</a>
											<button class="btn btn-primary">Đồng ý</button>
										</form>
									</div>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>