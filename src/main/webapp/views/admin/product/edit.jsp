<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<h1>${ pageTitle }</h1>
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
<form action="/NikaShop/admin/product/update?id=${ product.id }" method="post">

	<!-- Button active in-active -->

	<c:if test="${ product.status==1 }">
	<div class="row d-flex flex-row-reverse">
		<button class="btn btn-danger btn-lg rounded-pill offset-10 col-2" 
			name="btn-update" value="inactive">Dừng kinh doanh</button>
	</div>
	</c:if>
	<c:if test="${ product.status==0 }">
		<button class="btn btn-success btn-lg rounded-pill offset-10 col-2" 
			name="btn-update" value="active">Kích hoạt</button>
	</c:if>
	
	<!-- Name -->
	
	<label class="form-label">Tên sản phẩm
	</label> <input class="form-control mb-3" type="text" name="name" value="${ product.name }"/>
	
	<!-- Quantity -->
	
	<label class="form-label col-12">Số lượng
	</label> <input type="number" class="form-control mb-3" name="quantity" value="${ product.quantity }" disabled />
	
	<!-- Price -->
	
	<label class="form-label">Giá
	</label> <input type="text" class="form-control mb-3" name="price" 
	value="<fmt:formatNumber value="${ product.price }" pattern="####"></fmt:formatNumber>"/>
	
	<button class="btn btn-primary btn-lg rounded-pill col-12" name="btn-update" value="ok">Đồng ý</button>	
</form>