package creatures;

import huglife.*;

import java.awt.Color;
import java.util.*;

/**
 * An implementation of predator that enjoys nothing more than snacking on hapless Plips.
 *
 * @author Yun Hong
 */

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /** should always return the color red = 34, green = 0, blue = 231. */
    @Override
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    /** If a Clorus attacks another creature, it should gain that creature’s energy. */
    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    /** Cloruses should lose 0.03 units of energy on a MOVE action. */
    @Override
    public void move() {
        energy -= 0.03;
        if (energy < 0)
            energy = 0;
    }

    /** Cloruses should lose 0.01 units of energy on a STAY action. */
    @Override
    public void stay() {
        energy -= 0.01;
        if (energy < 0)
            energy = 0;
    }

    /** when a Clorus replicates, it keeps 50% of its energy. The other 50% goes to its offspring. */
    @Override
    public Plip replicate() {
        double babyEnergy = energy * 0.5;
        energy = babyEnergy;
        return new Plip(babyEnergy);
    }

    /**Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if any Plips are seen, ATTACK one of them randomly.
     * 3. Otherwise, if energy >= 1, REPLICATE to a random empty square
     * 4. Otherwise, MOVE to a random empty square.
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {

        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> pilpNeighbors = new ArrayDeque<>();
        for ( Direction key : neighbors.keySet()){
            if (neighbors.get(key).name().equals("plip")){
                pilpNeighbors.add(key);
            }
            if (neighbors.get(key).name().equals("empty")){
                emptyNeighbors.add(key);
            }
        }
        // Rule 1
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        // Rule 2
        else if (pilpNeighbors.size() != 0){
            return new Action(Action.ActionType.ATTACK, randomEntry(pilpNeighbors));
        }
        // Rule 3
        else if(energy >= 1){
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }
        // Rule 4
        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
    }

    /** Randomly return an element in the deque. */
    public static Direction randomEntry(Deque<Direction> deque){
        int dir = (int) Math.round(Math.random() * (deque.size() - 1));
        Object [] array = deque.toArray();
        return (Direction) array[dir];
    }

}
