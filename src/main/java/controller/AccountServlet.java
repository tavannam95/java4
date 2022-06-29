package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
 * Servlet implementation class AccountServlet
 */
@MultipartConfig
@WebServlet({
	"/account/login",//G
	"/account/signin",//P
	"/account/register",//G
	"/account/signup",//P
	"/account/logout",//G
	"/account/setting",//G
	"/account/setuser",//P
	"/account/changepassword",//G
	"/account/updatepassword"//P
	
})
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       UserDao callUserDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
        this.callUserDao = new UserDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if (uri.contains("login")) {
			this.login(request, response);
		}else if (uri.contains("register")) {
			this.register(request, response);
		}else if (uri.contains("logout")) {
			this.logout(request, response);
		}else {
			System.out.println("Error doGet AccountServlet!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if (uri.contains("signup")) {
			this.signup(request, response);
		}else if (uri.contains("signin")) {
			this.signin(request, response);
		}
		else {
			System.out.println("Error doPost AccountServlet!");
		}
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("view", "/views/account/login.jsp");
		request.setAttribute("pageTitle", "Đăng nhập");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		
	}
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.invalidate();
//		request.setAttribute("view", "/views/home.jsp");
//		request.setAttribute("pageTitle", "Trang chủ");
//		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
		response.sendRedirect("/NikaShop/Home");
		
	}
	
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("view", "/views/account/register.jsp");
		request.setAttribute("pageTitle", "Đăng ký tài khoản");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	protected void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			//Image
			Part part = request.getPart("avatar");
			
			String realPath = request.getServletContext().getRealPath("/images");
			String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
			
			if (!Files.exists(Path.of(realPath))) {
				Files.createDirectory(Path.of(realPath));
			}
			
			part.write(realPath+"/"+fileName);
			//Image
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
			response.sendRedirect("/NikaShop/account/login");
		} catch (Exception e) {
			// TODO: handle exception
			response.sendRedirect("/NikaShop/account/register");
		}
		
	}
	
	protected void signin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		try {
			User user = new User();
			String email = request.getParameter("email");
//			System.out.println(request.getParameter("email"));
			String password = request.getParameter("password");
			user = callUserDao.findByEmail(email, true);
			boolean checkLogin = EncryptUtil.checkPw(password, user.getPassword());
			if (checkLogin) {
				session.setAttribute("user", user);
//				System.out.println(user.getRole());
				session.setAttribute("message", "Đăng nhập thành công!");
				response.sendRedirect("/NikaShop/Home");
			}else {
				session.setAttribute("error", "Tài khoản, mật khẩu không đúng hoặc tài khoản đã bị vô hiệu hóa!");
				response.sendRedirect("/NikaShop/account/login");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.setAttribute("error", "Tài khoản, mật khẩu không đúng hoặc tài khoản đã bị vô hiệu hóa!");
			response.sendRedirect("/NikaShop/account/login");
		}
	}

}
