import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Solution {
	static int[] R_OFFSETS = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] C_OFFSETS = { 1, 2, 2, 1, -1, -2, -2, -1 };

	public double knightProbability(int N, int K, int r, int c) {
		Map<Square, Double> square2prob = new HashMap<Square, Double>();
		square2prob.put(new Square(r, c), 1.0);
		for (int i = 0; i < K; i++) {
			Map<Square, Double> nextSquare2prob = new HashMap<Square, Double>();
			for (Entry<Square, Double> entry : square2prob.entrySet()) {
				Square square = entry.getKey();
				double prob = entry.getValue();
				for (int j = 0; j < R_OFFSETS.length; j++) {
					int nextR = square.r + R_OFFSETS[j];
					int nextC = square.c + C_OFFSETS[j];
					if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < N) {
						Square nextSquare = new Square(nextR, nextC);
						nextSquare2prob.put(nextSquare,
								nextSquare2prob.getOrDefault(nextSquare, 0.0) + prob / R_OFFSETS.length);
					}
				}
			}
			square2prob = nextSquare2prob;
		}
		return square2prob.values().stream().mapToDouble(x -> x).sum();
	}
}

class Square {
	int r;
	int c;

	Square(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public int hashCode() {
		return r * c;
	}

	@Override
	public boolean equals(Object obj) {
		Square other = (Square) obj;
		return r == other.r && c == other.c;
	}
}