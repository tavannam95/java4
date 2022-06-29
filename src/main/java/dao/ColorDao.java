package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import entities.Color;
import entities.Size;

public class ColorDao extends BaseDao<Color>{

	@Override
	public Color create(Color entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}
	
	@Override
	public Color delete(Color entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}
	@Override
	public Color update(Color entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}
	
	@Override
	public List<Color> findAll(String sql, Class<Color> clazz) {
		// TODO Auto-generated method stub
		return super.findAll(sql, clazz);
	}
	public Color findById(int id) {
		String sql = "SELECT o FROM Color o WHERE o.id = :id ";
		TypedQuery<Color> query = callEntityManager.createQuery(sql, Color.class);
		query.setParameter("id", id);
		List<Color> list = query.getResultList();
//		System.out.println(list.get(0).getUName());
		return list.get(0);
	}
}
