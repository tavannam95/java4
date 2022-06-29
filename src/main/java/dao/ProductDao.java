package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import entities.Product;

public class ProductDao extends BaseDao<Product>{
	@Override
	public Product create(Product entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}
	@Override
	public Product update(Product entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}
	@Override
	public Product delete(Product entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}
	@Override
	public List<Product> findAll(String sql, Class<Product> clazz) {
		// TODO Auto-generated method stub
		return super.findAll(sql, clazz);
	}
	
	public List<Product> showProducts(int pageNumber, int pageSize) {
		String sql = "SELECT o FROM Product o WHERE o.status = 1";
		TypedQuery<Product> query = callEntityManager.createQuery(sql, Product.class);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
		
	}
	
	public List<Product> showProducts(int id,int pageNumber, int pageSize) {
		String sql = "SELECT o FROM Product o WHERE o.status = 1 AND o.category.id = " + id;
		TypedQuery<Product> query = callEntityManager.createQuery(sql, Product.class);
		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
		
	}
	
	public Product findByIdProduct(int id) {
		String sqlString = "SELECT o FROM Product o WHERE o.id = " + id;
		TypedQuery<Product> query = callEntityManager.createQuery(sqlString, Product.class);
		List<Product> list = query.getResultList();
		return list.get(0);
	}
	
}
