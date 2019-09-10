package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/** Tests the clorus class
 *  @authr Yun Hong
 */

public class TestClorus {
    @Test
    public void TestBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.001);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();;
        assertEquals(1.97, c.energy(), 0.001);
        c.move();;
        assertEquals(1.94, c.energy(), 0.001);
        c.stay();
        assertEquals(1.93, c.energy(), 0.001);
        c.stay();
        assertEquals(1.92, c.energy(), 0.001);
    }

    @Test
    public void TestAttack() {
        Clorus c = new Clorus(2);
        Plip p = new Plip(1);
        c.attack(p);
        assertEquals(3, c.energy(), 0.001);
    }

    @Test
    public void TestReplicate() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(1, c.replicate().energy(), 0.01);
        assertEquals(1, c.energy(), 0.01);
    }

    @Test
    public void TestChoose(){
        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // if any Plips are seen, ATTACK one of them randomly.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> ButtomPlip = new HashMap<Direction, Occupant>();
        ButtomPlip.put(Direction.TOP, new Empty());
        ButtomPlip.put(Direction.BOTTOM, new Plip(1));
        ButtomPlip.put(Direction.LEFT, new Empty());
        ButtomPlip.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(ButtomPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);

        assertEquals(expected, actual);

        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);

        // Energy < 1; move to an empty space;
        c = new Clorus(0.9);

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertEquals(expected, actual);

    }
}
