package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import entities.User;

public class UserDao extends BaseDao<User>{
	@Override
	public User create(User entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}
	@Override
	public User update(User entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}
	@Override
	public User delete(User entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}
	
	@Override
	public List<User> findAll(String sql, Class<User> clazz) {
		// TODO Auto-generated method stub
		return super.findAll(sql, clazz);
	}
	public User findByEmail(String email,boolean status) {
		String sql;
		if (status) {
			sql = "SELECT o FROM User o WHERE o.email = :email AND o.status = 1";
		}else {
			sql = "SELECT o FROM User o WHERE o.email = :email";
		}
		TypedQuery<User> query = callEntityManager.createQuery(sql, User.class);
		query.setParameter("email", email);
		List<User> list = query.getResultList();
//		System.out.println(list.get(0).getUName());
		return list.get(0);
	}
	
	public User findById(int id, boolean status) {
		String sql;
		if (status) {
			sql = "SELECT o FROM User o WHERE o.id = :id AND o.status = 1";
		}else {
			sql = "SELECT o FROM User o WHERE o.id = :id";
		}
		TypedQuery<User> query = callEntityManager.createQuery(sql, User.class);
		query.setParameter("id", id);
		List<User> list = query.getResultList();
//		System.out.println(list.get(0).getUName());
		return list.get(0);
	}
	
}
