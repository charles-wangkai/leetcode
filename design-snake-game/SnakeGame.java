import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class SnakeGame {
	int width;
	int height;
	int[][] food;

	Point pos;
	int foodIndex = 0;
	Deque<Point> body;
	Set<Point> bodySet;
	int score = 0;

	/**
	 * Initialize your data structure here.
	 * 
	 * @param width
	 *            - screen width
	 * @param height
	 *            - screen height
	 * @param food
	 *            - A list of food positions E.g food = [[1,1], [1,0]] means the
	 *            first food is positioned at [1,1], the second is at [1,0].
	 */
	public SnakeGame(int width, int height, int[][] food) {
		this.width = width;
		this.height = height;
		this.food = food;

		pos = new Point(0, 0);
		body = new LinkedList<Point>();
		body.add(pos);
		bodySet = new HashSet<Point>();
		bodySet.add(pos);
	}

	/**
	 * Moves the snake.
	 * 
	 * @param direction
	 *            - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
	 * @return The game's score after the move. Return -1 if game over. Game over
	 *         when snake crosses the screen boundary or bites its body.
	 */
	public int move(String direction) {
		go(direction);

		if (isOutOfBox()) {
			return -1;
		}

		if (foodIndex < food.length && pos.r == food[foodIndex][0] && pos.c == food[foodIndex][1]) {
			score++;
			foodIndex++;
		} else {
			Point tail = body.removeLast();
			bodySet.remove(tail);
		}

		if (bodySet.contains(pos)) {
			return -1;
		}
		bodySet.add(pos);
		body.addFirst(pos);

		return score;
	}

	private boolean isOutOfBox() {
		return pos.r < 0 || pos.r >= height || pos.c < 0 || pos.c >= width;
	}

	private void go(String direction) {
		int nextR = pos.r;
		int nextC = pos.c;
		if (direction.equals("U")) {
			nextR--;
		} else if (direction.equals("L")) {
			nextC--;
		} else if (direction.equals("R")) {
			nextC++;
		} else if (direction.equals("D")) {
			nextR++;
		}
		pos = new Point(nextR, nextC);
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
		return r * c;
	}

	@Override
	public boolean equals(Object obj) {
		Point other = (Point) obj;
		return r == other.r && c == other.c;
	}
}

// Your SnakeGame object will be instantiated and called as such:
// SnakeGame obj = new SnakeGame(width, height, food);
// int param_1 = obj.move(direction);
