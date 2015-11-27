package View;

import Controller.Util;
import Model.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by lassebjorklund on 21/11/15.
 */
public class MenuSystem {
    private static Scanner scan;
    private static int headMenu, playerMenu, gameMenu, gameSubMenu, playerToDelete, count;
    private static String confDeletePlayer;
    private static ArrayList<Player> playerList;
    private static boolean running;
    private final static String esc = "\u001b[2J";
    private final static String home = "\u001b[H";



    //Menu run class
   public static void menu() {

       Player player1 = new Player("Test", 2000, 2, "Cuba"); //Test players!
       Player player2 = new Player("Test2", 20003, 3, "Cuba");// Test players!
       playerList = new ArrayList<>();
       playerList.add(player1);
       playerList.add(player2);

       scan = new Scanner(System.in);
       running = true;

       while(running) {
           //Menu print out
           System.out.println("Welcome! \nPlease select one of the menu point below:");
           Util.createPlayerFolder();
           System.out.println("1: Players \n2: Games \n3: Quit ");
           System.out.println(headMenu);
           headMenu = scan.nextInt();
           switch (headMenu) {
               case 1:
                   System.out.println("Please select an option: \n1: New Player \n2: View players \n3: Edit player \n4: Delete Player \n5: Quit ");
                   playerMenu = scan.nextInt();
                   switch (playerMenu) {
                       case 1:
                           //TODO Some code for "new player" - Casper
                           //TODO Open issue: If two playes have the same name, what then?
                           // Casper, you need to make this check: if (pos > Util.fieldPosition.values().length || pos < 0), if the number is < 4 run else statement. This i when you set the position

                           break;
                       case 2:
                           //TODO Some code for "View player" - Lucas
                           break;
                       case 3:
                           //TODO Some code for "Edit player" - Henriette
                           break;
                       case 4:
                            //TODO Delete player - Lasse
                           System.out.println(esc + home);


                           break;
                       case 5:
                            //TODO Quit
                           break;
                   }

               case 2:
                   System.out.println("Please select an option: \n1: Manage Games \n2: View Game from file \n3: Quit");
                   gameMenu = scan.nextInt();
                   switch (gameMenu){
                       case 1:
                           System.out.println("Please select an option: \n1: Upcomming game \n2: Create Played game \n3: Cancle Upcomming Game \n4: Quit  ");
                           gameSubMenu = scan.nextInt();
                           switch (gameSubMenu){
                               case 1:
                                //TODO Some code for pre game info
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
