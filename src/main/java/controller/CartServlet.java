package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDao;
import dao.ProductDao;
import entities.Cart;
import entities.Product;
import entities.User;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet({
	"/admin/carts/index",
	"/admin/carts/create",
	"/admin/carts/store",
	"/admin/carts/edit",
	"/admin/carts/update",
	"/admin/carts/delete",
	"/admin/carts/show"
	
})
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductDao callProductDao = new ProductDao();
    private CartDao callCartDao = new CartDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
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
		}else if (uri.contains("update")) {
			this.update(request, response);
		}else if (uri.contains("delete")) {
			this.delete(request, response);
		}else if (uri.contains("show")) {
			this.show(request, response);
		}else {
			System.out.println("Error doGet CartServlet!");
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
		}else if (uri.contains("edit")) {
			this.edit(request, response);
		}else {
			System.out.println("Error doPost CartServlet!");
		}
	}
	
	protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String sql = "SELECT o FROM Cart o WHERE o.user.id = " + user.getId();

		List<Cart> listCarts = this.callCartDao.findAll(sql, Cart.class);
		
		request.setAttribute("listCarts", listCarts);
		
		request.setAttribute("view", "/views/admin/carts/index.jsp");
		request.setAttribute("pageTitle", "Giỏ hàng của bạn");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}
	
	protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		Cart cart = new Cart();
		User u = (User) session.getAttribute("user");
		Product product = new Product();
		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		int amount = Integer.parseInt(request.getParameter("amount"));
		try {
			List<Cart> listCarts = this.callCartDao.findAllByUser(u.getId());
			boolean check = false;
			for (Cart cart2 : listCarts) {
				if (cart2.getProduct().getId()==id) {
					check = true;
					break;
				}
			}
			if (check) {
				session.setAttribute("error", "Sản phẩm đã có trong giỏ hàng!");
			}else {
				product = this.callProductDao.findByIdProduct(id);
				cart.setProduct(product);
				cart.setUser(u);
				cart.setQuantity(amount);
				cart = this.callCartDao.create(cart);
				session.setAttribute("message", "Thêm vào giỏ hàng thành công!");
			}
			response.sendRedirect("/NikaShop/admin/carts/index");
		} catch (Exception e) {
			// TODO: handle exception
			session.setAttribute("message", "Thêm vào giỏ hàng thất bại!");
			response.sendRedirect("/NikaShop/admin/carts/show?id="+id);
		}
		
	}
	
	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		try {
			String idUserStr = request.getParameter("iduser"), idProStr = request.getParameter("idpro");
			int idUser = Integer.parseInt(idUserStr);
			int idPro = Integer.parseInt(idProStr);
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			Cart cart = this.callCartDao.findByUserAndPro(idUser, idPro);
			cart.setQuantity(quantity);
			cart = this.callCartDao.update(cart);
			session.setAttribute("message", "Cập nhật thành công!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.setAttribute("error", "Cập nhật thất bại!");
		}
		response.sendRedirect("/NikaShop/admin/carts/index");
	}
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
