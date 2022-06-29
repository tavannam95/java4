<%@ page language="java" session="true"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${ pageTitle }</title>
<link rel="stylesheet" href="/NikaShop/css/bootstrap.min.css">
<link rel="stylesheet" href="/NikaShop/css/style.css">
<link rel="stylesheet" href="/NikaShop/fontawesome/css/all.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500&family=Pacifico&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.0/dist/sweetalert2.min.css" />

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary bg-gradient">
		<div class="container-xxl">
			<a class="navbar-brand text-light" href="/NikaShop/Home"
				style="font-family: 'Pacifico', cursive;">${ contact.name }</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse offset-1" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-3 mb-lg-0 ">
					<li class="nav-item"><a class="nav-link active ms-3"
						aria-current="page" href="/NikaShop/Home">Trang chủ</a></li>
					<li class="nav-item"><a class="nav-link text-light ms-3"
						href="">Hàng mới
							<!-- <span class="badge bg-danger">New</span> -->
						</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle active ms-3" href="#"
						id="navbarDropdown" role="button" data-bs-toggle="dropdown"
						aria-expanded="false"> Danh Mục </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
						<c:forEach var="c" items="${ listCates }">
							<li><a class="dropdown-item" href="/NikaShop/admin/category/show?id=${ c.id }&page=0">${ c.name }</a></li>
						</c:forEach></a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link active ms-3" href="">Liên hệ</a></li>
					<li class="nav-item"><a class="nav-link active ms-3" href="">Giới thiệu</a></li>
					
					<c:if test="${ sessionScope.user.role==1 }">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle active" href="#"
							id="navbarDropdown" role="button" data-bs-toggle="dropdown"
							aria-expanded="false"> Quản trị </a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="/NikaShop/admin/users/index">Người dùng</a></li>
								<li><a class="dropdown-item" href="/NikaShop/admin/category/index">Danh mục</a></li>
								<li><a class="dropdown-item" href="/NikaShop/admin/product/index">Sản phẩm</a></li>
							</ul></li>
					</c:if>

				</ul>
				<c:if test="${ empty user }">
					<ul class="nav">
						<li class="nav-item"><a class="nav-link text-light active"
							aria-current="page" href="/NikaShop/account/login">Đăng nhập</a></li>
						<li class="nav-item"><a class="btn btn-outline-light"
							href="/NikaShop/account/register">Đăng ký</a></li>
					</ul>
				</c:if>
				<c:if test="${ !empty user }">
					<ul class="nav">
						<li class="nav-item"><a class="nav-link text-light active"
							aria-current="page" href="/NikaShop/admin/carts/index"> <i class="fas fa-shopping-cart"></i>
						</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle text-light" href="#"
							id="navbarDropdown" role="button" data-bs-toggle="dropdown"
							aria-expanded="false"> <img class="rounded-circle" height="28px" src="/NikaShop/images/${ user.avatar }"></img> </a>
							<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="#">Cài đặt chung</a></li>
								<li><a class="dropdown-item" href="#">Đổi mật khẩu</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a type="button" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#logout">Đăng xuất</a></li>
							
							</ul></li>
					</ul>
				</c:if>
			</div>
		</div>
	<!-- Modal -->
								<div class="modal fade" id="logout" tabindex="-1"
									aria-labelledby="exampleModalLabel" aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">Bạn có muốn đăng xuất không?</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body d-flex justify-content-center">
												<a class="col-1 btn btn-primary w-25" href="/NikaShop/account/logout">Đồng ý</a>
												<button type="button" class="col-1 btn btn-secondary ms-3 w-25"
													data-bs-dismiss="modal">Hủy</button>
											</div>
										</div>
									</div>
								</div>
	</nav>
	<div class="container p-3">
		<jsp:include page="${ view }"></jsp:include>
	</div>


	<script src="/NikaShop/js/jquery.min.js"></script>
	<script src="/NikaShop/js/popper.min.js"></script>
	<script src="/NikaShop/js/bootstrap.min.js"></script>
</body>
</html>