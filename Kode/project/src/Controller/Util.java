package Controller;

import java.io.File;
import Model.Game;

/**
 * Created by lassebjorklund on 20/11/15.
 */
public class Util {
    File savelocation = new File();

//    TODO: Define if we determine the location of save games, or the user do!
//    TODO: Complete file
    public void saveGame(Game game){

        if(System.getProperties("os.name").contains("Mac OS X")){ // Gets system name = Mac OS X. This is do to file system structure is different on operation systems.

        }else if(System.getProperties("os.name").contains("Windows")){ // Gets system name = Windows

        }else{ // Gets system name = Linux

        }

    }

    public enum fieldPosition{
        FORWARD("Forward position"),
        DEFENDER("Defending position"),
        MIDFIELD("Midfield position"),
        KEEPER("Keeper position");

        private String positionOnField;


        public String getPositionOnField() {
            return positionOnField;
        }

        fieldPosition(String positionOnField){
            this.positionOnField = positionOnField;
        }

    }

    public enum strategiesOfGame{
        FOURFOURTWO("4-4-2"),
        FOURTHREETHREE("4-3-3"),
        FOURFIVEONE("4-5-1"),
        THREEFIVETWO("3-5-2"),
        THREEFOURTHREE("3-4-3");

        private String strategies;

        public String getStrategies() {
            return strategies;
        }

        strategiesOfGame(String strategies){
            this.strategies = strategies;
        }
    }
}
