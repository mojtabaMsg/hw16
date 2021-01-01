package dataAccess;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeDao extends AbstractDao<Employee,Integer> {
    public EmployeeDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }

    public Double mostIncome(){
        TypedQuery<Double> query = super.entityManager.createQuery(
                "SELECT MAX(emp.salary) FROM Employee emp  ",
                java.lang.Double.class
        );

//        Employee employee = query.getSingleResult();
        return query.getSingleResult();
    }

    public Employee theEmployeeHasMostSalary(){
        TypedQuery<Employee> query = super.entityManager.createQuery(
                "SELECT emp FROM Employee emp ORDER BY salary desc ",
                Employee.class
        );

//        Employee employee = query.getSingleResult();
         List<Employee> employees  = query.getResultList();

         return employees.get(0);
    }

}
