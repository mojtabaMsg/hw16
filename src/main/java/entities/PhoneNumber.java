package entities;

import javax.persistence.*;

@Entity
@Table(name = "phone")
public class PhoneNumber {
    @Id
    @Column(name = "id_phone")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String telNumber;

    private String mobNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getMobNumber() {
        return mobNumber;
    }

    public void setMobNumber(String mobNumber) {
        this.mobNumber = mobNumber;
    }
}
