package Controller;

/**
 * Created by lassebjorklund on 20/11/15.
 */
public class Util {


    public enum fiealdPosition{
        FORWARD("Forward position", 1),
        DEFENDER("Defending position", 2),
        MIDFIELD("Midfield position", 3),
        KEEPER("Keeper position", 4);

        private String positionOnField;
        private int positionNumber;


        public String getPositionOnField() {
            return positionOnField;
        }

        public int getPositionNumber() {
            return positionNumber;
        }

        fiealdPosition(String positionOnField, int positionNumber){
            this.positionOnField = positionOnField;
            this.positionNumber = positionNumber;
        }

    }

    public enum 
}
