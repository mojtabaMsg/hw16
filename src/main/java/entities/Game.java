package entities;

import javax.persistence.*;

@Entity
public class Game {
    @Id
    @Column(name = "id_game")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "host_team")
    private String hostTeam;

    @Column(name = "away_team")
    private String awayTeam;

    @Column(name = "host_Team_Goals")
    private int hostTeamGoals;

    @Column(name = "away_Team_Goals")
    private int awayTeamGoals;

    @Column(name = "host_Team_Point")
    private int hostTeamPoint;

    @Column(name = "away_Team_Point")
    private int awayTeamPoint;

    @ManyToOne
    @JoinColumn(name = "fk_match_stadium")
    private Stadium stadium;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHostTeam() {
        return hostTeam;
    }

    public void setHostTeam(String hostTeam) {
        this.hostTeam = hostTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHostTeamGoals() {
        return hostTeamGoals;
    }

    public void setHostTeamGoals(int hostTeamGoals) {
        this.hostTeamGoals = hostTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    public int getHostTeamPoint() {
        return hostTeamPoint;
    }

    public void setHostTeamPoint(int hostTeamPoint) {
        this.hostTeamPoint = hostTeamPoint;
    }

    public int getAwayTeamPoint() {
        return awayTeamPoint;
    }

    public void setAwayTeamPoint(int awayTeamPoint) {
        this.awayTeamPoint = awayTeamPoint;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }
}
