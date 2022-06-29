package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import dao.UserDao;
import entities.User;
import util.EncryptUtil;

/**
 * Servlet implementation class UserServlet
 */
@MultipartConfig
@WebServlet({
	"/admin/users/index",
	"/admin/users/create",
	"/admin/users/store",
	"/admin/users/edit",
	"/admin/users/update",
	"/admin/users/delete",
	"/admin/users/show"
	
})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao callUserDao = new UserDao();
    /**
     * Default constructor. 
     */
    public UserServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if (uri.contains("index")) {
			this.index(request, response);
		}else if (uri.contains("create")) {
			this.create(request, response);
		}else if (uri.contains("edit")) {
			this.edit(request, response);
		}else if (uri.contains("delete")) {
			this.delete(request, response);
		}else if (uri.contains("show")) {
			this.show(request, response);
		}else {
			System.out.println("Error doGet UserServlet!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if (uri.contains("store")) {
			this.store(request, response);
		}else if (uri.contains("update")) {
			this.update(request, response);
		}else {
			System.out.println("Error doPost UserServlet!");
		}
	}

	protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<User> users = this.callUserDao.findAll("SELECT o FROM User o ", User.class);
		request.setAttribute("view", "/views/admin/users/index.jsp");
		request.setAttribute("pageTitle", "Quản lý người dùng");
		request.setAttribute("users", users);
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("view", "/views/admin/users/create.jsp");
		request.setAttribute("pageTitle", "Tạo tài khoản");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		try {
			Part part = request.getPart("avatar");
			
			String realPath = request.getServletContext().getRealPath("/images");
			String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
			
			if (!Files.exists(Path.of(realPath))) {
				Files.createDirectory(Path.of(realPath));
			}
			
			part.write(realPath+"/"+fileName);
			
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
//			user.setFullname(request.getParameter("fullname"));
//			user.setGender(Integer.parseInt(request.getParameter("gender")));
//			user.setEmail(request.getParameter("email"));
			String hashPwString = EncryptUtil.hashPw(request.getParameter("password"));
			user.setPassword(hashPwString);
			user.setAvatar(fileName);
			user.setStatus(1);
//			user.setAddress(request.getParameter("address"));
//			user.setPhone(request.getParameter("phone"));
			callUserDao.create(user);
			session.setAttribute("message", "Tạo mới thành công!");
			response.sendRedirect("/NikaShop/admin/users/index");
		} catch (Exception e) {
			// TODO: handle exception
			session.setAttribute("error", "Tạo mới thất bại!");
			response.sendRedirect("/NikaShop/admin/users/create");
		}
	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("pageTitle", "Chỉnh sửa thông tin người dùng");
		request.setAttribute("view", "/views/admin/users/edit.jsp");
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		User user = this.callUserDao.findById(id, false);
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");	
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String btn = request.getParameter("btn-update");
		User user = new User();
		user = this.callUserDao.findById(Integer.parseInt(request.getParameter("id")), false);
		String fileName = user.getAvatar();
		if (btn==null) {
			try {
				//Image
//				Part part = request.getPart("avatar");
//				
//				String realPath = request.getServletContext().getRealPath("/images");
//				String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
//				
//				if (!Files.exists(Path.of(realPath))) {
//					Files.createDirectory(Path.of(realPath));
//				}
//				
//				part.write(realPath+"/"+fileName);
				//Image
				BeanUtils.populate(user, request.getParameterMap());
				user.setAvatar(fileName);
				this.callUserDao.update(user);
				session.setAttribute("message", "Cập nhật thành công!");
				response.sendRedirect("/NikaShop/admin/users/index");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.setAttribute("error", "Cập nhật thất bại!");
				response.sendRedirect("/NikaShop/admin/users/update?id="+user.getId());
			}
		}else {
			if (btn.equals("inactive")) {
				try {
					BeanUtils.populate(user, request.getParameterMap());
					user.setStatus(0);
					this.callUserDao.update(user);
					session.setAttribute("message", "Hủy tài khoản thành công!");
					response.sendRedirect("/NikaShop/admin/users/index");
				} catch (Exception e) {
					// TODO: handle exception
					session.setAttribute("message", "Hủy tài khoản thất bại!");
					response.sendRedirect("/NikaShop/admin/users/update?id="+user.getId());
				}
			}else if (btn.equals("active")) {
				try {
					BeanUtils.populate(user, request.getParameterMap());
					user.setStatus(1);
					this.callUserDao.update(user);
					session.setAttribute("message", "Kích hoạt tài khoản thành công!");
					response.sendRedirect("/NikaShop/admin/users/index");
				} catch (Exception e) {
					// TODO: handle exception
					session.setAttribute("message", "Kích hoạt tài khoản thất bại!");
					response.sendRedirect("/NikaShop/admin/users/update?id="+user.getId());
				}
			}else {
				System.out.println("Error btn!");
			}
		}
		
		
		
		
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		User user = this.callUserDao.findById(id, false);
		try {
			this.callUserDao.delete(user);
			session.setAttribute("message", "Xóa thành công!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.setAttribute("error", "Xóa thất bại!");
		}
		response.sendRedirect("/NikaShop/admin/users/index");
	}

	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
}
