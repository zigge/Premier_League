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
    private static File playerFile, file;
    private static File playerDir = new File("PlayerFile");
    private static ObjectOutputStream objout = null;
    private static ObjectInputStream objin = null;
    private static Game returnGame;

    public static void saveGame(Game game, File saveLocation) {
        if (game != null) {
            try {
                objout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(saveLocation)));
                objout.writeObject(game);

            } catch (IOException e) {
                e.getCause();
                e.getMessage();
                e.printStackTrace();

            } finally {
                try {
                    objout.close();
                } catch (IOException e) {
                    e.getCause();
                    e.getMessage();
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Game is null " + game + "we can not save a null game to: " + saveLocation);
        }
    }

    public static void savePlayer(ArrayList<Player> player) {
        //We wanna know where the players are saved, so that we can load them at the start of the program.
        file = createPlayerFile();
        if (player.size() != 0) {
            try {
                objout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file))); //File to write to
                objout.writeObject(player); //Writes to file
            } catch (IOException e) {
                e.getCause();
                e.getMessage();
                e.printStackTrace();

            } finally {
                try {
                    objout.close();
                } catch (IOException e) {
                    e.getCause();
                    e.getMessage();
                    e.printStackTrace();
                }
            }
        } else {
            System.out.printf("No players in list");
        }

    }

    public static Game loadGame(File locationOfGame) {
        //TODO Some code to load "Game"
        if (locationOfGame.exists()) {
            try {

                objin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(locationOfGame)));
                returnGame = (Game) objin.readObject();

            } catch (IOException e) {
                e.getCause();
                e.getMessage();
                e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.getCause();
                e.getMessage();
                e.printStackTrace();

            } finally {
                try {
                    objin.close();
                } catch (IOException e) {
                    e.getCause();
                    e.getMessage();
                    e.printStackTrace();
                }
            }
        }
        return returnGame;
    }

    public static ArrayList<Player> loadPlayers() {
        //TODO Some code to load "players"
        ArrayList<Player> returnPlayer = null;
        if (playerDir.exists()) {
            try {
                objin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(playerFile)));
                returnPlayer = (ArrayList<Player>) objin.readObject();

            } catch (FileNotFoundException e) {
                e.getCause();
                e.getMessage();
                e.printStackTrace();

            } catch (IOException e) {
                e.getCause();
                e.getMessage();
                e.printStackTrace();

            }catch (ClassNotFoundException e){
                e.getCause();
                e.getMessage();
                e.printStackTrace();

            } finally {
                try {
                    objin.close();
                } catch (IOException e) {
                    e.getCause();
                    e.getMessage();
                    e.printStackTrace();
                }
            }
            return returnPlayer;

        } else {
            createPlayerFolder();
            try {
                objin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(playerFile)));
                returnPlayer = (ArrayList<Player>) objin.readObject();

            } catch (FileNotFoundException e) {
                e.getCause();
                e.getMessage();
                e.printStackTrace();

            } catch (IOException e) {
                e.getCause();
                e.getMessage();
                e.printStackTrace();

            }catch (ClassNotFoundException e) {
                e.getCause();
                e.getMessage();
                e.printStackTrace();

            } finally {
                try {
                    objin.close();
                } catch (IOException e) {
                    e.getCause();
                    e.getMessage();
                    e.printStackTrace();
                }
            }
            return returnPlayer;
        }
    }

    public static void createPlayerFolder() {

        try {
            playerDir.mkdir();
        } catch (SecurityException e) {
            System.err.println("Cannot create folder");
        }
        //Code for saving players to file.
    }

    public static File createPlayerFile() {
        if (System.getProperty("os.name").contains("OS X")) {
            playerFile = new File(System.getProperty("user.dir") + "/Playerfile" + ".txt"); // OSX/UNIX file system specific location /.../Playerfile
            return playerFile;
        } else {
            playerFile = new File(System.getProperty("user.dir") + "\\Playerfile\\playerlist" + ".txt"); //Windows file system specific location C:\...\Playerfile
            return playerFile;
        }
    }

    public enum fieldPosition {
        FORWARD("Forward position"),
        DEFENDER("Defending position"),
        MIDFIELD("Midfield position"),
        KEEPER("Keeper position");

        private String positionOnField;


        public String getPositionOnField() {
            return positionOnField;
        }

        fieldPosition(String positionOnField) {
            this.positionOnField = positionOnField;
        }

    }

    public enum strategiesOfGame {
        FOURFOURTWO("4-4-2"),
        FOURTHREETHREE("4-3-3"),
        FOURFIVEONE("4-5-1"),
        THREEFIVETWO("3-5-2"),
        THREEFOURTHREE("3-4-3");

        private String strategies;

        public String getStrategies() {
            return strategies;
        }

        strategiesOfGame(String strategies) {
            this.strategies = strategies;
        }
    }
}
