package View;

import java.io.File;
import java.util.Scanner;

/**
 * Created by lassebjorklund on 21/11/15.
 */
public class MenuSystem {
    private static Scanner scan;
    private static int headMenu, playerMenu, gameMenu, gameSubMenu;
    private static boolean running;


    //Menu run class
   public static void menu() {
       scan = new Scanner(System.in);
       running = true;
       // Run a check for player file!!
       //TODO Decide if this check is necessary.
       /*if(System.getProperty("os.name").contains("OS X")){

       }*/
       while(running) {
           //Menu print out
           System.out.println("Welcome! \nPlease select one of the menu point below:");
           System.out.println("1: Games \n2: Players \n3: Quit ");
           headMenu = scan.nextInt();
           switch (headMenu) {
               case 1:
                   System.out.println("Please select an option: \n1: New Player \n2: View players \n3: Edit player \n4: Quit ");
                   playerMenu = scan.nextInt();
                   switch (playerMenu) {
                       case 1:
                           //TODO Some code for "new player" Who is writing this part ?
                           break;
                       case 2:
                           //TODO Some code for "View player" Who is writing this part ?
                           break;
                       case 3:
                           //TODO Some code for "Edit player" Who is writing this part ?
                           break;
                       case 4:
                           //TODO Some code for "Quit" Who is writing this part ?
                           break;
                   }

               case 2:
                   System.out.println("Please select an option: \n1: Manage Games \n2: View Game from file \n3: Quit");
                   gameMenu = scan.nextInt();
                   switch (gameMenu){
                       case 1:
                           System.out.println("Please select an option: \n1: Upcomming game \n2: Played game \n3: Return  ");
                           switch (gameSubMenu){
                               case 1:
                                //TODO Some code for pre game info
                               case 2:
                                //TODO Some code for post game info
                               case 3:

                           }

                       case 2:
                           //TODO Some code for viewing game from file

                       case 3:
                           //TODO Some code for quitting program

                   }


               case 3:
                   //TODO Some code for "Quit" Who is writing this part ?
           }
       }
   }

}
