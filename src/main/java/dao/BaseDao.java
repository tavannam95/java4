package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import util.JpaUtil;

public class BaseDao<T> {
	EntityManager callEntityManager = JpaUtil.getEntityManager();
	public T create(T entity) {
		try {
			callEntityManager.getTransaction().begin();
			callEntityManager.persist(entity);
			callEntityManager.flush();
			callEntityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			callEntityManager.getTransaction().rollback();
			throw new RuntimeException();
		}
	}
	public T update(T entity) {
		try {
			callEntityManager.getTransaction().begin();
			callEntityManager.merge(entity);
			callEntityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			callEntityManager.getTransaction().rollback();
			throw new RuntimeException();
		}
	}
	public T delete(T entity) {
		try {
			callEntityManager.getTransaction().begin();
			callEntityManager.remove(entity);
			callEntityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			callEntityManager.getTransaction().rollback();
			throw new RuntimeException();
		}
	}
	public List<T> findAll(String sql,Class<T> clazz) {
		TypedQuery<T> query = callEntityManager.createQuery(sql, clazz);
		return (List<T>) query.getResultList();
	}
	
}