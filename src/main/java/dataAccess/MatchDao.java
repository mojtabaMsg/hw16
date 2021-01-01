package dataAccess;

import entities.Game;

import javax.persistence.EntityManager;

public class MatchDao extends AbstractDao<Game,Integer> {
    public MatchDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Game> getEntityClass() {
        return Game.class;
    }


}
