package org.ideaexchange.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.ideaexchange.dao.LocationDao;
import org.ideaexchange.entity.LocationEntity;
import org.ideaexchange.util.HibernateUtil;


public class LocationDaoImpl implements LocationDao {
	
	public LocationEntity getLocationByName(String name) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<LocationEntity> criteria = builder.createQuery(LocationEntity.class);
		Root<LocationEntity> locationRoot = criteria.from(LocationEntity.class);
		criteria.where(builder.equal(locationRoot.get("name"), name));
		
		LocationEntity location = HibernateUtil.getEntityManager().createQuery(criteria).getSingleResult();
		return location;
	}

	public LocationEntity getLocationById(long id) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		LocationEntity location = (LocationEntity)session.get(LocationEntity.class, (long) id); 
		return location;
	}
}