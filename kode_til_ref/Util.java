/*package com.company.controller;

import com.company.WashCard;
import com.company.Washtype;
*/
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Util {
    Scanner scanner = new Scanner(System.in);

    public String giveTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        String timeStamp = calendar.getTime().toString();
        return timeStamp;
    }

    // private int count = 0;
    public int chooseWashtypelist() {
        int count = 0; 
        System.out.println("Vælg en vasketype:");
        for (Washtype.typeOfWash o : Washtype.typeOfWash.values()) {
            count++;
            System.out.println(count + ": " + o.getName() + " " + o.getPrice() + " kr.");
        }
            int choice = scanner.nextInt();
            
            if (choice > Washtype.typeOfWash.values().length || choice <= 0) {
                do {
                    System.out.println("Indtast en af talene fra listen");
                    choice = scanner.nextInt();
                } while (choice > Washtype.typeOfWash.values().length || choice <= 0);
            }
        return choice-1;
    }


    public void loginSequence() {

    }

    public void refill(ArrayList<WashCard> washCards, int id, int amount) {
        System.out.println("Indtast venligst din kode");
        String pass = scanner.next();
        if (pass.equals("admin")) {
            for (WashCard w : washCards) {
                if (w.getId() == id) {
                    if (w.getAmount() + amount <= 1000 && w.getAmount() + amount >= 50) {
                        w.setAmount(amount);
                    } else {
                        System.out.println("Beløbet er for stort eller for lille, prøv med mindre beløb. Max beløb for dette kort: " + (1000 - w.getAmount()));
                    }
                }
            }
        }
    }

    public boolean pay(ArrayList<WashCard> washCards, int id,  int index){ // for at chekke om payment er gennemført og for at kunne bruge det til en ifstatement.
        boolean isPaymentDone = false; 
        for(WashCard w : washCards){
            if(w.getId() == id){
                if((w.getAmount() - Washtype.typeOfWash.values()[index].getPrice()) >= 0){
                    w.setAmount(-(Washtype.typeOfWash.values()[index].getPrice()));
                    System.out.println("Vask gennemført");
                    // reciept(washCards, id, index); Dette giver ingen mening, da vi spøger om kvitering ønskes efter
                    isPaymentDone = true;
                    break; 
                }else{
                    System.out.println("Ikke nok penge på kortet!");
                    System.out.println((Washtype.typeOfWash.values()[index].getPrice()));
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    break;
                }
            }

        }return isPaymentDone;
    }

    public void reciept(ArrayList<WashCard> washCards, int id , int wash) {
        for (int i = 0; i < 4; i++) {
            System.out.println("");
        }
        String reciept = "Vask: " + Washtype.typeOfWash.values()[wash].getName() + ".\n" +
                "Pris: " + Washtype.typeOfWash.values()[wash].getPrice() + " kr." + "\n" +
                "Date: " + giveTimeStamp();
        for (WashCard w : washCards) {
            if (w.getId() == id) {
                try {
                    System.out.println(reciept);
                    w.setLastPurchase(reciept);
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }
    }
    public void forceReciept(ArrayList<WashCard> washCards, int id , int wash) {
        for (int i = 0; i < 4; i++) {
            System.out.println("");
        }
        String reciept = "Vask: " + Washtype.typeOfWash.values()[wash].getName() + ".\n" +
                "Pris: " + Washtype.typeOfWash.values()[wash].getPrice() + " kr." + "\n" +
                "Date: " + giveTimeStamp();
        for (WashCard w : washCards) {
            if (w.getId() == id) {
                // try {
                //     w.setLastPurchase(reciept);
                //     TimeUnit.SECONDS.sleep(0);
                // } catch (InterruptedException e) {
                //     System.out.println(e);
                // }
                w.setLastPurchase(reciept);
            }
        }
    }

    public String getReciet(ArrayList<WashCard> washCards, int id){
        for (WashCard  w: washCards ) {
            if(w.getId() == id){
                if(w.getLastPurchase().equals(null)){
                    return "du har ikke fortaget dig noget køb!";
                }
                return w.getLastPurchase();
            }   
        } return "Ikke FUndet..."; 
    }
}
