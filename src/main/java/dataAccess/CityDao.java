package dataAccess;

import entities.City;
import entities.Player;

import javax.persistence.EntityManager;

public class CityDao extends AbstractDao<City,Integer> {
    public CityDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<City> getEntityClass() {
        return City.class;
    }


}
