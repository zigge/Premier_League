package View;

import Controller.Util;
import Model.Player;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by lassebjorklund on 21/11/15.
 */
public class MenuSystem {
    private static Scanner scan;
    private static int headMenu, playerMenu, gameMenu, gameSubMenu, editPlayerMenu, playerToDelete, count, playerNumber, editSalary, editPosition, hours, minutes, month, year, day, playerSalary, positionMenu, playerPosition;
    private static String editName, editNationality, opposingTeam, time, playerName, playerNationality, playerFirstName, playerLastName;
    private static ArrayList<Player> playerList;
    private static boolean running;
    private final static String esc = "\u001b[2J";
    private final static String home = "\u001b[H";


    //Menu run class
    public static void menu() {
        Util.createPlayerFolder();
        Util.createPlayerFile();
        Util.createKeeper("Test", 2, 0, "denmark", 1, 1);
        Util.createPlayer("Test2", 2, 1, "Denmark", 1);
        Util.updatePlayerList();

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
                            if (playerPosition < 3 || playerPosition > 0) {
                                System.out.println("here");
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
                                    System.out.println("Please enter time for the game in hours: ");
                                    hours = scan.nextInt();
                                    System.out.println("Please enter time for the game in minutes: ");
                                    minutes = scan.nextInt();
                                    LocalTime timeOfGame = LocalTime.of(hours, minutes);
                                    System.out.println("Please enter the month for the game 1-12:  ");
                                    month = scan.nextInt();
                                    System.out.println("Please enter the day for the game in numbers: ");
                                    day = scan.nextInt();
                                    System.out.println("Please enter the year for the game numbers: ");
                                    year = scan.nextInt();
                                    LocalDate gameDate = LocalDate.of(year, month, day);
                                    Util.createGameUpcommingGame(opposingTeam, gameDate, timeOfGame, opposingTeam);

                                case 2:
                                    //TODO Some code for post game info, REMEMBER the function "saveGame()" takes two parameters.
                                    //TODO A File, and a Game object. The file is the inputted location from a scanner.

                                case 3:
                                    //TODO Edit game code. This is only for the variable gameCanceled in game, no delition or edit of other att in a game.
                                case 4:
                            }
                        case 2:
                            //TODO Some code for viewing game from file

                        case 3:
                            //TODO Some code for quitting program
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
