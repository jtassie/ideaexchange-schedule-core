package org.ideaexchange.dao;

import org.ideaexchange.entity.LocationEntity;

public interface LocationDao {
	public LocationEntity getLocationByName(String name);
	public LocationEntity getLocationById(long id);
}