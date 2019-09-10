package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Random;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

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
    public Plip(double e) {
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        if (e > 2)
            energy = 2;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    @Override
    public Color color() {
        r = 99;
        b = 76;
        g =63 + (int) (96 * this.energy());
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    @Override
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    @Override
    public void move() {
        energy -= 0.15;
        if (energy < 0)
            energy = 0;
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    @Override
    public void stay() {
        if (energy > 1.8)
            energy = 1.8;
        energy += 0.2;
        if (energy > 2)
            energy = 2;
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    @Override
    public Plip replicate() {
        if (energy > 2)
            energy = 2;
        double babyEnergy = energy * 0.5;
        energy = babyEnergy;
        return new Plip(babyEnergy);
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
        for ( Direction key : neighbors.keySet()){
            if (neighbors.get(key).name().equals("clorus")){
                anyClorus = true;
                break;
            }
            if (neighbors.get(key).name().equals("empty")){
                emptyNeighbors.add(key);
            }
        }
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}

        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        // HINT: randomEntry(emptyNeighbors)
        else if (energy >=1 ){
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }

        // Rule 3
        if(anyClorus && Math.random() < 0.5){
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
        }

        // Rule 4
        return new Action(Action.ActionType.STAY);
    }

    /** Randomly return an element in the deque. */
    public static Direction randomEntry(Deque<Direction> deque){
        int dir = (int) Math.round(Math.random() * (deque.size() - 1));
        Object [] array = deque.toArray();
        return (Direction) array[dir];
    }


}
