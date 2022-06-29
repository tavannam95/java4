package dao;

import java.util.List;

import entities.Image;

public class ImageDao extends BaseDao<Image>{
	@Override
	public Image create(Image entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}
	@Override
	public Image update(Image entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}
	@Override
	public Image delete(Image entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}
	@Override
	public List<Image> findAll(String sql, Class<Image> clazz) {
		// TODO Auto-generated method stub
		return super.findAll(sql, clazz);
	}
}
