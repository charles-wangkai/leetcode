import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Solution {
    public boolean isPathCrossing(String path) {
        int x = 0;
        int y = 0;
        Set<Point> history = new HashSet<>();
        history.add(new Point(x, y));

        for (char command : path.toCharArray()) {
            if (command == 'N') {
                ++y;
            } else if (command == 'S') {
                --y;
            } else if (command == 'E') {
                ++x;
            } else {
                --x;
            }

            Point point = new Point(x, y);
            if (history.contains(point)) {
                return true;
            }
            history.add(point);
        }

        return false;
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        Point other = (Point) obj;

        return x == other.x && y == other.y;
    }
}