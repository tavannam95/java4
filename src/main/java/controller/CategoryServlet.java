package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import dao.CategoryDao;
import dao.ProductDao;
import entities.Category;
import entities.Product;

/**
 * Servlet implementation class CategoryServlet
 */
@MultipartConfig
@WebServlet({ "/admin/category/index", "/admin/category/create", "/admin/category/store", "/admin/category/edit",
		"/admin/category/update", "/admin/category/delete", "/admin/category/show"

})
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryDao callCategoryDao = new CategoryDao();
	ProductDao callProductDao = new ProductDao();
	List<Category> listCates = new ArrayList<Category>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if (uri.contains("index")) {
			this.index(request, response);
		} else if (uri.contains("create")) {
			this.create(request, response);
		} else if (uri.contains("edit")) {
			this.edit(request, response);
		} else if (uri.contains("delete")) {
			this.delete(request, response);
		} else if (uri.contains("show")) {
			this.show(request, response);
		} else {
			System.out.println("Error doGet Category!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if (uri.contains("store")) {
			this.store(request, response);
		} else if (uri.contains("update")) {
			this.update(request, response);
		} else {
			System.out.println("Error doPost Category!");
		}
	}

	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Category> listCategory = new ArrayList<Category>();
		String sql = "SELECT o FROM Category o ";
		listCategory = this.callCategoryDao.findAll(sql, Category.class);
		request.setAttribute("listCategory", listCategory);
		request.setAttribute("view", "/views/admin/category/index.jsp");
		request.setAttribute("pageTitle", "Quản lý danh mục");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	protected void create(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("view", "/views/admin/category/create.jsp");
		request.setAttribute("pageTitle", "Thêm danh mục");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	protected void store(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Category category = new Category();
		HttpSession session = request.getSession();
		Date date = new Date();
		listCates = callCategoryDao.findAll(true, 1);
		try {
//			String name = request.getParameter("name");
//			Part part = request.getPart("icon");
//			
//			String realPath = request.getServletContext().getRealPath("/images");
//			String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
//			
//			if (!Files.exists(Path.of(realPath))) {
//				Files.createDirectory(Path.of(realPath));
//			}
//			
//			part.write(realPath+"/"+fileName);
			category.setName(request.getParameter("name"));
			category.setCreateat(date);
			category.setUpdateat(date);
			category.setStatus(1);
			Category category2 = this.callCategoryDao.create(category);
			System.out.println(category2.getId());
			listCates.add(category2);
			session.setAttribute("message", "Thêm mới thành công!");
			session.setAttribute("listCates", listCates);
			response.sendRedirect("/NikaShop/admin/category/index");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.setAttribute("error", "Thêm mới thất bại!");
			response.sendRedirect("/NikaShop/admin/category/create");
		}
	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		Category category = this.callCategoryDao.findById(id, false);
		request.setAttribute("category", category);
		request.setAttribute("view", "/views/admin/category/edit.jsp");
		request.setAttribute("pageTitle", "Chỉnh sửa danh mục");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String btn = request.getParameter("btncategory");
		Category category = new Category();
		category = this.callCategoryDao.findById(Integer.parseInt(request.getParameter("id")), false);
		System.out.println(btn);
		if (btn.equals("ok")) {
			try {
				BeanUtils.populate(category, request.getParameterMap());
				this.callCategoryDao.update(category);
				session.setAttribute("message", "Cập nhật thành công!");
				listCates = callCategoryDao.findAll(true, 1);
				session.setAttribute("listCates", listCates);
				response.sendRedirect("/NikaShop/admin/category/index");

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.setAttribute("error", "Cập nhật thất bại!");
				response.sendRedirect("/NikaShop/admin/category/update?id=" + category.getId());
			}
		} else if (btn.equals("inactive")) {
			try {
				BeanUtils.populate(category, request.getParameterMap());
				category.setStatus(0);
				this.callCategoryDao.update(category);
				session.setAttribute("message", "Hủy danh mục thành công!");
				listCates = callCategoryDao.findAll(true, 1);
				session.setAttribute("listCates", listCates);
				response.sendRedirect("/NikaShop/admin/category/index");
			} catch (Exception e) {
				// TODO: handle exception
				session.setAttribute("message", "Hủy danh mục thất bại!");
				response.sendRedirect("/NikaShop/admin/category/update?id=" + category.getId());
			}
		} else if (btn.equals("active")) {
			try {
				BeanUtils.populate(category, request.getParameterMap());
				category.setStatus(1);
				this.callCategoryDao.update(category);
				session.setAttribute("message", "Kích hoạt danh mục thành công!");
				listCates = callCategoryDao.findAll(true, 1);
				session.setAttribute("listCates", listCates);
				response.sendRedirect("/NikaShop/admin/category/index");
			} catch (Exception e) {
				// TODO: handle exception
				session.setAttribute("message", "Kích hoạt danh mục thất bại!");
				response.sendRedirect("/NikaShop/admin/category/update?id=" + category.getId());
			}
		} else {
			System.out.println("Error Button update Category!");
			response.sendRedirect("/NikaShop/admin/users/update?id=" + category.getId());
		}

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		// Pagination
		Category category = this.callCategoryDao.findById(id,true);
		List<Product> countProducts = category.getProducts();
		String pageNumberString = request.getParameter("page");
//				System.out.println(countProducts.size());

		int maxPage = (int) Math.ceil((double) countProducts.size() / 8);
		List<Product> listProducts;
		if (pageNumberString.equals("0")) {
			listProducts = this.callProductDao.showProducts(id,1, 8);
			request.setAttribute("currentPage", 1);
		} else {
			int pageNumber = Integer.parseInt(pageNumberString);
			listProducts = this.callProductDao.showProducts(id,pageNumber, 8);
			request.setAttribute("currentPage", pageNumber);
		}
		request.setAttribute("cateid", id);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("listProducts", listProducts);
		request.setAttribute("view", "/views/admin/category/show.jsp");
		request.setAttribute("pageTitle", category.getName());
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);

	}

}
