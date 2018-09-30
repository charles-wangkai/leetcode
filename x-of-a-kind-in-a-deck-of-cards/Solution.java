import java.util.HashMap;
import java.util.Map;

public class Solution {
	public boolean hasGroupsSizeX(int[] deck) {
		Map<Integer, Integer> cardToCount = new HashMap<>();
		for (int card : deck) {
			cardToCount.put(card, cardToCount.getOrDefault(card, 0) + 1);
		}

		return cardToCount.values().stream().reduce(this::gcd).get() != 1;
	}

	int gcd(int a, int b) {
		return (b == 0) ? a : gcd(b, a % b);
	}
}
