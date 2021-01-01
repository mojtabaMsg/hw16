package dataAccess;

import entities.Stadium;

import javax.persistence.EntityManager;

public class StadiumDao extends AbstractDao<Stadium,Integer> {
    public StadiumDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Stadium> getEntityClass() {
        return Stadium.class;
    }

}

