package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import entities.Category;

public class CategoryDao extends BaseDao<Category>{
	@Override
	public Category create(Category entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}
	@Override
	public Category update(Category entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}
	@Override
	public Category delete(Category entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}
	@Override
	public List<Category> findAll(String sql, Class<Category> clazz) {
		// TODO Auto-generated method stub
		return super.findAll(sql, clazz);
	}
	
	public Category findById(int id, boolean status) {
		String sql;
		if (status) {
			sql = "SELECT o FROM Category o WHERE o.id = :id AND o.status = 1";
		}else {
			sql = "SELECT o FROM Category o WHERE o.id = :id";
		}
		TypedQuery<Category> query = callEntityManager.createQuery(sql, Category.class);
		query.setParameter("id", id);
		List<Category> list = query.getResultList();
//		System.out.println(list.get(0).getUName());
		return list.get(0);
	}
	
	public List<Category> findAll(boolean status, int valueStatus) {
		StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM Category o WHERE o.status = ").append(valueStatus);
		TypedQuery<Category> query = callEntityManager.createQuery(sql.toString(), Category.class);
		List<Category> list = query.getResultList();
//		System.out.println(list.get(0).getUName());
		return list;
	}
	
}
