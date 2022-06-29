package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import dao.CategoryDao;
import dao.ColorDao;
import dao.ProductDao;
import dao.SizeDao;
import entities.Category;
import entities.Color;
import entities.Product;
import entities.Size;

/**
 * Servlet implementation class ProductServlet
 */
@MultipartConfig
@WebServlet({ "/admin/product/index", "/admin/product/create", "/admin/product/store", "/admin/product/edit",
		"/admin/product/update", "/admin/product/delete", "/admin/product/show"

})
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao callProductDao = new ProductDao();
	private CategoryDao callCategoryDao = new CategoryDao();
	private ColorDao callColorDao = new ColorDao();
	private SizeDao callSizeDao = new SizeDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
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
			System.out.println("Error doGet Product!");
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
			System.out.println("Error doPost product!");
		}
	}

	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Product> listProducts = new ArrayList<Product>();
		String sql = "SELECT o FROM Product o ";
		listProducts = this.callProductDao.findAll(sql, Product.class);

		request.setAttribute("listProduct", listProducts);

		request.setAttribute("view", "/views/admin/product/index.jsp");
		request.setAttribute("pageTitle", "Quản lý sản phẩm");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	protected void create(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Category> listCategories = this.callCategoryDao.findAll(true, 1);

		List<Color> listColors = new ArrayList<Color>();
		String sqlColor = "SELECT o FROM Color o ";
		listColors = this.callColorDao.findAll(sqlColor, Color.class);

		List<Size> listSizes = new ArrayList<Size>();
		String sqlSize = "SELECT o FROM Size o ";
		listSizes = this.callSizeDao.findAll(sqlSize, Size.class);

		request.setAttribute("listColors", listColors);
		request.setAttribute("listSizes", listSizes);

		request.setAttribute("listCategories", listCategories);
		request.setAttribute("view", "/views/admin/product/create.jsp");
		request.setAttribute("pageTitle", "Thêm mới sản phẩm");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	protected void store(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Date date = new Date();
		// Images

		Part part = request.getPart("images");
		String realPath = request.getServletContext().getRealPath("/images");
		String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
		if (!Files.exists(Path.of(realPath))) {
			Files.createDirectories(Path.of(realPath));
		}
		part.write(realPath + "/" + fileName);

		// Images

		HttpSession session = request.getSession();
		try {
			Color color = new Color();
			color = this.callColorDao.findById(Integer.parseInt(request.getParameter("colors2")));
			List<Color> listColors = new ArrayList<Color>();
			listColors.add(color);
			
			Size size = new Size();
			size = this.callSizeDao.findById(Integer.parseInt(request.getParameter("sizes2")));
			List<Size> listSizes = new ArrayList<Size>();
			
			listSizes.add(size);
			
			

			Product product = new Product();
			BeanUtils.populate(product, request.getParameterMap());
			Category category = new Category();
			category = callCategoryDao.findById(Integer.parseInt(request.getParameter("categories")), true);
			product.setCategory(category);
			product.setImage(fileName);
			product.setCreateat(date);
			product.setUpdateat(date);
			product.setStatus(1);
			
			product.setColors(listColors);
			product.setSizes(listSizes);
			
			product = this.callProductDao.create(product);
			List<Product> listProducts = new ArrayList<Product>();
			listProducts.add(product);

			// Session Product

			session.setAttribute("message", "Thêm mới thành công!");
			response.sendRedirect("/NikaShop/admin/product/index");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.setAttribute("error", "Thêm mới thất bại!");
			response.sendRedirect("/NikaShop/admin/product/create");
		}

	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		Product product = this.callProductDao.findByIdProduct(id);
		request.setAttribute("product", product);
		request.setAttribute("view", "/views/admin/product/edit.jsp");
		request.setAttribute("pageTitle", "Chỉnh sửa sản phẩm");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);

	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String btn = request.getParameter("btn-update");
		Product product = new Product();
		product = this.callProductDao.findByIdProduct(Integer.parseInt(request.getParameter("idpro")));
		if (btn == null || btn.equals("ok")) {
			try {
				BeanUtils.populate(product, request.getParameterMap());
				this.callProductDao.update(product);
				session.setAttribute("message", "Cập nhật thành công!");
				response.sendRedirect("/NikaShop/admin/product/index");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				session.setAttribute("error", "Cập nhật thất bại!");
				response.sendRedirect("/NikaShop/admin/product/update?id=" + product.getId());
			}
		} else {
			if (btn.equals("inactive")) {
				try {
					BeanUtils.populate(product, request.getParameterMap());
					product.setStatus(0);
					this.callProductDao.update(product);
					session.setAttribute("message", "Hủy tài khoản thành công!");
					response.sendRedirect("/NikaShop/admin/product/index");
				} catch (Exception e) {
					// TODO: handle exception
					session.setAttribute("message", "Hủy tài khoản thất bại!");
					response.sendRedirect("/NikaShop/admin/product/update?id=" + product.getId());
				}
			} else if (btn.equals("active")) {
				try {
					BeanUtils.populate(product, request.getParameterMap());
					product.setStatus(1);
					this.callProductDao.update(product);
					session.setAttribute("message", "Kích hoạt tài khoản thành công!");
					response.sendRedirect("/NikaShop/admin/product/index");
				} catch (Exception e) {
					// TODO: handle exception
					session.setAttribute("message", "Kích hoạt tài khoản thất bại!");
					response.sendRedirect("/NikaShop/admin/product/update?id=" + product.getId());
				}
			} else if (btn.equals("image")) {
				try {

					// Images

					Part part = request.getPart("images");
					String realPath = request.getServletContext().getRealPath("/images");
					String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
					if (!Files.exists(Path.of(realPath))) {
						Files.createDirectories(Path.of(realPath));
					}
					part.write(realPath + "/" + fileName);

					// Images
//					BeanUtils.populate(product,request.getParameterMap());
					product.setImage(fileName);
					this.callProductDao.update(product);
					session.setAttribute("message", "Cập nhật thành công!");
					response.sendRedirect("/NikaShop/admin/product/index");
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					session.setAttribute("error", "Cập nhật thất bại!");
					response.sendRedirect("/NikaShop/admin/product/update?id=" + product.getId());
				}

			} else {
				System.out.println("Error btn!");
			}
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
		Product product = this.callProductDao.findByIdProduct(id);
		
		List<Size> listSizes = product.getSizes();
		List<Color> listColors = product.getColors();
		
		request.setAttribute("listColors", listColors);
		request.setAttribute("listSizes", listSizes);
		request.setAttribute("product", product);
		request.setAttribute("view", "/views/admin/product/show.jsp");
		request.setAttribute("pageTitle", product.getName());
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

}
