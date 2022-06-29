package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import entities.Size;

public class SizeDao extends BaseDao<Size>{
	@Override
	public Size create(Size entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}
	@Override
	public Size update(Size entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	} @Override
	public Size delete(Size entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}
	@Override
	public List<Size> findAll(String sql, Class<Size> clazz) {
		// TODO Auto-generated method stub
		return super.findAll(sql, clazz);
	}
	
	public Size findById(int id) {
		String sql = "SELECT o FROM Size o WHERE o.id = :id ";
		TypedQuery<Size> query = callEntityManager.createQuery(sql, Size.class);
		query.setParameter("id", id);
		List<Size> list = query.getResultList();
//		System.out.println(list.get(0).getUName());
		return list.get(0);
	}
}
