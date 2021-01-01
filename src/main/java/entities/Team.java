package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Team {
    @Id
    @Column(name = "id_team")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "team_name",nullable = false,length = 40)
    private String teamName;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    private Set<Player> players;

    @OneToOne(mappedBy = "team",cascade = CascadeType.ALL)
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "fk_city_team")
    private City city;

    @ManyToOne
    @JoinColumn(name = "match_team")
    private Game game;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", players=" + players +
                ", coach=" + coach +
                ", city=" + city +
                ", game=" + game +
                '}';
    }
}
