

import dataAccess.*;;
import entities.*;

import util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

public class App {
    private static TeamDao teamDao;
    private static PlayerDao playerDao;
    private static CityDao cityDao;
    private static StadiumDao stadiumDao;
    private static MatchDao matchDao;
    private static CoachDao coachDao;


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
        teamDao = new TeamDao(entityManager);
        playerDao = new PlayerDao(entityManager);
        cityDao = new CityDao(entityManager);
        stadiumDao = new StadiumDao(entityManager);
        matchDao = new MatchDao(entityManager);
        coachDao = new CoachDao(entityManager);
    }

    private static void initializeData(){
        Player player = createPlayer("Ali","Daei",200.0);
        Player player2 = createPlayer("Mehdi","Mahdavi",210.0);
        Player player3 = createPlayer("Farhad","Majidi",190.0);
        Player player4 = createPlayer("Mehdi","Taremi",250.0);
        Player player5 = createPlayer("Arash","Borhani",250.0);

        Set<Player> players = new HashSet<>();
        players.add(player);
        players.add(player2);
        Set<Player> players2 = new HashSet<>();
        players2.add(player3);
        players2.add(player4);

        Coach coach = createCoach("Amir","Ghale",250.5);
        Coach coach2 = createCoach("Yahya","Golmohamadi",350.5);
        Coach coach3 = createCoach("Majid","Jalali",320.5);
        Coach coach4 = createCoach("Mahmood","Yavari",300.5);

        City city = createCity("Tehran");
        City city2 = createCity("Tabriz");
        City city3 = createCity("Ahvaz");
        City city4 = createCity("Mashad");
        City city5 = createCity("Shiraz");

        Stadium stadium = createStadium("Azadi",city);
        Stadium stadium2 = createStadium("Takhti",city);
        Stadium stadium3 = createStadium("Foolad",city3);
        Stadium stadium4 = createStadium("Yadegar",city4);

        Team team = createTeam("Perspolis",players,coach2,city);
        Team team2 = createTeam("Es degh lal",players2,coach3,city);
        Team team4 = createTeam("Pas",players2,coach3,city5);

        Game game = createMatch(team.getTeamName(),team2.getTeamName(),1,0,3,0,stadium);
        //select-------------------------------------------
        System.out.println(teamDao.load(1));
        //update-------------------------------------------
        team2.setTeamName("Esteghlool");
        teamDao.update(team2);
        //delete-------------------------------------------
//        cityDao.delete(city5);
        teamDao.delete(team4);

    }

    private static City createCity(String cityName){
        City city = new City();
        city.setCityName(cityName);
        cityDao.save(city);
        return  city;
    }

    private static Stadium createStadium(String stadiumName,City city){
        Stadium stadium = new Stadium();
        stadium.setStadiumName(stadiumName);
        stadium.setCity(city);
        stadiumDao.save(stadium);
        return  stadium;
    }


    private static Team createTeam(String teamName, Set<Player> players, Coach coach, City city) {
        Team team = new Team();
        team.setCity(city);
        team.setCoach(coach);
        team.setPlayers(players);
        team.setTeamName(teamName);
        teamDao.save(team);
        return team;
    }

    private static Player createPlayer(String firstName, String lastName, Double salary) {
        Player player = new Player();
        player.setFirstName(firstName);
        player.setLastName(lastName);
        player.setSalary(salary);
        playerDao.save(player);
        return player;
    }

    private static Coach createCoach(String firstName, String lastName, Double salary) {
        Coach coach = new Coach();
        coach.setFirstName(firstName);
        coach.setLastName(lastName);
        coach.setSalary(salary);
        coachDao.save(coach);
        return coach;
    }

    private static Game createMatch(String hostTeam, String awayTeam, int hostTeamGoals, int awayTeamGoals,
                                    int hostTeamPoint, int awayTeamPoint, Stadium stadium) {
        Game game = new Game();
        game.setAwayTeam(awayTeam);
        game.setHostTeam(hostTeam);
        game.setAwayTeamGoals(awayTeamGoals);
        game.setHostTeamGoals(hostTeamGoals);
        game.setAwayTeamPoint(awayTeamPoint);
        game.setHostTeamPoint(hostTeamPoint);
        game.setStadium(stadium);
        matchDao.save(game);
        return game;
    }
}
