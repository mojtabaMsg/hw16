package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id_employee")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "employee_code",nullable = false,unique = true)
    private Integer empCode;

    @Column(name = "last_name",nullable = false,length = 40)
    private String lastName;

    private Double salary;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_emp_adrs")
    private Set<Address> addresses;

    public Integer getEmpCode() {
        return empCode;
    }

    public void setEmpCode(Integer empCode) {
        this.empCode = empCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", empCode=" + empCode +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
