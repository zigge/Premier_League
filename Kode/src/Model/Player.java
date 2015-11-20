package Model;

/**
 * Created by lassebjorklund on 20/11/15.
 */
public class Player {

    private String name;
    private int salary;
    private int position;
    private String Nationalaty;
    private int game;
    private int gamesWon;
    private int gamesLoss;
    private int ties;
    private String goal;


    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public String getNationalaty() {
        return Nationalaty;
    }

    public int getGame() {
        return game;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesLoss() {
        return gamesLoss;
    }

    public int getTies() {
        return ties;
    }

    public String getGoal() {
        return goal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setNationalaty(String nationalaty) {
        Nationalaty = nationalaty;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public void setGamesLoss(int gamesLoss) {
        this.gamesLoss = gamesLoss;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
