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

/**
 * Created by lassebjorklund on 21/11/15.
 */
public class MenuSystem {
    private static Scanner scan;
    private static int headMenu, playerMenu, gameMenu, gameSubMenu, editPlayerMenu, playerToDelete, count, playerNumber, editSalary, editPosition, playerSalary, positionMenu, playerPosition;
    private static String editName, editNationality, opposingTeam, time, playerName, playerNationality, wasThereGolas, day, playerNumberString;
    private static ArrayList<Player> playerList;
    private static ArrayList<String> goals = new ArrayList<>();
    private static ArrayList<String> upcommingFileList, playedGameFileList;
    private static boolean running, addMorePlayers;
    private final static String esc = "\u001b[2J";
    private final static String home = "\u001b[H";


    //Menu run class
    public static void menu() {
       // try { //One solution, to handle all of the exceptions we get in the program. NOT THE BEST !
            Util.createPlayerFolder();
            Util.createPlayerFile();
            Util.createUpcommingGameFolder();
            Util.createPlayedGamesFolder();
           /* Util.createKeeper("Test", 2, 0, "Denmark", 1, 1); //TEST PLAYERS
            Util.createPlayer("Test2", 2, 1, "Denmark", 2); //TEST PLAYERS
            Util.updatePlayerList();*/
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
                                //TODO Some code for "new player" - Casper
                                //TODO Open issue: If two playes have the same name, what then?
                                // Casper, you need to make this check: if (pos > Util.fieldPosition.values().length || pos < 0), if the number is < 4 run else statement. This i when you set the position
                                // Casper, use the methode from Util.createPlayer, to create player. Your task is to make checks and switches for entering data intro the method

                                System.out.println("Enter the first name of the player you want to create:");
                                scan.nextLine();
                                playerName = scan.nextLine();
                                System.out.println("Enter the salary for " + playerName + ":");
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
                                if (playerPosition <= 3 && playerPosition > 0) {
                                    Util.createPlayer(playerName, playerSalary, playerPosition, playerNationality, playerNumber);
                                } else if (playerPosition == 0) {
                                    Util.createKeeper(playerName, playerSalary, playerPosition, playerNationality, playerNumber, 0);
                                } else {
                                    System.out.println("An error has occured. You did not select a valid number for the position.");
                                }
                                continue;
                            case 2: //Show players
                                Util.viewPlayers();
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                System.out.println(esc + home);
                                continue;
                            case 3: //Edit Player
                                //TODO Some code for "Edit player" - Henriette
                                Util.viewPlayers();

                                System.out.println("Which player do you want to edit?");
                                System.out.println("Player number:");

                                playerNumber = scan.nextInt();
                                //TODO Make match on player, and typecast object to "keeper" like: keeper = (Keeper) Util.getPlayer(playerNumber);
                                System.out.println("What do you want to edit?");

                                if (Util.getPlayer(playerNumber).getPosition().equals("Keeper position")) {
                                    Keeper keeper = (Keeper) Util.getPlayer(playerNumber);
                                    System.out.println("Please select an option: \n1: Name \n2: Salary \n3: Position \n4: Nationality \n5: Game Options \n6: Goals \n7: Saves \n8: Quit");
                                    editPlayerMenu = scan.nextInt();

                                    switch (editPlayerMenu) {
                                        case 1:
                                            //Name
                                            System.out.println("What is the keeer's new name?");
                                            editName = scan.next();
                                            keeper.setName(editName);
                                            break;
                                        case 2:
                                            //Salary
                                            System.out.println("What is the keeper's new salary?");
                                            try {
                                                editSalary = scan.nextInt();
                                                keeper.setSalary(editSalary);
                                            }catch(InputMismatchException e){
                                                System.out.println("pls write the Salary in numbers");
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
                                            System.out.println("What is the player's new position?");
                                            editPosition = scan.nextInt();
                                            keeper.setPosition(editPosition);
                                            break;
                                        case 4:
                                            //Nationality
                                            System.out.println("What is the keeper's new nationality?");
                                            editNationality = scan.next();
                                            keeper.setNationality(editNationality);
                                            break;
                                        case 5:
                                            System.out.println("Please select a game option \n1: Total Games \n2: Games Won \n3: Games Lost \n4: Games Tied");
                                            editPlayerMenu = scan.nextInt();
                                            switch (editPlayerMenu) {
                                                //Game Options
                                                case 1:
                                                    System.out.println("Number of games the keeper has played: " + keeper.getGame());
                                                    System.out.println("How many games would you like to add?");
                                                    count = scan.nextInt();
                                                    keeper.setGame(count);
                                                    break;
                                                case 2:
                                                    System.out.println("Number of games the keeper has Won: " + keeper.getGamesWon());
                                                    System.out.println("How many wins would you like to add?");
                                                    count = scan.nextInt();
                                                    keeper.setGamesWon(count);
                                                    break;
                                                case 3:
                                                    System.out.println("Number of games the keeper has Lost: " + keeper.getGamesLoss());
                                                    System.out.println("How many losses would you like to add?");
                                                    count = scan.nextInt();
                                                    keeper.setGamesLoss(count);
                                                    break;
                                                case 4:
                                                    System.out.println("Number of games the keeper has Tied: " + keeper.getTies());
                                                    System.out.println("How many ties would you like to add?");
                                                    count = scan.nextInt();
                                                    keeper.setTies(count);
                                                    break;
                                                default:
                                                    System.out.println("Please enter a valid number");
                                                    break;
                                            }
                                            Util.updatePlayer(keeper);
                                            continue;
                                        case 6:
                                            //Goals
                                            System.out.println("Number of goals made by the keeper: " + keeper.getGoal());
                                            System.out.println("How many goals would you like to add?");
                                            count = scan.nextInt();
                                            keeper.setGoal(count);
                                            break;
                                        case 7:
                                            //Saves
                                            System.out.println("Number of saves made by the keeper: " + keeper.getSaves());
                                            System.out.println("How many goals would you like to add?");
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
                                    Util.updatePlayer(keeper); //Updates player att's

                                } else {
                                    Player player = Util.getPlayer(playerNumber);
                                    System.out.println("Please select an option: \n1: Name \n2: Salary \n3: Position \n4: Nationality \n5: Game \n6: Goals \n7: Quit");
                                    editPlayerMenu = scan.nextInt();
                                    switch (editPlayerMenu) {
                                        case 1:
                                            //                                  Name
                                            System.out.println("What is the player's new name?");
                                            editName = scan.next();
                                            player.setName(editName);
                                            break;
                                        case 2:
                                            //Salary
                                            System.out.println("What is the player's new salary?");
                                            editSalary = scan.nextInt();
                                            player.setSalary(editSalary);
                                            break;
                                        case 3:
                                            //Position
                                            //TODO Print all the players
                                            System.out.println("What is the player's new position?");
                                            editPosition = scan.nextInt();
                                            player.setPosition(editPosition);
                                            break;
                                        case 4:
                                            //Nationality
                                            System.out.println("What is the player's new nationality?");
                                            editNationality = scan.next();
                                            player.setNationality(editNationality);
                                            break;
                                        case 5:
                                            System.out.println("Please select a game option \n1: Total Games \n2: Games Won \n3: Games Lost \n4: Games Tied");
                                            editPlayerMenu = scan.nextInt();
                                            switch (editPlayerMenu) {
                                                //Game Options
                                                case 1:
                                                    System.out.println("Number of games the player has played: " + player.getGame());
                                                    System.out.println("How many games would you like to add?");
                                                    count = scan.nextInt();
                                                    player.setGame(count);
                                                    break;
                                                case 2:
                                                    System.out.println("Number of games the player has Won: " + player.getGamesWon());
                                                    System.out.println("How many wins would you like to add?");
                                                    count = scan.nextInt();
                                                    player.setGamesWon(count);
                                                    break;
                                                case 3:
                                                    System.out.println("Number of games the player has Lost: " + player.getGamesLoss());
                                                    System.out.println("How many losses would you like to add?");
                                                    count = scan.nextInt();
                                                    player.setGamesLoss(count);
                                                    break;
                                                case 4:
                                                    System.out.println("Number of games the player has Tied: " + player.getTies());
                                                    System.out.println("How many ties would you like to add?");
                                                    count = scan.nextInt();
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
                                            System.out.println("Number of goals made by the keeper: " + player.getGoal());
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
                                //TODO Delete player - Lasse
                                ArrayList<Player> players = new ArrayList<>(Util.loadPlayers());
                                for (Player p : players) {
                                    System.out.println(p);
                                }
                                System.out.println("Who do you want to remove?");
                                System.out.println("Player number:");
                                try {
                                    playerNumber = scan.nextInt();
                                } catch (ArrayIndexOutOfBoundsException | InputMismatchException e){
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
                                System.out.println("Please select an option: \n1: Upcoming game \n2: Create Played game \n3: Cancel Upcoming Game \n4: Quit");
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
                                        LocalDate dateOfGame = null;
                                        LocalTime gameTime = null;
                                        System.out.println("Please enter the opposing team for this game: ");
                                        scan.nextLine();
                                        try {
                                            opposingTeam = scan.nextLine();
                                        } catch (InputMismatchException e) {
                                            scan.nextLine();
                                        }
                                        System.out.println("Please enter the date for the game: YYYY-MM-DD ");
                                        scan.nextLine();
                                        try {
                                            day = scan.nextLine();
                                            dateOfGame = LocalDate.parse(day);
                                            System.out.println("Please enter the time for the game: HH:MM:SS");
                                            scan.nextLine();
                                            time = scan.nextLine();
                                            gameTime = LocalTime.parse(time);
                                        } catch (DateTimeParseException e) {
                                            scan.nextLine();
                                        }
                                        System.out.println("What players was in the game ? ");
                                        if (Util.viewPlayers()) {
                                            scan.nextLine();
                                            playerNumberString = scan.next(); // There is probably a better way to do this...
                                            ArrayList<Integer> playerNumberIntList = new ArrayList<>();
                                            ArrayList<String> playerNumberList = new ArrayList<>(Arrays.asList(playerNumberString.split(",")));
                                            playerList = new ArrayList<>();
                                            for (String s : playerNumberList) {
                                                playerNumberIntList.add(Integer.parseInt(s));
                                            }
                                            for (Integer p : playerNumberIntList) {
                                                playerList.add(Util.getPlayer(p));
                                            }
                                        }

                                        System.out.println("How many goals for the opposing team? ");
                                        try {
                                            opposingTeamGoals = scan.nextInt();
                                            System.out.println("how many goals for home team");
                                            homeGoal = scan.nextInt();
                                        } catch (InputMismatchException e) {
                                            scan.nextInt();
                                        }
                                        if (homeGoal != 0) {

                                            while (addMorePlayers) {
                                                System.out.println("Add player who scored: ");
                                                int playerToAddGoals = scan.nextInt();

                                                for (Player p : playerList) {
                                                    if (p.getPlayerNumber() == playerToAddGoals) {
                                                        player = p;
                                                    }

                                                }
                                                System.out.println("When did he score ? ");
                                                try {
                                                    int goalsToAdd = scan.nextInt();
                                                    goals.add("Player: " + player.getName() + "Scored at: " + goalsToAdd);
                                                } catch (InputMismatchException e) {
                                                    scan.nextInt();
                                                }
                                                System.out.println("Add more players? yes/no");

                                                String choise = scan.next();
                                                if (choise.contains("yes")) {
                                                    addMorePlayers = true;
                                                } else {
                                                    addMorePlayers = false;
                                                }

                                                resultOfGame = homeGoal + " - " + opposingTeamGoals;

                                            }
                                        } else {
                                            resultOfGame = homeGoal + "-" + opposingTeamGoals;
                                            goals.add("No goals for home team");
                                        }

                                        Game game = new Game(playerList, opposingTeam, resultOfGame, dateOfGame, gameTime, goals);

                                    case 3:
                                        //TODO Edit game code. This is only for the variable gameCanceled in game, no deletion or edit of other att in a game.
                                    case 4:
                                        continue;
                                }
                            case 2:
                                //TODO Some code for viewing game from file
                                System.out.println("What type of game do you want to view ? \n1: Upcomming Game \n2: Played Game");
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
                                                System.out.println("Chelsea FC vs " + game.getOpposingTeam() + " and the game is do " + game.getGameDate() + " at " + game.getGameTime() );

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
       /* }catch (Exception e){
            System.out.println("input is wrong!");
            menu();
        }*/
    }

}
