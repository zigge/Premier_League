import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class RunClient {
   final static String esc = "\u001b[2J";
   final static String home = "\u001b[H";
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int menu = 0;
      WashCard activeObject = null;
      Util util = new Util();
      WashCard washCard1 = new WashCard(1234);
      WashCard washCard2 = new WashCard(2341);
      WashCard washCard3 = new WashCard(2134);
      WashCard washCard4 = new WashCard(3231);
      ArrayList<WashCard> washCards = new ArrayList<>();
      washCards.add(washCard1);
      washCards.add(washCard2);
      washCards.add(washCard3);
      washCards.add(washCard4);



      while (true) {

         int activeCard = 0;
         boolean cardPresent = false;

         //Kort Scan
         while (!cardPresent) {
            int cardScan = 0;
            boolean cardScanTrue = true;
            //sæt aktivt kort ID og sæt cardPresent til true
            System.out.println("Velkommen til CarWash");
            System.out.println("");
            System.out.println("indsæt kort");
            cardScanTrue = scanner.hasNextInt();
            if (cardScanTrue) {
               cardScan = scanner.nextInt();
               for (WashCard id : washCards) {
                  if (id.getId() == cardScan) {
                     activeCard = cardScan;
                     activeObject = id;
                     cardPresent = true;
                     continue;
                  }
               }
            } else if (!cardPresent) {
               System.out.println("kort kan ikke aflæses");
               cardScan = scanner.nextInt();
               break;
            }
         }
         //Hoved menu med aktiv session
         while (cardPresent) {
            menu = 0;
            System.out.println(esc + home); //Clear terminal og få teksten til at stå i toppen igen.
            //Vis hovedmenu (indeholder 3 elementer -> Vask, Deposit og Afslut
            System.out.println("Du har " + activeObject.getAmount() + " på kortet."); //Vi skal tage højde for om brugerens input er korrekt. Hvis ikke kast en undtagelse/besked.
            System.out.println("Vælg handling:");
            System.out.println("");
            System.out.println("1. Vask");
            System.out.println("2. Indsæt penge");
            System.out.println("3. Print kvittering fra sidste vask");
            System.out.println("4. Afslut");
            menu = scanner.nextInt();
            if (menu == 1) {
               System.out.println(esc + home); //Clear terminal og få teksten til at stå i toppen igen.
               int index = 0;
               index = util.chooseWashtypelist();
               //int index = scanner.nextInt();
               boolean payment = false;
               payment = util.pay(washCards, activeCard, index); //Vi skal have tjekket om denne retunere en fejl.. !!!!!!
               if (payment == true) { // For at tjekke om Betalingen er gennemført.

                  System.out.println("Ønsker du kvittering?");
                  System.out.println("1. Ja");
                  System.out.println("2. Nej");
                  menu = scanner.nextInt();

                  if (menu == 1) {
                     util.reciept(washCards, activeCard , index);
                     System.out.println(esc + home); //Clear terminal og få teksten til at stå i toppen igen.
                  }
                  util.forceReciept(washCards, activeCard, index);
                  System.out.println(esc + home); //Clear terminal og få teksten til at stå i toppen igen.
               }

               continue;//retur til hovedmenu
            } else if (menu == 2) {
               //metode kald refill
               System.out.println(esc + home); //Clear terminal og få teksten til at stå i toppen igen.
               int amount = 0;
               System.out.println("Du har " + activeObject.getAmount() + " på kortet.");
               System.out.println("Indtast beløb du vil indsætte: ");

               amount = scanner.nextInt();
               util.refill(washCards, activeCard, amount);

               System.out.println(esc + home); //Clear terminal og få teksten til at stå i toppen igen.
               continue;//retur til hoved menu
            }

            else if (menu == 3) {
               try {
                  String lastpurchage = util.getReciet(washCards, activeCard);
                  System.out.println(lastpurchage);
                  TimeUnit.SECONDS.sleep(2);
               } catch (Exception e) {
                  System.out.println(e);
               }
            }

            else if (menu == 4) {
               //afslutter og retunerer til Card Scanner
               cardPresent = false;
               System.out.println(esc + home); //Clear terminal og få teksten til at stå i toppen igen.
               break;
            } else {
               continue;
            }
         }
      }
   }
}