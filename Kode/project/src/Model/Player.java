package Model;

import Controller.Util;

import java.io.Serializable;

/**
 * Created by lassebjorklund on 20/11/15.
 */
public class Player implements Serializable {

    private String name;
    private int salary;
    private String position;
    private String nationality;
    private int game; //Number of games played
    private int won;
    private int lost;
    private int ties;
    private String goal; //Consists of two ints


    public Player(String name, int salary, int position, String nationality) {

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
        return won;
    }

    public int getGamesLoss() {
        return lost;
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

    //Before this method is called, call a method that displays positions on field!!!
    public void setPosition(int pos){// Takes an int, which is the position number from the constructor: Position: int
            position = Util.fieldPosition.values()[pos].getPositionOnField();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setNationality(String nationality) {
        nationality = nationality;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public void setGamesWon(int gamesWon) {
        this.won = gamesWon;
    }

    public void setGamesLoss(int gamesLoss) {
        this.lost = gamesLoss;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }
}
