package dataAccess;

import entities.PhoneNumber;

import javax.persistence.EntityManager;

public class PhoneDao extends AbstractDao<PhoneNumber,Integer> {
    public PhoneDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<PhoneNumber> getEntityClass() {
        return PhoneNumber.class;
    }

}

