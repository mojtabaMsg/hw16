package entities;

import javax.persistence.*;

@Entity
public class Stadium {
    @Id
    @Column(name = "id_stadium")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stadium_name")
    private String stadiumName;

    @ManyToOne
    @JoinColumn(name = "fk_city_stadium")
    private City city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
