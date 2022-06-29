<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<h1>Edit</h1>
<c:if test="${ !empty sessionScope.message }">
	<div class="alert alert-primary" role="alert">
  		${ sessionScope.message }
	</div>
	<c:remove var="message" scope="session"/>
</c:if>
<c:if test="${ !empty sessionScope.error }">
	<div class="alert alert-danger" role="alert">
  		${ sessionScope.error }
	</div>
	<c:remove var="error" scope="session"/>
</c:if>
<form action="/NikaShop/admin/category/update?id=${ category.id }" method="post">
	<c:if test="${ category.status==1 }">
	<div class="row d-flex flex-row-reverse">
			<button class="btn btn-danger rounded-pill col-2"
				name="btncategory" value="inactive">Dừng hoạt động</button>
		</div>
	</c:if>
	<c:if test="${ category.status==0 }">
	<div class="row d-flex flex-row-reverse">
			<button class="btn btn-success rounded-pill col-2"
				name="btncategory" value="active">Kích hoạt</button>
		</div>
	</c:if>
	<label class="form-label fw-bold">Tên danh mục
	</label> <input class="form-control mb-3" type="text" name="name" value="${ category.name }"/>
	<button class="btn btn-primary btn-lg rounded-pill col-12" name="btncategory" value="ok">Đồng ý</button>
</form>