public class JudgeRouteCircle {
	public boolean judgeCircle(String moves) {
		int r = 0;
		int c = 0;
		for (char move : moves.toCharArray()) {
			if (move == 'R') {
				c++;
			} else if (move == 'L') {
				c--;
			} else if (move == 'U') {
				r--;
			} else if (move == 'D') {
				r++;
			}
		}
		return r == 0 && c == 0;
	}
}
