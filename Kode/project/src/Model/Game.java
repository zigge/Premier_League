package Model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by lassebjorklund on 21/11/15.
 */
public class Game implements Serializable {

    private static final long serialVersionUID = 1L;
    private ArrayList<Player> players;
    private ArrayList<String> goals;
    private String opposingTeam;
    private String result;
    private LocalDate gameDate;
    private LocalTime gameTime;
    private boolean gameCanceled;

//    TODO: Verify this constructor
    public Game(ArrayList<Player> player, String opposingTeam, String result, LocalDate gameDate, LocalTime gameTime, ArrayList<String> goals) {
//    TODO: Fill out constructor for game
        this.opposingTeam = opposingTeam;
        this.result = result;
        this.gameDate = gameDate;
        this.gameTime = gameTime;
        players = player;
        setGoals(goals);

    }
//    TODO: Verify this constructor
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

    public String getOpposingTeam() {
        return opposingTeam;
    }

    public String getResult() {
        return result;
    }

    public LocalTime getGameTime() {
        return gameTime;
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

    public LocalDate getGameDate() {
        return gameDate;
    }

    public void setGameCanceled(boolean gameCanceled) {
        this.gameCanceled = gameCanceled;
    }

    public boolean isGameCanceled() {
        return gameCanceled;
    }

    public void setGoals(ArrayList<String> goals) {
        if(goals.size() != 0){
            this.goals = goals;
        }else{
            System.out.println("The score of the game was 0-0");
        }
    }

    public void getGoals() {
        for(String s: goals){
            System.out.println(s);
        }
    }

    @Override
    public String toString() {
        return "Chelsea vs  " + getOpposingTeam() + " The Result of the game was: " + getResult() + " and it was played on " + getGameDate() + " at " + getGameTime();
    }
}

