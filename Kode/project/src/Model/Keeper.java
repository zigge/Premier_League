package Model;

/**
 * Created by lassebjorklund on 20/11/15.
 */
public class Keeper extends Player {

    private int saves;


    public Keeper(String name, int salary, int position, String nationality, int saves) {
        super(name, salary, position, nationality);
        this.saves = saves;
    }

    public int getSaves() {
        return saves;
    }

    public void setSaves(int saves) {
        this.saves = saves;
    }
}
