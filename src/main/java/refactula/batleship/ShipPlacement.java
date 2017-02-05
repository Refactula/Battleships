package refactula.batleship;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class ShipPlacement {
    private final int boardWidth;
    private final int boardHeight;
    private final ImmutableList<Ship> ships;

    private ShipPlacement(int boardWidth, int boardHeight, ImmutableList<Ship> ships) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.ships = ships;
    }

    public static Builder onBoard(int boardWidth, int boardHeight) {
        return new Builder(boardWidth, boardHeight);
    }

    public ImmutableList<Ship> getShips() {
        return ships;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public static class Builder {
        private final int boardWidth;
        private final int boardHeight;
        private final List<Ship> ships = new ArrayList<>();

        private Builder(int boardWidth, int boardHeight) {
            this.boardWidth = boardWidth;
            this.boardHeight = boardHeight;
            Preconditions.checkArgument(boardWidth > 0);
            Preconditions.checkArgument(boardHeight > 0);
        }

        public Builder withShip(Ship ship) {
            Preconditions.checkArgument(ship.isInsideBounds(boardWidth, boardHeight));
            for (Ship existingShip : ships) {
                Preconditions.checkArgument(!existingShip.isTouching(ship));
            }
            ships.add(ship);
            return this;
        }

        public ShipPlacement build() {
            return new ShipPlacement(boardWidth, boardHeight, ImmutableList.copyOf(ships));
        }
    }
}
