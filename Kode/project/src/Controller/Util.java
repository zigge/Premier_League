package Controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import Model.Game;
import Model.Player;

/**
 * Created by lassebjorklund on 20/11/15.
 * TODO: Complete file
 */
public class Util {
    private static File playerFile = null;
    private static File file = null;
    private static File playerDir = new File("PlayerFile");
    private static File playedGameDir = new File("PlayedGames");
    private static File upcommingGames = new File("UpcommingGames");
    private static ObjectOutputStream objout = null;
    private static ObjectInputStream objin = null;
    private static Game returnGame;
    private static int playerIndex;

    public static void createPlayer(String name, int salary, int position, String nationality, int playerNumber) {
        ArrayList<Player> tempPlayerList = new ArrayList<>(loadPlayers());
        Player player = new Player(name, salary, position, nationality, playerNumber);
        if (!tempPlayerList.contains(player)) {
            updatePlayer(player);
        } else {
            System.out.println("Player all ready exists in file!");
        }

    }

    public static void saveGame(Game game, String fileName) {
        if (game != null) {
            if (game.getResult() == null) {
                try {
                    objout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(System.getProperty("user.dir") + "/PlayedGames" + fileName + " - playedGame.txt")));
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
                try {
                    objout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(System.getProperty("user.dir") + "UpcommingGames" + fileName + " - upcommingGame.txt")));
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
            }

        } else {
            System.out.println("Game is null, cannot create!");
        }
    }

    public static void savePlayers(ArrayList<Player> player) {
        //We wanna know where the players are saved, so that we can load them at the start of the program.
        if (player.size() != 0) {
            try {
                objout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(playerFile))); //File to write to
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
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            } catch (NullPointerException e) {
                e.printStackTrace();

            } finally {
                try {
                    objin.close();
                } catch (IOException e) {

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
            return returnPlayer;
        }
    }

    public static void createPlayerFolder() {
        if (!playerDir.exists()) {
            try {
                playerDir.mkdir();
            } catch (SecurityException e) {
                System.err.println("Cannot create folder");
            }
            //Code for saving players to file.
        } else {

        }
    }

    public static File createPlayerFile() {
            playerFile = new File(System.getProperty("user.dir") + "/Playerfile/playerFile" + ".txt"); // OSX/UNIX file system specific location /.../Playerfile
            return playerFile;

    }

    public static void createPlayedGamesFolder() {

        try {
            playedGameDir.mkdir();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public static void createUpcommingGameFolder() {

        try {
            upcommingGames.mkdir();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    public static String deletePlayer(int number) {

        String returnStatement = "";
        ArrayList<Player> tempPlayerArrayList = new ArrayList<>(loadPlayers());
        for (Player p : tempPlayerArrayList) {
            if (p.getPlayerNumber() == number) {
                int index = tempPlayerArrayList.indexOf(p);
                tempPlayerArrayList.remove(index);
                for (Player d : tempPlayerArrayList) {
                    System.out.println(d);
                }
                savePlayers(tempPlayerArrayList);
                returnStatement = "Player: " + p + " deleted!";
            } else {
                returnStatement = "Could not find player";
            }
        }

        return returnStatement;
    }

    public static Player getPlayer(int id) {
        Player player = null;
        ArrayList<Player> tempPlayerList = new ArrayList<>(loadPlayers());
        for (Player p : tempPlayerList) {
            if (p.getPlayerNumber() == id) {
                player = p;
                playerIndex = tempPlayerList.indexOf(p);
            }
        }
        return player;
    }

    public static void updatePlayer(Player player) {
        ArrayList<Player> tempArraylist = new ArrayList<>(loadPlayers());
        tempArraylist.set(playerIndex, player);
        savePlayers(tempArraylist);
    }

    public static void createGameUpcommingGame(String opposingTeam, LocalDate gameTime, String nameOfFile) {
        Game upcommingGame = new Game(opposingTeam, gameTime);
        saveGame(upcommingGame, nameOfFile);
    }

    public static void createGamePlayed(ArrayList<Player> player, String opposingTeam, String result, LocalDate gameTime, String filename) {
        Game playedGame = new Game(player, opposingTeam, result, gameTime);
        saveGame(playedGame, filename);
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
