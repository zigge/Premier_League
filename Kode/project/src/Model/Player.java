package Model;

import Controller.Util;

import java.io.Serializable;

/**
 * Created by lassebjorklund on 20/11/15.
 */
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int salary;
    private String position;
    private String nationality;
    private int game; //Number of games played
    private int won;
    private int lost;
    private int ties;
    private int playerNumber;
    private int goal; //Consists of two ints

    public Player(String name, int salary, int position, String nationality, int playerNumber) {

        this.name = name;
        this.salary = salary;
        setPosition(position); // Sets position number -> 1 = "Forward position" with a call to methode "setPosition()"
        this.nationality = nationality;
        this.playerNumber = playerNumber;
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

    public int getGoal() {

        return goal;
    }

    public String getPosition() {

        return position;
    }

    public int getPlayerNumber() {

        return playerNumber;

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

        this.nationality = nationality;
    }

    public void setGame(int game) {

        this.game += game;

    }

    public void setGamesWon(int gamesWon) {

        this.won += gamesWon;
    }

    public void setGamesLoss(int gamesLoss) {

        this.lost += gamesLoss;
    }

    public void setTies(int ties) {

        this.ties += ties;
    }

    public void setGoal(int goal) {

        this.goal += goal;
    }

    public void setPlayerNumber(int number){

        playerNumber = number;
    }

    public boolean compare(Player s){
        if(this.getPlayerNumber() == s.getPlayerNumber()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String toString(){
        return "Name: " + getName() + "." + " Nationality: " + getNationalaty() + "." + " Player Number: " + getPlayerNumber() + "." + " Salary: " + getSalary() + "." + " Games: " + getGame() +
               "." + " Position: " + getPosition() + "." + " Won: " + getGamesWon() + "." + " Losses: " + getGamesLoss() + "." + " Ties: " + getTies() + ".";
    }


}
