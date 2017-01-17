import java.util.HashMap;
import java.util.Map;

public class ZumaGame {
	public int findMinStep(String board, String hand) {
		Map<Character, Integer> handBall2count = buildHandBall2count(hand);
		return search(board, handBall2count);
	}

	Map<Character, Integer> buildHandBall2count(String hand) {
		Map<Character, Integer> handBall2count = new HashMap<Character, Integer>();
		for (char ball : hand.toCharArray()) {
			increase(handBall2count, ball);
		}
		return handBall2count;
	}

	int search(String board, Map<Character, Integer> handBall2count) {
		if (board.isEmpty()) {
			return 0;
		}

		int result = Integer.MAX_VALUE;

		char ball = 0;
		int count = -1;
		for (int i = 0; i <= board.length(); i++) {
			if (i == board.length() || board.charAt(i) != ball) {
				if (count > 0 && handBall2count.containsKey(ball)) {
					decrease(handBall2count, ball);

					String nextBoard = clean(new StringBuilder(board).insert(i, ball).toString());
					int subResult = search(nextBoard, handBall2count);
					if (subResult >= 0) {
						result = Math.min(result, 1 + subResult);
					}

					increase(handBall2count, ball);
				}

				if (i < board.length()) {
					ball = board.charAt(i);
				}
				count = 1;
			} else {
				count++;
			}
		}

		return (result == Integer.MAX_VALUE) ? -1 : result;
	}

	void increase(Map<Character, Integer> handBall2count, char ball) {
		handBall2count.put(ball, handBall2count.getOrDefault(ball, 0) + 1);
	}

	void decrease(Map<Character, Integer> handBall2count, char ball) {
		handBall2count.put(ball, handBall2count.get(ball) - 1);

		if (handBall2count.get(ball) == 0) {
			handBall2count.remove(ball);
		}
	}

	String clean(String board) {
		char ball = 0;
		int count = -1;
		for (int i = 0; i <= board.length(); i++) {
			if (i == board.length() || board.charAt(i) != ball) {
				if (count >= 3) {
					return clean(board.substring(0, i - count) + board.substring(i));
				}

				if (i < board.length()) {
					ball = board.charAt(i);
				}
				count = 1;
			} else {
				count++;
			}
		}
		return board;
	}
}
