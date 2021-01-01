package dataAccess;

import entities.Address;

import javax.persistence.EntityManager;

public class AddressDao extends AbstractDao<Address,Integer> {
    public AddressDao(EntityManager entityManager) {
        super(entityManager);
    }

    public Class<Address> getEntityClass() {
        return Address.class;
    }
}
