package refactula.batleship;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

import java.util.Objects;

public class Ship {
    private final ImmutableSet<Position> occupiedCellPositions;
    private final ImmutableSet<Position> neighbourPositions;

    public static Ship horizontal(int x1, int x2, int y) {
        return rectangular(x1, y, x2, y);
    }

    public static Ship vertical(int x, int y1, int y2) {
        return rectangular(x, y1, x, y2);
    }

    private static Ship rectangular(int x1, int y1, int x2, int y2) {
        Builder<Position> builder = ImmutableSet.builder();
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                builder.add(new Position(x, y));
            }
        }
        return create(builder.build());
    }

    public static Ship create(ImmutableSet<Position> occupiedCellPositions) {
        Preconditions.checkNotNull(occupiedCellPositions);
        Builder<Position> builder = ImmutableSet.builder();
        for (Position position : occupiedCellPositions) {
            position.addNeighbours(builder::add);
        }
        return new Ship(occupiedCellPositions, builder.build());
    }

    private Ship(ImmutableSet<Position> occupiedCellPositions, ImmutableSet<Position> neighbourPositions) {
        this.occupiedCellPositions = occupiedCellPositions;
        this.neighbourPositions = neighbourPositions;
    }

    public ImmutableSet<Position> getOccupiedCellPosition() {
        return occupiedCellPositions;
    }

    public boolean isInsideBounds(int width, int height) {
        for (Position position : occupiedCellPositions) {
            if (!position.isInsideBounds(width, height)) {
                return false;
            }
        }
        return true;
    }

    public boolean isTouching(Ship otherShips) {
        return neighbourPositions.stream().anyMatch(otherShips::isOccupying);
    }

    private boolean isOccupying(Position position) {
        return occupiedCellPositions.contains(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return Objects.equals(occupiedCellPositions, ship.occupiedCellPositions) &&
                Objects.equals(neighbourPositions, ship.neighbourPositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(occupiedCellPositions, neighbourPositions);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("occupiedCellPositions", occupiedCellPositions)
                .add("neighbourPositions", neighbourPositions)
                .toString();
    }
}
