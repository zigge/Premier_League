package Model;

/**
 * Created by lassebjorklund on 20/11/15.
 */
public class Keeper extends Player {

    private int saves;


    public Keeper(String name, int salary, int position, String nationality, int playerNumber, int saves) {
        super(name, salary, position, nationality, playerNumber);
        this.saves = saves;
    }

    public int getSaves() {
        return saves;
    }

    public void setSaves(int saves) {
        this.saves = saves;
    }

    public String toString(){
        return "Name: " + getName() + " Nationalaty: " + getNationalaty() + "Player Number: " + getPlayerNumber() + " Salary: " + getSalary() + " Games: " + getGame() +
                " Position: " + getPosition() + " Won: " + getGamesWon() + " Losses: " + getGamesLoss() + " Ties: " + getTies() + " Saves: " + getSaves();
    }
}
