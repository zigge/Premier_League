package Controller;

import java.io.*;
import java.util.ArrayList;

import Model.Game;
import Model.Player;

/**
 * Created by lassebjorklund on 20/11/15.
 * TODO: Complete file
 */
public class Util {
    private static File playerFile;
    private static File playerDir = new File("Playerfile");
    private static ObjectOutputStream objout = null;
    private static ObjectInputStream objin = null;
    private boolean folderCreated;

        //TODO: Define if we determine the location of save games, or the user do!
    public static void saveGame(Game game, File saveLocation){
        //TODO Some code to save "Game"
        try {

            objout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(saveLocation)));
            objout.writeObject(game);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                objout.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void savePlayer(ArrayList<Player> player){
        //TODO Some code to save "players"
        //We wanna know where the players are saved, so that we can load them at the start of the program.

    }

    public static Game loadGame(File locationOfGame){
        //TODO Some code to load "Game"
        return null;
    }


    public static  ArrayList<Player> loadPlayers(File locationOfPlayers){
        //TODO Some code to load "players"
        if(playerDir.exists()){
            //Code for loading players from file

        }else{
            System.out.println("Folder: " + playerDir.getName() + "does not exists");
        }

        return null;
    }

    public static void checkPlayerFolder(){

        if(!playerDir.exists()){
            try {
               playerDir.mkdir();
               createPlayerFile();
            } catch (SecurityException e) {
                System.err.println("Cannot create folder");
            }
            //Code for saving players to file.
        }else{
            createPlayerFile();

        }
    }

    public static File createPlayerFile(){

        if(System.getProperty("os.name").contains("OS X")){
            playerFile = new File(System.getProperty("user.dir") + "/Playerfile" + ".txt"); // OSX/UNIX file system specific location /.../Playerfile
            return playerFile;
        } else{
            playerFile = new File(System.getProperty("user.dir") + "\\Playerfile\\playerlist" + ".txt"); //Windows file system specific location C:\...\Playerfile
            return playerFile;
        }
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
