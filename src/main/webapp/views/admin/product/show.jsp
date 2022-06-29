<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<div class="row border border-light p-3 rounded-3">
	<h1>${ pageTitle }</h1>
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
	<div class="col-6">
		<img alt="" src="/NikaShop/images/${ product.image }"
			class="h-100 img-fluid">
	</div>
	<div class="col-6">
		<form action="/NikaShop/admin/carts/store?id=${ product.id }" method="post">
			<ul>
				<h1 class="card-text text-danger">
					<li>Giá: <fmt:formatNumber value="${ product.price }"
							pattern="#,###"></fmt:formatNumber> ₫
					</li>
				</h1>
				<h1 class="card-text text-dark">
					<li>Trong kho: còn ${ product.quantity } chiếc</li>
				</h1>
			</ul>
			<!-- Color -->

			<label class="form-label fw-bold">Màu sắc </label> <select
				class="form-select mb-3" aria-label="Default select example"
				name="colors2">
				<c:forEach var="c" items="${ listColors }">
					<option value="${ c.id }">${ c.name }</option>
				</c:forEach>
			</select>

			<!-- Size -->

			<label class="form-label fw-bold">Kích thước </label> <select
				class="form-select mb-3" aria-label="Default select example"
				name="sizes2">
				<c:forEach var="c" items="${ listSizes }">
					<option value="${ c.id }">${ c.name }</option>
				</c:forEach>
			</select> <label class="form-label col-12 fw-bold"><h3>Số lượng</h3></label> <input
				type="number" class="form-control mb-3" name="amount" value="1" min="1"/>
			<button class="btn btn-primary w-100">Thêm vào giỏ</button>
		</form>
	</div>

</div>