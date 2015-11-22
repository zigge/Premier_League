package View;

import java.io.File;
import java.util.Scanner;

/**
 * Created by lassebjorklund on 21/11/15.
 */
public class MenuSystem {
    private static Scanner scan;
    private static int headMenu, playerMenu, GameMenu;
    private static File playerFile = new File(System.getProperty("user.dir") + "/Players", "txt");
    private static File playerDIR = new File("Players");
    private static boolean folderCreated, running;


    //Menu run class
   public static void menu() {
       running = true;
       // Run a check for player file!!
       // Decide if this check is necessary.
       /*if(System.getProperty("os.name").contains("OS X")){

       }*/
       //We wanna know where the players are saved, so that we can load them at the start of the program.
       while(running) {
           try {

               playerDIR.mkdir();
               folderCreated = true;
           } catch (SecurityException e) {
               System.err.println("Cannot create folder");
               folderCreated = false;
           }

           //Menu print out
           System.out.println("Welcome! \n Please select one of the menu point below:");
           System.out.println("1: Games \n 2: Players \n 3: Quit ");
           headMenu = scan.nextInt();

           switch (headMenu) {
               case 1:
                   System.out.println("Please select an option: \n 1: New Player \n 2: View players \n 3: Edit player \n 4: Quit ");
                   playerMenu = scan.nextInt();
                   switch (playerMenu) {
                       case 1:
                           //Some code for "new player"
                       case 2:
                           //Some code for "View player"
                       case 3:
                           //Some code for "Edit player"
                       case 4:
                           //Some code for "Quit"
                   }

               case 2:
                   //Some code for "Game"

               case 3:
                   //Some code for "Quit"
           }
       }
   }

}
