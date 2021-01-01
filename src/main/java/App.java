

import dataAccess.AbstractDao;
import dataAccess.AddressDao;
import dataAccess.EmployeeDao;
import dataAccess.PhoneDao;
import entities.Address;
import entities.Employee;
import entities.PhoneNumber;
import util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

public class App {
    private static EmployeeDao employeeDao;
    private static AddressDao addressDao;
    private static PhoneDao phoneDao;


    public static void main(String[] args) {
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        initializeDao(entityManager);
        entityManager.getTransaction().begin();
        initializeData();

        entityManager.getTransaction().commit();

        entityManager.close();
        JpaUtil.shutdown();
    }




    private static void initializeDao(EntityManager entityManager) {
        employeeDao = new EmployeeDao(entityManager);
        addressDao = new AddressDao(entityManager);
        phoneDao = new PhoneDao(entityManager);
    }

    private static void initializeData(){
        Set<PhoneNumber> phoneNumbers = new HashSet<>();
        Set<PhoneNumber> phoneNumbers2 = new HashSet<>();
        Set<PhoneNumber> phoneNumbers3 = new HashSet<>();
        Set<PhoneNumber> phoneNumbers4 = new HashSet<>();


        PhoneNumber phoneNumber = createPhoneNumber("021564645","091554845");
        PhoneNumber phoneNumber2 = createPhoneNumber("021561655","0915551611");
        PhoneNumber phoneNumber3 = createPhoneNumber("021655656","0915565451");
        PhoneNumber phoneNumber4 = createPhoneNumber("02165515656","0917565451");

        phoneNumbers.add(phoneNumber);
        phoneNumbers2.add(phoneNumber2);
        phoneNumbers2.add(phoneNumber3);
        phoneNumbers4.add(phoneNumber4);

        Address address = createAddress("0214446464","Qom","546465","Street 1",phoneNumbers);
        address.setPhoneNumbers(phoneNumbers);

        Address address2 = createAddress("021565448","Ilam","546444","Street 3",phoneNumbers2);
        address.setPhoneNumbers(phoneNumbers2);

        Address address3 = createAddress("02156511448","Karaj","561133","Street 4",phoneNumbers3);
        address.setPhoneNumbers(phoneNumbers3);

        Address address4 = createAddress("021565448","Kish","515332","Street 5",phoneNumbers4);

        Set<Address> addresses = new HashSet<>();
        Set<Address> addresses2 = new HashSet<>();
        Set<Address> addresses3 = new HashSet<>();

        addresses.add(address);
        addresses.add(address2);
        addresses2.add(address3);
        addresses3.add(address4);

        Employee employee = createEmployee(455,"Reza",200.0,addresses);
        Employee employee2 = createEmployee(434,"Javad",350.0,addresses2);
        Employee employee3 = createEmployee(456,"Reza",550.0,addresses3);




//        employee.setAddresses(addresses);
        System.out.println("the most salary is : "+employeeDao.mostIncome());
        System.out.println("the Employee Has Most Salary : "+employeeDao.theEmployeeHasMostSalary());

//        DELETE
        employeeDao.delete(employee);

    }

    private static Employee createEmployee(Integer empCode, String lastName,
                                           Double salary,Set<Address> addresses){
        Employee employee = new Employee();
        employee.setLastName(lastName);
        employee.setEmpCode(empCode);
        employee.setSalary(salary);
        employee.setAddresses(addresses);
        employeeDao.save(employee);
        return  employee;
    }

    private static PhoneNumber createPhoneNumber(String tel, String mob) {
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setTelNumber(tel);
        phoneNumber.setMobNumber(mob);
        phoneDao.save(phoneNumber);
        return phoneNumber;
    }

    private static Address createAddress(String number, String city, String postalCode,
                                         String postalAddress,Set<PhoneNumber> phoneNumbers){
        Address address = new Address();
        address.setCity(city);
        address.setPostalAddress(postalAddress);
        address.setNumber(number);
        address.setPostalCode(postalCode);
        address.setPhoneNumbers(phoneNumbers);
        addressDao.save(address);
        return address;
    }
}
