package Model;

//-------Everybody is responsible-------

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
        this.saves += saves;
    }

    @Override
    public String toString(){
        return "Name: " + getName() + "." + " Nationality: " + getNationalaty() + "." + " Player Number: " + getPlayerNumber() + "." + " Salary: " + getSalary() + "." + " Games: " + getGame() +
               "." + " Position: " + getPosition() + "." + " Won: " + getGamesWon() + "." + " Losses: " + getGamesLoss() + "." + " Ties: " + getTies() + "." + " Saves: " + getSaves() + ".";
    }
}
