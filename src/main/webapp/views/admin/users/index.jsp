<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="row">
	<div class="col-8">
		<h1>Quản lý người dùng</h1>
	</div>
	<div class="col-4 mt-3 d-flex flex-row-reverse">
		<a class="btn btn-primary" href="/NikaShop/admin/users/create">
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
			<th scope="col">Họ và tên</th>
			<th scope="col">Giới tính</th>
			<th scope="col">Email</th>
			<th scope="col">Địa chỉ</th>
			<th scope="col">Số điện thoại</th>
			<th scope="col">Quyền hạn</th>
			<th scope="col">Trạng thái</th>
			<th scope="col">Avatar</th>
			<th scope="col">Chức năng</th>

		</tr>
	</thead>
	<tbody>
		<c:forEach var="u" items="${ users }" varStatus="s">
			<tr class="text-center">
				<th>${ s.index + 1}</th>
				<td>${ u.fullname }</td>
				<td>${ u.gender==1? "Nam":"Nữ" }</td>
				<td>${ u.email }</td>
				<td>${ u.address }</td>
				<td>${ u.phone }</td>
				<td>${ u.role==1? "Quản trị viên":"Người dùng" }</td>
				<td>${ u.status==1? "Đang hoạt động":"Dừng hoạt động" }</td>
				<td><img src="/NikaShop/images/${ u.avatar }" height="100px"></td>
				<td><a class="" href="/NikaShop/admin/users/edit?id=${ u.id }"> <i class="fas fa-edit"></i>
				</a></td>
				<%-- <td><a type="button" class="text-danger" data-bs-toggle="modal"
					data-bs-target="#delete${ u.id }"> <i class="fas fa-trash"></i>
				</a></td> --%>
			</tr>
			<!-- Modal -->
			<%-- <div class="modal fade" id="delete${ u.id }" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Bạn có muốn
								xóa không?</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body d-flex justify-content-center">
							<a class="col-1 btn btn-primary w-25"
								href="/NikaShop/admin/users/delete?id=${ u.id }">Đồng ý</a>
							<button type="button" class="col-1 btn btn-secondary ms-3 w-25"
								data-bs-dismiss="modal">Hủy</button>
						</div>
					</div>
				</div>
			</div> --%>
		</c:forEach>
	</tbody>
</table>
