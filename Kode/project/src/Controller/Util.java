package Controller;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;


import Model.Game;
import Model.Keeper;
import Model.Player;
import com.sun.org.apache.xpath.internal.operations.Bool;

//-------Lasse is responsible for this section-------

public class Util {
    private static File playerFile, file;
    private static File playerDir = new File("PlayerFile");
    private static File playedGameDir = new File("PlayedGames");
    private static File upcommingGamesDir = new File("UpcommingGames");
    private static ObjectInputStream objin = null;
    private static ArrayList<Player> loadList;
    private static Game returnGame;
    private static int playerIndex;

    public static void createPlayer(String name, int salary, int position, String nationality, int playerNumber) {
        Player player = new Player(name, salary, position, nationality, playerNumber);
        Boolean isPlayerThere = false;
        try {
            loadList = new ArrayList<>(loadPlayers());
            for (Player p : loadList) {
                if (player.getPlayerNumber() == p.getPlayerNumber() || player.getName().equalsIgnoreCase(p.getName())) { //Checks for dublicates of "player"
                    isPlayerThere = true;
                } else {

                }
            }
            if(isPlayerThere){
                System.out.println("player exists!");
            }else {
                loadList.add(player);
                savePlayers(loadList);
            }
        }catch (NullPointerException e ){ //Handles if null elements in "loadList" is found
            loadList = new ArrayList<>();
            loadList.add(player);
            savePlayers(loadList);

        }
    }

    public static void createKeeper(String name, int salary, int position, String nationality, int playerNumber, int saves) {
        Keeper keeper = new Keeper(name, salary, position, nationality, playerNumber, saves); //Check comments in createPlayer
        Boolean isPlayerThere = false;
        try {
            loadList = new ArrayList<>(loadPlayers());
            for (Player p : loadList) {
                if (keeper.getPlayerNumber() == p.getPlayerNumber() || keeper.getName().equalsIgnoreCase(p.getName())) {
                    isPlayerThere = true;
                } else {

                }
            }
            if(isPlayerThere){
                System.out.println("player exists!");
            }else {
                loadList.add(keeper);
                savePlayers(loadList);
            }
        }catch (NullPointerException e ){
            loadList = new ArrayList<>();
            loadList.add(keeper);
            savePlayers(loadList);

        }
    }

    public static void saveGame(Game game, String fileName) {
        ObjectOutputStream objout = null;
        if (game != null) {
            if (game.getResult() != null) {
                try {
                    //System.getProperty returns the path to the current directory where the program is executed from
                    objout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(System.getProperty("user.dir") + "/PlayedGames/" + fileName + " - playedGame.txt")));
                    objout.writeObject(game);
                } catch (IOException e) {
                    e.printStackTrace(); //Prints out error tree

                } finally {
                    try {
                        objout.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    //If result is null it is an upcomming game and therefore we use a different directory
                    objout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(System.getProperty("user.dir") + "/UpcommingGames/" + fileName + " - upcommingGame.txt")));
                    objout.writeObject(game);

                } catch (IOException e) {
                    e.printStackTrace();

                } finally {
                    try {
                        objout.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } else {
            System.out.println("Game is null, cannot create!");
        }
    }

