package dataAccess;

import entities.Coach;
import entities.Player;

import javax.persistence.EntityManager;

public class CoachDao extends AbstractDao<Coach,Integer> {
    public CoachDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Coach> getEntityClass() {
        return Coach.class;
    }


}
