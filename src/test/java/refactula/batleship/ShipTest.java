package refactula.batleship;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShipTest {

    @Test
    public void createHorizontalShip() {
        Ship ship = Ship.horizontal(1, 4, 3);
        assertEquals(ImmutableSet.of(
                new Position(1, 3),
                new Position(2, 3),
                new Position(3, 3),
                new Position(4, 3)), ship.getOccupiedCellPosition());
    }


    @Test
    public void createVerticalShip() {
        Ship ship = Ship.vertical(6, 5, 6);
        assertEquals(ImmutableSet.of(
                new Position(6, 5),
                new Position(6, 6)), ship.getOccupiedCellPosition());
    }

    @Test(expected = NullPointerException.class)
    public void failNullCells() {
        Ship.create(null);
    }

}