    public static void savePlayers(ArrayList<Player> players) {
        ObjectOutputStream objout = null;
        try {
            objout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(playerFile))); //File to write to
            objout.writeObject(players); //Writes to file

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objout != null) {
                    objout.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Boolean viewPlayers() {
        Boolean playesIsThere = false; //Checks if there is playes in the playerFile, if true, returns true, else false.
        try {
            ArrayList<Player> tempArray = loadPlayers();
            if (tempArray.size() != 0) {
                for (Player p : tempArray) {
                    System.out.println(p);
                }
                playesIsThere = true;

            } else {
                System.out.println("You have no players on your current team");
            }
        } catch (NullPointerException e) { //If null is read.
            System.out.println("Player File is empty! ");
        }
        return playesIsThere;
    }

    public static Game loadGame(File locationOfGame) {
        ObjectInputStream objin = null;
        if (locationOfGame.exists()) { //Checks the location of the parameter, if true, runs, else do not.
            try {
                objin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(locationOfGame)));
                returnGame = (Game) objin.readObject(); //We have to type cast, to make sure it is the type of object.

            } catch (IOException e) {
                e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            } catch (NullPointerException e) {
                System.out.println("Playerfile is empty!");
            } finally {
                try {
                    objin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnGame;
    }

    public static ArrayList<Player> loadPlayers() {
        ObjectInputStream objin = null;
        ArrayList<Player> returnPlayers = null;
        try {
            if (playerFile.length() != 0) { //If the length of the playerFile is !0 says that there is players in the file.
                objin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(playerFile)));
                returnPlayers = (ArrayList<Player>) objin.readObject();
            } else {

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objin != null) {
                    objin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return returnPlayers;
    }

    public static void createPlayerFolder() {
        if (!playerDir.exists()) { //If folder does not exists, create it.
            try {
                playerDir.mkdir();
            } catch (SecurityException e) {
                System.err.println("Cannot create folder");
            }
            //Code for saving players to file.
        } else { // does nothing

        }
    }

    public static File createPlayerFile() {
        try {
            playerFile = new File(System.getProperty("user.dir") + "/Playerfile/playerFile" + ".txt"); //Create file in the folder specified
            playerFile.createNewFile();
            return playerFile;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return playerFile;
        }
    }

    public static void createPlayedGamesFolder() {
        if (!playedGameDir.exists()) {
            try {
                playedGameDir.mkdir();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    public static void createUpcommingGameFolder() {
        if (!upcommingGamesDir.exists()) {
            try {
                upcommingGamesDir.mkdir();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    public static String deletePlayer(int number, ArrayList<Player> players) {
        int index = -1; //0 indexed
        String returnStatement = "";
        for (Player p : players) {
            if (p.getPlayerNumber() == number) {
                index = players.indexOf(p);
                returnStatement = "Player: " + p + " has been deleted!";
                break;
            } else {
                returnStatement = "Could not find player";
            }
        }
        players.remove(index);
        savePlayers(players); //Calls method savePlayers to update playerFile

        return returnStatement;
    }

    public static Player getPlayer(int id) {
        Player player = null;
        ArrayList<Player> tempPlayerList = loadPlayers(); //loadPlayers returns new ArrayList
        for (Player p : tempPlayerList) {
            if (p.getPlayerNumber() == id) {
                player = p;
                playerIndex = tempPlayerList.indexOf(p); //Takes the index of the player "p" for the list, for later use
            }
        }
        return player;
    }

    public static void updatePlayer(Player player) {
        ArrayList<Player> tempArraylist = new ArrayList<>(loadPlayers());
        if (tempArraylist.size() != 0) {
            tempArraylist.set(playerIndex, player); //Updates the player from the parameters given.
            savePlayers(tempArraylist);
            tempArraylist.clear();
        } else {
            savePlayers(tempArraylist);
            tempArraylist.clear();

        }

    }

    public static void createGameUpcommingGame(String opposingTeam, LocalDate gameDate, LocalTime gameTime, String nameOfFile) {
        Game upcommingGame = new Game(opposingTeam, gameDate, gameTime);
        saveGame(upcommingGame, nameOfFile);
    }

    public static void createGamePlayed(ArrayList<Player> player, String opposingTeam, String result, LocalDate gameDate, LocalTime gameTime, String strategy, ArrayList<String> goals, String filename) {
        Game playedGame = new Game(player, opposingTeam, result, gameDate, gameTime, strategy, goals);
        saveGame(playedGame, filename);
    }

    public static ArrayList<String> listFilesUpcomming() {
        ArrayList<String> filterFilenames = new ArrayList<>();
        ArrayList<String> fileNameArray = new ArrayList<>(Arrays.asList(upcommingGamesDir.list())); // Gets the contents of the folder
        for (String f : fileNameArray) {
            if (f.endsWith(".txt")) { //returns list of elements ending ".txt".
                filterFilenames.add(f); // adds strings with the ending ".txt" to filter output to user.
            }
        }

        return filterFilenames;

    }

    public static ArrayList<String> listFilesPlayed() {
        ArrayList<String> filterFilenames = new ArrayList<>();
        ArrayList<String> fileNameArray = new ArrayList<>(Arrays.asList(playedGameDir.list())); // Gets the contents of the folder
        for (String f : fileNameArray) {
            if (f.endsWith(".txt")) {
                filterFilenames.add(f); // adds strings with the ending ".txt" to filter output to user.
            }
        }

        return filterFilenames;

    }

    public enum fieldPosition {
        KEEPER("Keeper position"),
        DEFENDER("Defending position"),
        MIDFIELDER("Midfield position"),
        FORWARD("Forward position");


        private String positionOnField;

        public String getPositionOnField() {
            return positionOnField;
        } //Returns the string rep of the enum.

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
