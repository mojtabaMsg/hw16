package dataAccess;

import entities.Address;
import entities.Employee;
import entities.PhoneNumber;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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

    public void searchEmployeeByPostalCode(String postalCode){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> fromEmployee = cq.from(Employee.class);

        Join<Employee, Address> employeeAddressJoin = fromEmployee.join("addresses");
        cq.select(fromEmployee).where(cb.equal(employeeAddressJoin.get("postalCode"),postalCode));

        TypedQuery<Employee> query = entityManager.createQuery(cq);
        System.out.println("The searched Employee by postalCode is : "+ query.getSingleResult());
    }

    public void searchEmployeeByTelNumber(String telNumber){
        CriteriaBuilder cb  = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> fromEmployee = cq.from(Employee.class);

        Join<Employee,Address> employeeAddressJoin = fromEmployee.join("addresses");
        Join<Address, PhoneNumber> addressPhoneNumberJoin = employeeAddressJoin.join("phoneNumbers");
        cq.select(fromEmployee).where(cb.equal(addressPhoneNumberJoin.get("telNumber"),telNumber));

        TypedQuery<Employee> query = entityManager.createQuery(cq);
        System.out.println("The searched Employee by phoneNumber is :"+query.getSingleResult());
    }

    public void mostSalaryBasesCity(){
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
        Root<Employee> fromEmployee = cq.from(Employee.class);
        Subquery<Long> subquery = cq.subquery(Long.class);
        Root<Employee> subRoot = subquery.from(Employee.class);
        subquery.select(cb.max(subRoot.get("salary")));

        Join<Employee,Address> addressJoin = fromEmployee.join("addresses");
        cq.multiselect(fromEmployee.get("salary"),addressJoin.get("city")).where(cb.equal(fromEmployee.get("salary"),subquery));
        TypedQuery<Object[]> query = entityManager.createQuery(cq);
        Object[] reult = query.getSingleResult();
        System.out.println("The most salary bases on city: "+" cityName: "+reult[1]+" salary:"+reult[0]);
    }

        public void getEmployeeWithMostSalaryInEachCity(){
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);
            Root<Employee> fromEmployee = cq.from(Employee.class);
            Join<Employee,Address> addressJoin = fromEmployee.join("addresses");
            cq.multiselect(fromEmployee.get("lastName"),addressJoin.get("city"),cb.max(fromEmployee.get("salary")));
            cq.groupBy(addressJoin.get("city"));

            TypedQuery<Object[]> query = entityManager.createQuery(cq);
            List<Object[]> resultList = query.getResultList();

            System.out.println("the employees that have most salary in each salary: ");
            for (Object[] o : resultList) {
                System.out.println(o[0]+" "+o[1]+" "+o[2]);

            }

    }



}
