package Controller;

import java.io.File;
import java.util.ArrayList;

import Model.Game;
import Model.Player;

/**
 * Created by lassebjorklund on 20/11/15.
 * TODO: Complete file
 */
public class Util {
    private static File playerFile = new File(System.getProperty("user.dir") + "/Players", "txt");
    private static File playerDIR = new File("Players");
    private boolean folderCreated;
    File savelocation;

        //TODO: Define if we determine the location of save games, or the user do!
    public void saveGame(Game game){
        //TODO Some code to save "Game"
    }

    public void savePlayer(ArrayList<Player> player){
        //TODO Some code to save "players"
    }

    public Game loadGame(File locationOfGame){
        //TODO Some code to load "Game"
        return null;
    }


    public ArrayList<Player> loadPlayers(File locationOfPlayers){
        //TODO Some code to load "players"
        //We wanna know where the players are saved, so that we can load them at the start of the program.
        if(!playerDIR.exists()){
            try {

                playerDIR.mkdir();
                folderCreated = true;
            } catch (SecurityException e) {
                System.err.println("Cannot create folder");
                folderCreated = false;
            }

        }else{

        }

        return null;
    }


    public enum fieldPosition{
        FORWARD("Forward position"),
        DEFENDER("Defending position"),
        MIDFIELD("Midfield position"),
        KEEPER("Keeper position");

        private String positionOnField;


        public String getPositionOnField() {
            return positionOnField;
        }

        fieldPosition(String positionOnField){
            this.positionOnField = positionOnField;
        }

    }

    public enum strategiesOfGame{
        FOURFOURTWO("4-4-2"),
        FOURTHREETHREE("4-3-3"),
        FOURFIVEONE("4-5-1"),
        THREEFIVETWO("3-5-2"),
        THREEFOURTHREE("3-4-3");

        private String strategies;

        public String getStrategies() {
            return strategies;
        }

        strategiesOfGame(String strategies){
            this.strategies = strategies;
        }
    }
}
