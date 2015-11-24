package Model;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Created by lassebjorklund on 21/11/15.
 */
public class Game {

    private ArrayList<Player> players;
    private String opposingTeam;
    private String result;
    private LocalDate gameTime;

//    TODO: Verify this constructor
    public Game(ArrayList<Player> player, String opposingTeam, String result, LocalDate gameTime) {
//    TODO: Fill out constructor for game
        this.opposingTeam = opposingTeam;
        this.result = result;
        this.gameTime = gameTime;
        this.players = player;

    }
//    TODO: Verify this constructor
    public Game(String opposingTeam, LocalDate gameTime) {
        this.opposingTeam = opposingTeam;
        this.gameTime = gameTime;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public String getOpposingTeam() {
        return opposingTeam;
    }

    public String getResult() {
        return result;
    }

    public LocalDate getGameTime() {
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

    public void setGameTime(LocalDate gameTime) {
        this.gameTime = gameTime;
    }
}

