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
<form action="/NikaShop/admin/users/update?id=${ user.id }" method="post">
	<c:if test="${ user.status==1 }">
	<div class="row d-flex flex-row-reverse">
		<button class="btn btn-danger btn-lg rounded-pill offset-10 col-2" 
			name="btn-update" value="inactive">Hủy tài khoản</button>
	</div>
	</c:if>
	<c:if test="${ user.status==0 }">
		<button class="btn btn-success btn-lg rounded-pill offset-10 col-2" 
			name="btn-update" value="active">Kích hoạt</button>
	</c:if>
</form>
<form action="/NikaShop/admin/users/update?id=${ user.id }" method="post" enctype="multipart/form-data">
	
	<label class="form-label fw-bold">Họ và tên
	</label> <input class="form-control mb-3" type="text" name="fullname" value="${ user.fullname }"/>
	<label class="form-label col-12 fw-bold">Giới tính</label>
	<div class="form-check form-check-inline">
		<input class="form-check-input" type="radio" name="gender"
			id="inlineRadio1" value="1" ${ user.gender==1? "checked":"" }> <label
			class="form-check-label mb-3" for="inlineRadio1">Nam</label>
	</div>
	<div class="form-check form-check-inline">
		<input class="form-check-input mb-3" type="radio" name="gender"
			id="inlineRadio2" value="0" ${ user.gender==0? "checked":"" }> <label
			class="form-check-label" for="inlineRadio2" >Nữ</label>
	</div>
	<label class="form-label col-12 fw-bold">Email
	</label> <input type="email" class="form-control mb-3" name="email" value="${ user.email }" disabled/>
	<input type="password" class="form-control mb-3 d-none" name="password" value="${ user.password }"/>
	<label class="form-label fw-bold">Địa chỉ
	</label> <input type="text" class="form-control mb-3" name="address" value="${ user.address }"/>
	<label class="form-label fw-bold">Số điện thoại
	</label> <input type="text" class="form-control mb-3" name="phone" value="${ user.phone }"/>
	<label class="form-label col-12 fw-bold">Quyền hạn</label>
	<div class="form-check form-check-inline">
		<input class="form-check-input" type="radio" name="role"
			id="inlineRadio1" value="1" ${ user.role==1? "checked":"" }> <label
			class="form-check-label mb-3" for="inlineRadio1">Quản trị viên</label>
	</div>
	<div class="form-check form-check-inline">
		<input class="form-check-input mb-3" type="radio" name="role"
			id="inlineRadio2" value="0" ${ user.role!=1? "checked":"" }> <label
			class="form-check-label" for="inlineRadio2">Người dùng</label>
	</div>
	<button class="btn btn-primary btn-lg rounded-pill col-12">Đồng ý</button>	
</form>