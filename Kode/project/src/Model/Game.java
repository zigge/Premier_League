package Model;

import java.time.LocalDate;

/**
 * Created by lassebjorklund on 21/11/15.
 */
public class Game {

    private Player player;
    private String opposingTeam;
    private int result;
    private LocalDate gameTime;
    private String playedGame;

//    TODO: Verifiy this constructer
    public Game(Player player, String opposingTeam, int result, LocalDate gameTime, String playedGame) {
        this.player = player;
        this.opposingTeam = opposingTeam;
        this.result = result;
        this.gameTime = gameTime;
        this.playedGame = playedGame;
    }
//    TODO: Verifiy this constructer
    public Game(String opposingTeam, LocalDate gameTime) {
        this.opposingTeam = opposingTeam;
        this.gameTime = gameTime;
    }
}
