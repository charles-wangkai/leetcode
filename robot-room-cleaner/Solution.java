import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// This is the robot's control interface.
// You should not implement it, or speculate about its implementation
interface Robot {
	// Returns true if the cell in front is open and robot moves into the cell.
	// Returns false if the cell in front is blocked and robot stays in the current
	// cell.
	public boolean move();

	// Robot will stay in the same cell after calling turnLeft/turnRight.
	// Each turn will be 90 degrees.
	public void turnLeft();

	public void turnRight();

	// Clean the current cell.
	public void clean();
}

public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public void cleanRoom(Robot robot) {
		search(robot, 0, 0, 0, new HashSet<>());
	}

	void search(Robot robot, int r, int c, int direction, Set<Point> cleaned) {
		Point point = new Point(r, c);
		if (cleaned.contains(point)) {
			return;
		}

		robot.clean();
		cleaned.add(point);

		for (int i = 0; i < R_OFFSETS.length; i++) {
			if (robot.move()) {
				int nextR = r + R_OFFSETS[direction];
				int nextC = c + C_OFFSETS[direction];

				search(robot, nextR, nextC, direction, cleaned);

				robot.turnRight();
				robot.turnRight();
				robot.move();
				robot.turnRight();
				robot.turnRight();
			}

			robot.turnRight();
			direction = (direction + 1) % R_OFFSETS.length;
		}
	}
}

class Point {
	int r;
	int c;

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public int hashCode() {
		return Objects.hash(r, c);
	}

	@Override
	public boolean equals(Object obj) {
		Point other = (Point) obj;
		return r == other.r && c == other.c;
	}
}