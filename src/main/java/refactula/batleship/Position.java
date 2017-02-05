package refactula.batleship;

import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.function.Consumer;

public class Position {
    private static final int[] NEIGHBOUR_DX = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static final int[] NEIGHBOUR_DY = {-1, -1, -1, 0, 0, 1, 1, 1};

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isInsideBounds(int width, int height) {
        return 0 <= x && x < width && 0 <= y && y < height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("x", x)
                .add("y", y)
                .toString();
    }

    public void addNeighbours(Consumer<Position> positionConsumer) {
        for (int i = 0; i < NEIGHBOUR_DX.length; i++) {
            positionConsumer.accept(new Position(x + NEIGHBOUR_DX[i], y + NEIGHBOUR_DY[i]));
        }
    }
}
