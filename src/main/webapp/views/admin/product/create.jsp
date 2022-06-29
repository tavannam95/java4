<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<h1>Create</h1>
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
<form class="p-3" action="/NikaShop/admin/product/store" method="post"
	enctype="multipart/form-data">

	<!-- Name -->

	<label class="form-label fw-bold">Tên sản phẩm </label> <input
		class="form-control mb-3" type="text" name="name" />

	<!-- Quantity -->

	<label class="form-label col-12 fw-bold">Số lượng </label> <input
		type="number" class="form-control mb-3" name="quantity" />

	<!-- Price -->

	<label class="form-label fw-bold">Giá </label> <input type="number"
		class="form-control mb-3" name="price" />

	<!-- Images -->

	<label class="form-label fw-bold">Ảnh sản phẩm </label> <input
		class="form-control mb-3" type="file" name="images"
		accept="image/*"/>

	<!-- Category -->

	<label class="form-label fw-bold">Danh mục </label> <select
		class="form-select mb-3" aria-label="Default select example" name="categories">
		<option disabled selected>Danh mục sản phẩm</option>
		<c:forEach var="c" items="${ listCategories }">
			<option value="${ c.id }">${ c.name }</option>
		</c:forEach>
	</select>
	
	<!-- Color -->
	
	<label class="form-label fw-bold">Màu sắc </label> <select
		class="form-select mb-3" aria-label="Default select example" name="colors2">
		<option disabled selected>Màu sắc</option>
		<c:forEach var="c" items="${ listColors }">
			<option value="${ c.id }">${ c.name }</option>
		</c:forEach>
	</select>
	
	<!-- Size -->
	
	<label class="form-label fw-bold">Kích thước </label> <select
		class="form-select mb-3" aria-label="Default select example" name="sizes2">
		<option disabled selected>Kích thước</option>
		<c:forEach var="c" items="${ listSizes }">
			<option value="${ c.id }">${ c.name }</option>
		</c:forEach>
	</select>

	<!-- Button OK -->

	<button class="btn btn-primary btn-lg rounded-pill col-12">Đồng
		ý</button>
</form>