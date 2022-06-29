package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import entities.Order;
import entities.User;

public class OrderDao extends BaseDao<Order>{
	@Override
	public Order create(Order entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}
	@Override
	public Order delete(Order entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}
	@Override
	public Order update(Order entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}
	@Override
	public List<Order> findAll(String sql, Class<Order> clazz) {
		// TODO Auto-generated method stub
		return super.findAll(sql, clazz);
	}
	
	public List<Order> findAllByUser(User user) {
		String sql = "SELECT o FROM Order o WHERE o.user = :user ";
		TypedQuery<Order> query = callEntityManager.createQuery(sql,Order.class);
		query.setParameter("user", user);
		return query.getResultList();
	}
	
}
