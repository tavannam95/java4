package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDao;
import dao.OrderDao;
import entities.Cart;
import entities.Order;
import entities.User;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet({
	"/admin/order/index",
	"/admin/order/create",
	"/admin/order/store",
	"/admin/order/edit",
	"/admin/order/update",
	"/admin/order/delete",
	"/admin/order/show"
	
})
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CartDao callCartDao = new CartDao();  
    private OrderDao callOrderDao = new OrderDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
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
		}else if (uri.contains("edit")) {
			this.edit(request, response);
		}else if (uri.contains("delete")) {
			this.delete(request, response);
		}else if (uri.contains("show")) {
			this.show(request, response);
		}else {
			System.out.println("Error doGet OrderServlet!");
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
			System.out.println("Error doPost OrderServlet!");
		}
	}

	protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
		User user = (User) session.getAttribute("user");
		List<Cart> listCarts = new ArrayList<Cart>();
		listCarts = this.callCartDao.findAllByUser(user.getId());
		for (Cart cart : listCarts) {
			System.out.println(cart.getUser().getEmail());
		}
		Order order = new Order();
		try {
			listCarts = this.callCartDao.findAllByUser(user.getId());
			double total = 0;
			for (Cart cart : listCarts) {
				total+= (cart.getProduct().getPrice()*cart.getQuantity());
			}
//			System.out.println(total);
			Date date = new Date();
			order.setTotal(total);
			order.setStatus(1);
			order.setOrderat(date);
			order.setShipname(user.getFullname());
			order.setShipaddress(user.getAddress());
			order.setShipemail(user.getEmail());
			order.setShipphone(user.getPhone());
			order.setUser(user);
			
//			System.out.println(order.getTotal());
//			System.out.println(order.getStatus());
//			System.out.println(order.getOrderat());
//			System.out.println(order.getShipaddress());
//			System.out.println(order.getShipemail());
//			System.out.println(order.getShipname());
//			System.out.println(order.getShipphone());
			
			
			//Create
			this.callOrderDao.create(order);
			
			
			
//			for (Cart cart : listCarts) {
//				Orderdetail orderdetail = new Orderdetail();
//				orderdetail.setOrder(order);
//				orderdetail.setProduct(cart.getProduct());
//				orderdetail.setQuantity(cart.getQuantity());
//				orderdetail.setPrice((float)cart.getProduct().getPrice());
//				this.callOrderDetailDao.create(orderdetail);
//			}
//			
//			for (Cart cart : listCarts) {
//				this.callCartDao.delete(cart);
//			}
			session.setAttribute("error", "Đặt hàng thành công!");
			response.sendRedirect("/NikaShop/admin/order/show");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.setAttribute("error", "Đặt hàng thất bại!");
			response.sendRedirect("/NikaShop/admin/carts/index");
		}
	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
		
		request.setAttribute("view", "/views/admin/orders/show.jsp");
		request.setAttribute("pageTitle", "Đơn hàng");
		request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
	}

	
	
}
