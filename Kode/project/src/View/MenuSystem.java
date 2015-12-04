package View;

import Controller.Util;
import Model.Game;
import Model.Player;

import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
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
                                Util.updatePlayerList();
                                break;
                            case 2: //Show players
                                Util.viewPlayers();
                                try {
                                    TimeUnit.SECONDS.sleep(2);
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                System.out.println(esc + home);
                                break;
                            case 3: //Edit Player
                                //TODO Some code for "Edit player" - Henriette
                                Util.viewPlayers();

                                System.out.println("Which player do you want to edit?");
                                System.out.println("Player number:");

                                playerNumber = scan.nextInt();
                                Player player = Util.getPlayer(playerNumber);

                                System.out.println("What do you want to edit?");

                                if (player.getPosition().equals("Keeper position")) {
                                    System.out.println("Please select an option: \n1: Name \n2: Salary \n3: Position \n4: Nationality \n5: Game \n6: Goals \n7: Saves \n8: Quit");
                                    editPlayerMenu = scan.nextInt();

                                    switch (editPlayerMenu) {
                                        case 1:
                                            //Name
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
                                            System.out.println("The positions:");
                                            int count = 0;
                                            for (Util.fieldPosition f : Util.fieldPosition.values()) {
                                                count++;
                                                System.out.println(count + ": " + f.getPositionOnField());
                                            }
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
                                            //Game
                                            System.out.println("Number of games the player has played: " + player.getGame());
                                            int i;
                                            int addGame;
                                            addGame = scan.nextInt();
                                            for (i = player.getGame(); i >= 0; i += addGame) {
                                                System.out.println(i);
                                            }
                                            break;
                                        case 6:
                                            //Goals
                                            System.out.println("");
                                            break;
                                        default:
                                            System.out.println("Please enter a valid number");
                                            break;
                                    }
                                    Util.updatePlayer(player); //Updates player att's

                                } else {
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
                                            //Game
                                            System.out.println("");
                                            break;
                                        case 6:
                                            //Goals
                                            System.out.println("");
                                            break;
                                        default:
                                            System.out.println("Please enter a valid number");
                                            break;
                                    }
                                    Util.updatePlayer(player);

                                }
                                break;
                            case 4: //Delete player
                                //TODO Delete player - Lasse
                                ArrayList<Player> players = new ArrayList<>(Util.loadPlayers());
                                for (Player p : players) {
                                    System.out.println(p);
                                }
                                System.out.println("Who do you want to remove?");
                                System.out.println("Player number:");
                                playerNumber = scan.nextInt();
                                String conf = Util.deletePlayer(playerNumber, players);
                                System.out.println(conf);

                                break;
                            case 5:
                                //TODO Quit
                                break;
                            default:
                                System.out.println("Please enter a valid number");
                                break;
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

                                    case 2:
                                        System.out.println("Please enter opposing team: ");
                                        scan.nextLine();
                                        opposingTeam = scan.nextLine();
                                        System.out.println("Please enter the date for the game, in the format: YYYY-MM-DD");
                                        time = scan.next();
                                        LocalDate machdate = LocalDate.parse(time);
                                        System.out.println("Please enter time for the game: HH:MM:SS ");
                                        time = scan.next();
                                        LocalTime timeOfPlayedGame = LocalTime.parse(time);
                                        System.out.println("Please add players who played the game by playernumber seperated by periot: ");
                                        if(Util.viewPlayers()){
                                            scan.nextLine();
                                            playerNumberString = scan.next(); // There is probably a better way to do this...
                                            ArrayList<Integer> playerNumberIntList = new ArrayList<>();
                                            ArrayList<String> playerNumberList = new ArrayList<>(Arrays.asList(playerNumberString.split(",")));
                                            playerList = new ArrayList<>();
                                            for(String s: playerNumberList){
                                                playerNumberIntList.add(Integer.parseInt(s));
                                            }
                                            for(Integer p: playerNumberIntList){
                                                playerList.add(Util.getPlayer(p));
                                            }
                                        }
                                        System.out.println("Was there any goals this game: ");
                                        wasThereGolas = scan.next();
                                        if(wasThereGolas.equalsIgnoreCase("yes")){
                                                while (!addMorePlayers) {
                                                    System.out.println("Which player scored ?");
                                                    for (Player p : playerList) {
                                                        System.out.println(p);
                                                    }
                                                    System.out.println("player number: ");
                                                    playerNumber = scan.nextInt();
                                                    String playerName = Util.getPlayer(playerNumber).getName();
                                                    System.out.println("When did he score ?");
                                                    scan.nextLine();
                                                    String timeOfGoal = scan.nextLine();
                                                    goals.add("Player: " + playerName + " scored at: " + timeOfGoal);
                                                    System.out.println("Add another player ?");
                                                    String addPlayer = scan.next();
                                                    addMorePlayers = !addPlayer.equalsIgnoreCase("yes");
                                                }
                                        }else{
                                            System.out.println("No goals was added");
                                            goals.add("0-0");
                                        }
                                        System.out.println("The result of the game ?");
                                        scan.nextLine();
                                        String resultOfGame = scan.nextLine();
                                        if(!goals.get(0).contains(resultOfGame)){
                                            Util.createGamePlayed(playerList, opposingTeam,resultOfGame,machdate,timeOfPlayedGame,goals,opposingTeam);
                                        }else{
                                            System.out.println("result is not maching the number of goals!");
                                        }

                                    case 3:
                                        //TODO Edit game code. This is only for the variable gameCanceled in game, no deletion or edit of other att in a game.
                                    case 4:
                                        break;
                                }
                            case 2:
                                //TODO Some code for viewing game from file
                                System.out.println("What type of game do you want to view ? \n1: Upcomming Game \n2: Played Game");
                                int choice = scan.nextInt();
                                switch (choice){
                                    case 1:
                                        upcommingFileList = new ArrayList<>(Util.listFilesUpcomming()); //Listes the files in the folder
                                        for(String s: upcommingFileList){
                                            System.out.println(s);
                                        }
                                        System.out.println("Please select a file from the list");
                                        scan.nextLine();
                                        String fileToDisplayUpcomming = scan.nextLine();
                                        try{
                                            File file = new File(System.getProperty("user.dir") + "/UpcommingGames/" + fileToDisplayUpcomming + ".txt");
                                            if(file.isFile()){
                                                Game game = Util.loadGame(file);
                                                System.out.println(game);
                                                game.getPlayers();
                                                game.getGoals();
                                            }


                                        }catch (Exception e){

                                        }


                                        break;

                                    case 2:
                                        playedGameFileList = new ArrayList<>(Util.listFilesPlayed());
                                        for(String s: playedGameFileList){
                                            System.out.println(s);
                                        }
                                        System.out.println("Please select a file from the list");
                                        scan.nextLine();
                                        String fileToDisplayPlayed = scan.nextLine();
                                        try{
                                            File file = new File(System.getProperty("user.dir") + "/PlayedGames/" + fileToDisplayPlayed + ".txt");
                                            if(file.isFile()){
                                                Game game = Util.loadGame(file);
                                                System.out.println(game);
                                                game.getPlayers();
                                                game.getGoals();
                                            }


                                        }catch (Exception e){

                                        }
                                        break;

                                    default:
                                        System.out.println("Please select a number from the menu!");
                                        break;

                                }

                            case 3:
                                break;
                            default:
                                System.out.println("Please enter a valid number");
                                break;
                        }
                    case 3:
                        break;

                    default:
                        System.out.println("Please enter a valid number");
                        break;
                }
            }
        }

}
