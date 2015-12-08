package View;

import Controller.Util;
import Model.Game;
import Model.Keeper;
import Model.Player;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MenuSystem {
    private static Scanner scan;
    private static int headMenu, playerMenu, gameMenu, gameSubMenu, editPlayerMenu, count, playerNumber, editSalary, editPosition, playerSalary, positionMenu, playerPosition;
    private static String editName, editNationality, opposingTeam, time, playerName, playerNationality, wasThereGolas, day, playerNumberString;
    private static ArrayList<Player> playerList;
    private static ArrayList<String> goals = new ArrayList<>();
    private static ArrayList<String> upcommingFileList, playedGameFileList;
    private static boolean running, addMorePlayers;
    private final static String esc = "\u001b[2J";
    private final static String home = "\u001b[H";

    //Menu run class
    //----------Casper is responsible for this section---------
    public static void menu() {
        Util.createPlayerFolder();
        Util.createPlayerFile();
        Util.createUpcommingGameFolder();
        Util.createPlayedGamesFolder();
        scan = new Scanner(System.in);
        running = true;

        while (running) {
            //Menu print out
            System.out.println("Welcome! \nPlease select one of the menu point below:");
            System.out.println("1: Players \n2: Games \n3: Quit ");
            headMenu = scan.nextInt();
            switch (headMenu) {
                case 1:
                    System.out.println("Please select an option: \n1: New Player \n2: View players \n3: Edit player \n4: Delete Player \n5: Quit ");
                    playerMenu = scan.nextInt();
                    switch (playerMenu) {
                        case 1: // Create Player
                            System.out.println("Enter the full name of the player you want to create:");
                            scan.nextLine();
                            playerName = scan.nextLine();
                            System.out.println("Enter the weekly salary (in £) for " + playerName + ":");
                            playerSalary = scan.nextInt();
                            System.out.println("Enter the player number for " + playerName + ":");
                            playerNumber = scan.nextInt();
                            System.out.println("Enter the nationality for " + playerName + ":");
                            scan.nextLine();
                            playerNationality = scan.nextLine();
                            System.out.println("Select the position for " + playerName + ".");
                            System.out.println("0: Keeper");
                            System.out.println("1: Defender");
                            System.out.println("2: Midfielder");
                            System.out.println("3: Forward");
                            playerPosition = scan.nextInt();
                            if (playerPosition <= 3 && playerPosition > 0) { //Checks if player is a field player
                                Util.createPlayer(playerName, playerSalary, playerPosition, playerNationality, playerNumber);
                            } else if (playerPosition == 0) { //Checks if player is a keeper
                                Util.createKeeper(playerName, playerSalary, playerPosition, playerNationality, playerNumber, 0);
                            } else {
                                System.out.println("An error has occured. You did not select a valid number for the position.");
                            }
                            continue;
                        case 2: //Show players
                            Util.viewPlayers();
                            try {
                                TimeUnit.SECONDS.sleep(2); //Thread is sleeping for two seconds
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            System.out.println(esc + home);
                            continue;
                        case 3: //Edit Player
                            Util.viewPlayers();

                            System.out.println("Which player do you want to edit?");
                            System.out.println("Player number:");

                            playerNumber = scan.nextInt();
                            System.out.println("What do you want to edit?");

                            if (Util.getPlayer(playerNumber).getPosition().equals("Keeper position")) {
                                Keeper keeper = (Keeper) Util.getPlayer(playerNumber); //Type casts so it is possible to edit saves for a keeper
                                System.out.println("Please select an option: \n1: Name \n2: Salary \n3: Position \n4: Nationality \n5: Game Options \n6: Goals \n7: Saves \n8: Quit");
                                editPlayerMenu = scan.nextInt();

                                switch (editPlayerMenu) {
                                    case 1:
                                        //Name
                                        System.out.println("What is the " + keeper.getName() + "'s new name?");
                                        editName = scan.next();
                                        keeper.setName(editName);
                                        break;
                                    case 2:
                                        //Salary
                                        System.out.println("What is the " + keeper.getName() + "'s new weekly salary?");
                                        try {
                                            editSalary = scan.nextInt();
                                            keeper.setSalary(editSalary);
                                        } catch (InputMismatchException e) {
                                            System.out.println("please write the Salary in numbers");
                                        }
                                        break;
                                    case 3:
                                        //Position
                                        System.out.println("The positions:");
                                        int count = 0;
                                        for (Util.fieldPosition f : Util.fieldPosition.values()) {
                                            count++;
                                            System.out.println(count + ": " + f.getPositionOnField());
                                        }
                                        System.out.println("What is the " + keeper.getName() + "'s new position?");
                                        editPosition = scan.nextInt();
                                        keeper.setPosition(editPosition - 1);
                                        break;
                                    case 4:
                                        //Nationality
                                        System.out.println("What is the " + keeper.getName() + "'s new nationality?");
                                        editNationality = scan.next();
                                        keeper.setNationality(editNationality);
                                        break;
                                    case 5:
                                        System.out.println("Please select a Game Option \n1: Games Won \n2: Games Lost \n3: Games Tied");
                                        editPlayerMenu = scan.nextInt();
                                        switch (editPlayerMenu) {
                                            //Game Options
                                            case 1:
                                                System.out.println("Number of games " + keeper.getName() + " has won: " + keeper.getGamesWon());
                                                System.out.println("How many wins would you like to add?");
                                                count = scan.nextInt();
                                                keeper.setGame(count);
                                                keeper.setGamesWon(count);
                                                break;
                                            case 2:
                                                System.out.println("Number of games " + keeper.getName() + " has lost: " + keeper.getGamesLoss());
                                                System.out.println("How many losses would you like to add?");
                                                count = scan.nextInt();
                                                keeper.setGame(count);
                                                keeper.setGamesLoss(count);
                                                break;
                                            case 3:
                                                System.out.println("Number of games " + keeper.getName() + " has tied: " + keeper.getTies());
                                                System.out.println("How many ties would you like to add?");
                                                count = scan.nextInt();
                                                keeper.setGame(count);
                                                keeper.setTies(count);
                                                break;
                                            default:
                                                System.out.println("Please enter a valid number");
                                                break;
                                        }
                                        Util.updatePlayer(keeper); //Can update keeper because of inheritance between keeper and player
                                        continue;
                                    case 6:
                                        //Goals
                                        System.out.println("Number of goals scored by " + keeper.getName() + ": " + keeper.getGoal());
                                        System.out.println("How many goals would you like to add?");
                                        count = scan.nextInt();
                                        keeper.setGoal(count);
                                        break;
                                    case 7:
                                        //Saves
                                        System.out.println("Number of saves made by " + keeper.getName() + ": " + keeper.getSaves());
                                        System.out.println("How many saves would you like to add?");
                                        count = scan.nextInt();
                                        keeper.setSaves(count);
                                        break;
                                    case 8:
                                        //Quit
                                        continue;
                                    default:
                                        System.out.println("Please enter a valid number");
                                        break;
                                }
                                Util.updatePlayer(keeper); //Updates keeper attributes

                            //------Henriette is responsible for this section--------
                            } else {
                                Player player = Util.getPlayer(playerNumber);
                                System.out.println("Please select an option: \n1: Name \n2: Salary \n3: Position \n4: Nationality \n5: Game Options \n6: Goals \n7: Quit");
                                editPlayerMenu = scan.nextInt();
                                switch (editPlayerMenu) {
                                    case 1:
                                        //Name
                                        System.out.println("What is " + player.getName() + "'s new name?");
                                        editName = scan.next();
                                        player.setName(editName);
                                        break;
                                    case 2:
                                        //Salary
                                        System.out.println("What is " + player.getName() + "'s new weekly salary?");
                                        editSalary = scan.nextInt();
                                        player.setSalary(editSalary);
                                        break;
                                    case 3:
                                        //Position
                                        System.out.println("What is " + player.getName() + "'s new position?");
                                        int count = 0;
                                        for (Util.fieldPosition f : Util.fieldPosition.values()) {
                                            count++;
                                            System.out.println(count + ": " + f.getPositionOnField());
                                        }
                                        editPosition = scan.nextInt();
                                        player.setPosition(editPosition - 1);
                                        break;
                                    case 4:
                                        //Nationality
                                        System.out.println("What is " + player.getName() + "'s new nationality?");
                                        editNationality = scan.next();
                                        player.setNationality(editNationality);
                                        break;
                                    case 5:
                                        System.out.println("Please select a game option \n1: Games Won \n2: Games Lost \n3: Games Tied");
                                        editPlayerMenu = scan.nextInt();
                                        switch (editPlayerMenu) {
                                            //Game Options
                                            case 1:
                                                System.out.println("Number of games " + player.getName() + " has won: " + player.getGamesWon());
                                                System.out.println("How many wins would you like to add?");
                                                count = scan.nextInt();
                                                player.setGame(count);
                                                player.setGamesWon(count);
                                                break;
                                            case 2:
                                                System.out.println("Number of games " + player.getName() + " has lost: " + player.getGamesLoss());
                                                System.out.println("How many losses would you like to add?");
                                                count = scan.nextInt();
                                                player.setGame(count);
                                                player.setGamesLoss(count);
                                                break;
                                            case 3:
                                                System.out.println("Number of games " + player.getName() + " has tied: " + player.getTies());
                                                System.out.println("How many ties would you like to add?");
                                                count = scan.nextInt();
                                                player.setGame(count);
                                                player.setTies(count);
                                                break;
                                            default:
                                                System.out.println("Please enter a valid number");
                                                break;
                                        }
                                        Util.updatePlayer(player);
                                        continue;
                                    case 6:
                                        //Goals
                                        System.out.println("Number of goals scored by " + player.getName() + ": " + player.getGoal());
                                        System.out.println("How many goals would you like to add?");
                                        count = scan.nextInt();
                                        player.setGoal(count);
                                        break;
                                    case 7:
                                        //Quit
                                        continue;
                                    default:
                                        System.out.println("Please enter a valid number");
                                        break;

                                }
                                Util.updatePlayer(player);

                            }
                            continue;
                        case 4: //Delete player
                            ArrayList<Player> players = Util.loadPlayers();
                            for (Player p : players) {
                                System.out.println(p);
                            }
                            System.out.println("Who do you want to remove?");
                            System.out.println("Player number:");
                            try {
                                playerNumber = scan.nextInt();
                            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                                scan.nextInt();
                            }
                            String conf = Util.deletePlayer(playerNumber, players);
                            System.out.println(conf);
                            continue;
                        case 5:
                            continue;
                        default:
                            System.out.println("Please enter a valid number");
                            continue;
                    }

                case 2:
                    System.out.println("Please select an option: \n1: Manage Games \n2: View Game from file \n3: Quit");
                    gameMenu = scan.nextInt();
                    switch (gameMenu) {
                        case 1:
                            System.out.println("Please select an option: \n1: Upcoming game \n2: Create Played game \n3: Quit");
                            gameSubMenu = scan.nextInt();
                            switch (gameSubMenu) {
                                case 1:
                                    System.out.println("Please enter the opposing team for the upcomming game: ");
                                    scan.nextLine();
                                    opposingTeam = scan.nextLine();
                                    System.out.println("Please enter time for the game: HH:MM:SS ");
                                    time = scan.next();
                                    LocalTime timeOfGame = LocalTime.parse(time);
                                    System.out.println("Please enter date of game: YYYY-MM-DD");
                                    day = scan.next();
                                    LocalDate gameDate = LocalDate.parse(day);
                                    Util.createGameUpcommingGame(opposingTeam, gameDate, timeOfGame, opposingTeam);
                                    continue;

                                case 2:
                                    int opposingTeamGoals = 0;
                                    int homeGoal = 0;
                                    String resultOfGame = "";
                                    Player player = null;
                                    String typeOfStrategy = "";
                                    LocalDate dateOfGame = null;
                                    LocalTime gameTime = null;
                                    ArrayList<Integer> playerNumberIntList = new ArrayList<>();
                                    System.out.println("Please enter the opposing team for this game: ");
                                    scan.nextLine();
                                    try {
                                        opposingTeam = scan.nextLine();
                                    } catch (InputMismatchException e) {
                                        scan.nextLine();
                                    }
                                    System.out.println("Please enter the date for the game: YYYY-MM-DD ");
                                    try {
                                        day = scan.nextLine();
                                        dateOfGame = LocalDate.parse(day); //Converts a string to LocalDate
                                        System.out.println("Please enter the time for the game: HH:MM:SS");
                                        time = scan.nextLine();
                                        gameTime = LocalTime.parse(time); //Converts a string to LocalTime
                                    } catch (DateTimeParseException e) {
                                        scan.nextLine();
                                    }

                                    System.out.println("What strategy are used ? ");
                                    int countOfStrategies = 0;
                                    for (Util.strategiesOfGame s : Util.strategiesOfGame.values()) {
                                        countOfStrategies++;
                                        System.out.println(countOfStrategies + ": " + s.getStrategies());
                                    }
                                    try {
                                        int choiseOfStrategy = scan.nextInt();
                                        if (choiseOfStrategy > Util.strategiesOfGame.values().length || choiseOfStrategy <= 0) {
                                            do {
                                                System.out.println("invalid choice!");
                                            }
                                            while (choiseOfStrategy > Util.strategiesOfGame.values().length || choiseOfStrategy <= 0);
                                        }
                                        typeOfStrategy = Util.strategiesOfGame.values()[choiseOfStrategy - 1].getStrategies(); //0 indexed
                                        System.out.println(typeOfStrategy);

                                    } catch (InputMismatchException e) {

                                    }
                                    System.out.println("What players were in the game ? ");
                                    if (Util.viewPlayers()) {
                                        playerNumberString = scan.next();
                                        ArrayList<String> playerNumberList = new ArrayList<>(Arrays.asList(playerNumberString.split(",")));
                                        playerList = new ArrayList<>();
                                        for (String s : playerNumberList) {
                                            playerNumberIntList.add(Integer.parseInt(s)); //Converts string to list of ints
                                        }
                                        for (Integer p : playerNumberIntList) {
                                            playerList.add(Util.getPlayer(p));

                                        }
                                    } else {
                                        System.out.println("no players in file!");
                                    }

                                    //--------Lucas is responsible for-----------

                                    System.out.println("How many goals for the opposing team? ");
                                    opposingTeamGoals = scan.nextInt();
                                    System.out.println("How many goals for home team?");
                                    homeGoal = scan.nextInt();
                                    if (homeGoal != 0) {
                                        if (homeGoal > opposingTeamGoals) { //won
                                            for (int i =0; i < playerList.size(); i++) {
                                                 if(Util.getPlayer(playerList.get(i).getPlayerNumber()).getPosition().equalsIgnoreCase("Keeper position")){ //get players position and matches against string "keeper position"
                                                     Keeper keeperToUpdate = (Keeper) Util.getPlayer(playerList.get(i).getPlayerNumber());
                                                     keeperToUpdate.setGame(1);
                                                     keeperToUpdate.setGamesWon(1);
                                                     Util.updatePlayer(keeperToUpdate);
                                                }else{
                                                     Player playerToUpdate = Util.getPlayer(playerList.get(i).getPlayerNumber());
                                                     playerToUpdate.setGame(1);
                                                     playerToUpdate.setGamesWon(1);
                                                     Util.updatePlayer(playerToUpdate);
                                                 }
                                            }

                                        } else if (homeGoal == opposingTeamGoals) { //tied
                                            for (int i =0; i < playerList.size(); i++) {
                                                if(Util.getPlayer(playerList.get(i).getPlayerNumber()).getPosition().equalsIgnoreCase("Keeper position")){
                                                    Keeper keeperToUpdate = (Keeper) Util.getPlayer(playerList.get(i).getPlayerNumber());
                                                    keeperToUpdate.setGame(1);
                                                    keeperToUpdate.setTies(1);
                                                    Util.updatePlayer(keeperToUpdate);
                                                }else{
                                                    Player playerToUpdate = Util.getPlayer(playerList.get(i).getPlayerNumber());
                                                    playerToUpdate.setGame(1);
                                                    playerToUpdate.setTies(1);
                                                    Util.updatePlayer(playerToUpdate);
                                                }
                                            }
                                        } else {
                                            for (int i =0; i < playerList.size(); i++) { //lost
                                                if(Util.getPlayer(playerList.get(i).getPlayerNumber()).getPosition().equalsIgnoreCase("Keeper position")){
                                                    Keeper keeperToUpdate = (Keeper) Util.getPlayer(playerList.get(i).getPlayerNumber());
                                                    keeperToUpdate.setGame(1);
                                                    keeperToUpdate.setGamesLoss(1);
                                                    Util.updatePlayer(keeperToUpdate);
                                                }else{
                                                    Player playerToUpdate = Util.getPlayer(playerList.get(i).getPlayerNumber());
                                                    playerToUpdate.setGame(1);
                                                    playerToUpdate.setGamesLoss(1);
                                                    Util.updatePlayer(playerToUpdate);
                                                }
                                            }
                                        }
                                        while (!addMorePlayers) { //Continue until false
                                            System.out.println("Add player who scored: ");
                                            int playerToAddGoals = scan.nextInt();
                                            for (Player p : playerList) {
                                                if (p.getPlayerNumber() == playerToAddGoals) {
                                                    player = p;
                                                }
                                            }
                                            System.out.println("When did he score? ");
                                            try {
                                                int goalsToAdd = scan.nextInt();
                                                goals.add("Player: " + player.getName() + " scored at: " + goalsToAdd);

                                            } catch (InputMismatchException e) {
                                                scan.nextInt();
                                            }
                                            System.out.println("Add more players? yes/no");

                                            String choise = scan.next();
                                            if (choise.contains("yes")) {
                                                addMorePlayers = false;
                                            } else {
                                                addMorePlayers = true;
                                            }

                                            resultOfGame = homeGoal + " - " + opposingTeamGoals;
                                        }

                                    } else {
                                        resultOfGame = homeGoal + "-" + opposingTeamGoals;
                                        goals.add("No goals for home team");
                                    }
                                    Util.createGamePlayed(playerList, opposingTeam, resultOfGame, dateOfGame, gameTime, typeOfStrategy, goals, opposingTeam);

                                case 4:
                                    continue;
                            }
                        case 2:
                            System.out.println("What type of game do you want to view? \n1: Upcomming Game \n2: Played Game");
                            int choice = scan.nextInt();
                            switch (choice) {
                                case 1:
                                    upcommingFileList = new ArrayList<>(Util.listFilesUpcomming()); //Listes the files in the folder
                                    for (String s : upcommingFileList) {
                                        System.out.println(s);
                                    }
                                    System.out.println("Please select a file from the list");
                                    scan.nextLine();
                                    String fileToDisplayUpcomming = scan.nextLine();
                                    try {
                                        File file = new File(System.getProperty("user.dir") + "/UpcommingGames/" + fileToDisplayUpcomming + ".txt");
                                        if (file.isFile()) {
                                            Game game = Util.loadGame(file);
                                            System.out.println("Chelsea FC vs " + game.getOpposingTeam() + " and the game is played " + game.getGameDate() + " at " + game.getGameTime());

                                        }

                                    } catch (Exception e) {

                                    }

                                    continue;

                                case 2:
                                    playedGameFileList = new ArrayList<>(Util.listFilesPlayed());
                                    for (String s : playedGameFileList) {
                                        System.out.println(s);
                                    }
                                    System.out.println("Please select a file from the list");
                                    scan.nextLine();
                                    String fileToDisplayPlayed = scan.nextLine();
                                    try {
                                        File file = new File(System.getProperty("user.dir") + "/PlayedGames/" + fileToDisplayPlayed + ".txt");
                                        if (file.isFile()) {
                                            Game game = Util.loadGame(file);
                                            System.out.println(game);
                                            game.getPlayers();
                                            game.getGoals();
                                        }


                                    } catch (Exception e) {

                                    }
                                    continue;

                                default:
                                    System.out.println("Please select a number from the menu!");
                                    continue;
                            }

                        case 3:
                            continue;
                        default:
                            System.out.println("Please enter a valid number");
                            continue;
                    }
                case 3:
                    continue;

                default:
                    System.out.println("Please enter a valid number");
                    continue;
            }
        }
    }
}
