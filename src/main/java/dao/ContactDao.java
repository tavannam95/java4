package dao;

import java.util.List;

import entities.Contact;

public class ContactDao extends BaseDao<Contact>{
	@Override
	public Contact create(Contact entity) {
		// TODO Auto-generated method stub
		return super.create(entity);
	}
	@Override
	public Contact update(Contact entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}
	@Override
	public Contact delete(Contact entity) {
		// TODO Auto-generated method stub
		return super.delete(entity);
	}
	@Override
	public List<Contact> findAll(String sql, Class<Contact> clazz) {
		// TODO Auto-generated method stub
		return super.findAll(sql, clazz);
	}
}
