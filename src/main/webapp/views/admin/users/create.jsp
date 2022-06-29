<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<h1>Create</h1>
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
<form class="p-3" action="/NikaShop/admin/users/store" method="post" enctype="multipart/form-data">
	<label class="form-label fw-bold">Họ và tên
	</label> <input class="form-control mb-3" type="text" name="fullname" />
	<label class="form-label col-12 fw-bold">Giới tính</label>
	<div class="form-check form-check-inline">
		<input class="form-check-input" type="radio" name="gender"
			id="inlineRadio1" value="1" checked> <label
			class="form-check-label mb-3" for="inlineRadio1">Nam</label>
	</div>
	<div class="form-check form-check-inline">
		<input class="form-check-input mb-3" type="radio" name="gender"
			id="inlineRadio2" value="0"> <label
			class="form-check-label" for="inlineRadio2">Nữ</label>
	</div>
	<label class="form-label col-12 fw-bold">Email
	</label> <input type="email" class="form-control mb-3" name="email" />
	<label class="form-label fw-bold">Mật khẩu
	</label> <input type="password" class="form-control mb-3" name="password" />
	<label
		class="form-label fw-bold">Ảnh đại diện </label> <input
		class="form-control mb-3" type="file" name="avatar"
		accept=".jpg, .png, .jpeg, .gif, .bmp, .tif, .tiff|image/*" />
	<label class="form-label fw-bold">Địa chỉ
	</label> <input type="text" class="form-control mb-3" name="address" />
	<label class="form-label fw-bold">Số điện thoại
	</label> <input type="text" class="form-control mb-3" name="phone" />
	<label class="form-label col-12 fw-bold">Quyền hạn</label>
	
	<div class="form-check form-check-inline">
		<input class="form-check-input mb-3" type="radio" name="role"
			id="inlineRadio2" value="0" checked> <label
			class="form-check-label" for="inlineRadio2">Người dùng</label>
	</div>
	<div class="form-check form-check-inline">
		<input class="form-check-input" type="radio" name="role"
			id="inlineRadio1" value="1"> <label
			class="form-check-label mb-3" for="inlineRadio1">Quản trị viên</label>
	</div>
	<button class="btn btn-primary btn-lg rounded-pill col-12">Đồng ý</button>	
</form>