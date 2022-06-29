package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CategoryDao;
import dao.ContactDao;
import dao.ProductDao;
import entities.Category;
import entities.Contact;
import entities.Product;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    List<Category> listCates;
    CategoryDao callCategoryDao = new CategoryDao();
    Contact contact = new Contact();
    ContactDao callContactDao = new ContactDao();
    private ProductDao callProductDao = new ProductDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        this.listCates = callCategoryDao.findAll(true,1);
        this.contact = callContactDao.findAll("SELECT o FROM Contact o ", Contact.class).get(0);
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		if (uri.contains("Home")) {
			this.home(request, response);
		}else {
			System.out.println("Error doGet Home!");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		//Pagination
		
		String sql = "SELECT o FROM Product o WHERE o.status = 1";
		List<Product> countProducts = this.callProductDao.findAll(sql, Product.class);
		String pageNumberString = request.getParameter("page");
//		System.out.println(countProducts.size());
		
		int maxPage = (int) Math.ceil((double)countProducts.size()/8);
		List<Product> listProducts;
		if (pageNumberString == null) {
			listProducts = this.callProductDao.showProducts(1, 8);
			request.setAttribute("currentPage", 1);
		}else {
			int pageNumber = Integer.parseInt(pageNumberString);
			listProducts = this.callProductDao.showProducts(pageNumber, 8);
			request.setAttribute("currentPage", pageNumber);
		}
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("listProducts", listProducts);
		request.setAttribute("view", "/views/home.jsp");
		request.setAttribute("pageTitle", "Trang chủ");
		session.setAttribute("listCates", listCates);
		session.setAttribute("contact", contact);
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
}
