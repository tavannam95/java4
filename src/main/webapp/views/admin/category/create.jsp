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
<form action="/NikaShop/admin/category/store" method="post">
	<label class="form-label fw-bold">Tên danh mục
	</label> <input class="form-control mb-3" type="text" name="name" />
	<button class="btn btn-primary btn-lg rounded-pill col-12">Đồng
		ý</button>
</form>