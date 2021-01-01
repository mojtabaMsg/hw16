package dataAccess;

import javax.persistence.EntityManager;

public abstract class AbstractDao<E,I> {
    protected EntityManager entityManager;

    public AbstractDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save(E entity){
        entityManager.persist(entity);
    }

    public E load(I id){
        return entityManager.find(getEntityClass(),id);
    }

    public void delete(E entity){entityManager.remove(entity);}

    public abstract Class<E> getEntityClass();
}
