package refactula.batleship;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ShipPlacementTest {

    @Test(expected = IllegalArgumentException.class)
    public void failNegativeBoardWidth() {
        ShipPlacement.onBoard(-1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failNegativeBoardHeight() {
        ShipPlacement.onBoard(3, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failZeroBoardWidth() {
        ShipPlacement.onBoard(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void failZeroBoardHeight() {
        ShipPlacement.onBoard(10, 0);
    }

    @Test
    public void onValidBoard() {
        ShipPlacement.onBoard(10, 10);
    }

    @Test
    public void createShipPlacementWithoutShips() {
        ShipPlacement shipPlacement = ShipPlacement.onBoard(10, 10).build();
        assertNotNull(shipPlacement);
    }

    @Test
    public void createShipPlacementWithOneShip() {
        Ship ship = Ship.horizontal(3, 7, 0);
        ShipPlacement shipPlacement = ShipPlacement.onBoard(10, 10).withShip(ship).build();
        assertNotNull(shipPlacement);
        assertEquals(ImmutableList.of(ship), shipPlacement.getShips());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createShipOutsideTheBoard() {
        ShipPlacement.onBoard(10, 10).withShip(Ship.horizontal(-1, 3, 5)).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void createShipOutsideTheBoard2() {
        ShipPlacement.onBoard(10, 10).withShip(Ship.vertical(10, 4, 9)).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void failIntersectingShips() {
        ShipPlacement.onBoard(10, 10)
                .withShip(Ship.horizontal(4, 6, 7))
                .withShip(Ship.vertical(5, 7, 7));
    }

    @Test(expected = IllegalArgumentException.class)
    public void failTouchingShips() {
        ShipPlacement.onBoard(10, 10)
                .withShip(Ship.horizontal(3, 6, 1))
                .withShip(Ship.vertical(2, 2, 3));
    }

    @Test
    public void createValidBoard() {
        Ship horizontalShip = Ship.horizontal(4, 7, 1);
        Ship verticalShip = Ship.vertical(2, 2, 3);
        Ship tinyShip = Ship.vertical(6, 6, 6);

        ShipPlacement shipPlacement = ShipPlacement.onBoard(10, 10)
                .withShip(horizontalShip)
                .withShip(verticalShip)
                .withShip(tinyShip)
                .build();

        assertEquals(
                ImmutableList.of(horizontalShip, verticalShip, tinyShip),
                shipPlacement.getShips());
    }

}