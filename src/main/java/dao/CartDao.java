package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import entities.Cart;

public class CartDao extends BaseDao<Cart>{
	@Override
	public Cart create(Cart entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}
	@Override
	public Cart delete(Cart entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}
	@Override
	public Cart update(Cart entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}
	@Override
	public List<Cart> findAll(String sql, Class<Cart> clazz) {
		// TODO Auto-generated method stub
		return super.findAll(sql, clazz);
	}
	
	public List<Cart> findAllByUser(int id) {
		String sql = "SELECT o FROM Cart o WHERE o.user.id = :iduser";
		TypedQuery<Cart> query = callEntityManager.createQuery(sql,Cart.class);
		query.setParameter("iduser", id);
		return query.getResultList();
	}
	
	public Cart findByUserAndPro(int idUser, int idPro) {
		String sql = "SELECT o FROM Cart o WHERE o.user.id = :iduser AND o.product.id = :idpro ";
		TypedQuery<Cart> query = callEntityManager.createQuery(sql,Cart.class);
		query.setParameter("iduser", idUser);
		query.setParameter("idpro", idPro);
		return query.getResultList().get(0);
	}
	
}
