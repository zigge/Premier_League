package Model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

//-------Everybody is responsible-------

public class Game implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Player> players;
    private ArrayList<String> goals;
    private String opposingTeam;
    private String strategy;
    private String result;
    private LocalDate gameDate;
    private LocalTime gameTime;

    //Constructor for played game
    public Game(ArrayList<Player> player, String opposingTeam, String result, LocalDate gameDate, LocalTime gameTime, String strategy, ArrayList<String> goals) {
        this.opposingTeam = opposingTeam;
        this.result = result;
        this.gameDate = gameDate;
        this.gameTime = gameTime;
        this.strategy = strategy;
        players = player;
        this.goals = goals;

    }

    //Constructor for upcomming game
    public Game(String opposingTeam, LocalDate gameDate, LocalTime gameTime) {
        this.opposingTeam = opposingTeam;
        this.gameDate = gameDate;
        this.gameTime = gameTime;
    }

    public void getPlayers() {
        for(Player p: players) {
            System.out.println(p);
        }
    }

    public LocalDate getGameDate() {
        return gameDate;
    }

    public String getStrategy() {
        return strategy;
    }

    public String getOpposingTeam() {
        return opposingTeam;
    }

    public String getResult() {
        return result;
    }

    public LocalTime getGameTime() {
        return gameTime;
    }

    public void getGoals() {
        for(String s: goals){
            System.out.println(s);
        }
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setOpposingTeam(String opposingTeam) {
        this.opposingTeam = opposingTeam;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setGameTime(LocalTime gameTime) {
        this.gameTime = gameTime;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public void setGoals(ArrayList<String> goals) {
            this.goals = goals;
    }

    @Override
    public String toString() {
        return "Chelsea vs  " + getOpposingTeam() + " The Result of the game was: " + getResult() + " and it was played on " + getGameDate() + " at " + getGameTime() + " with the strategy " + getStrategy();
    }
}

