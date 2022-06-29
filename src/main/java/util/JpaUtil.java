package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	public static EntityManagerFactory getEntityManagerFactory() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("NikaShop");
		return factory;
	}
	public static EntityManager getEntityManager() {
		return JpaUtil.getEntityManagerFactory().createEntityManager();
	}
}
