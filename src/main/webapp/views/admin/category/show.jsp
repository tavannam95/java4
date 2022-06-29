<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<div class="row border border-light p-3 rounded-3">
<h1>${ pageTitle } </h1>
<hr>
<c:if test="${ empty listProducts }">
	<div class="alert alert-danger" role="alert">
		Không có sản phẩm!
	</div>
</c:if>
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

<div class="row">
	<div class="row">
		<c:forEach var="p" items="${ listProducts }" varStatus="s">
			<div class="col-3 mb-3">
				<div class="card text-center" style="width: 18rem;">
					<img src="/NikaShop/images/${ p.image }" class="card-img-top h-100"
						alt="...">
					<div class="card-body">
						<h5 class="card-title fw-bold">${ p.name }</h5>
						<h1 class="card-text text-danger">
							<fmt:formatNumber value="${ p.price }" pattern="#,###"></fmt:formatNumber>
							₫
						</h1>
						<a href="#" class="btn btn-primary">Add to cart</a>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<c:if test="${ currentPage ==1 }">
				<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
			</c:if>
			<c:if test="${ currentPage > 1 }">
				<li class="page-item"><a class="page-link"
					href="/NikaShop/admin/category/show?page=${ currentPage - 1 }">Previous</a></li>
			</c:if>
			<c:forEach varStatus="i" begin="1" end="${ maxPage }">

				<li class="page-item  ${ currentPage == i.index ? 'active':'' }">
					<a href="/NikaShop/admin/category/show?id=${ cateid }&page=${ i.index }" class="page-link">${ i.index }</a>
				</li>
			</c:forEach>
			<c:if test="${ currentPage == maxPage }">
				<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
			</c:if>
			<c:if test="${ currentPage < maxPage }">
				<li class="page-item"><a class="page-link"
					href="/NikaShop/admin/category/show?id=${ cateid }&page=${ currentPage + 1 }">Next</a></li>
			</c:if>
		</ul>
	</nav>
	</div>
</div>