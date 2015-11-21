package Model;

import Controller.Util;

/**
 * Created by lassebjorklund on 20/11/15.
 */
public class Player {

    private String name;
    private int salary;
    private String position;
    private String nationality;
    private int game;
    private int gamesWon;
    private int gamesLoss;
    private int ties;
    private String goal;


    public Player(String name, int salary, int position, String nationality){

        this.name = name;
        this.salary = salary;
        setPosition(position); // Sets position number -> 1 = "Forward position" with a call to methode "setPosition()"
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public String getNationalaty() {
        return nationality;
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

    public String getPosition() {

        return position;
    }

    //Before this methode is called, call a method that displays positions on field!!!
    public void setPosition(int pos){ // Takes a int, witch is the position number fron the constructer: Positinon: int
        if(pos < Util.fieldPosition.values().length || pos > 0){ // Gets the number of values of the enum: 4 in this case
            position = Util.fieldPosition.values()[pos].getPositionOnField();
        }else{
            System.out.println("The position does not exists");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setNationalaty(String nationalaty) {
        nationalaty = nationalaty;
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
