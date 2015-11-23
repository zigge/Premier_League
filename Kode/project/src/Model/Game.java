package Model;

import java.time.LocalDate;
import java.util.ArrayList;


/**
 * Created by lassebjorklund on 21/11/15.
 */
public class Game {

    private ArrayList<Player> players;
    private String opposingTeam;
    private int result;
    private LocalDate gameTime;

//    TODO: Verify this constructor
    public Game(ArrayList<Player> player, String opposingTeam, int result, LocalDate gameTime) {
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

    public int getResult() {
        return result;
    }

    public LocalDate getGameTime() {
        return gameTime;
    }
}
