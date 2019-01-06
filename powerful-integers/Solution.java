import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
	public List<Integer> powerfulIntegers(int x, int y, int bound) {
		List<Integer> xPowers = buildPowers(x, bound);
		List<Integer> yPowers = buildPowers(y, bound);

		return xPowers.stream().flatMap(xPower -> yPowers.stream().map(yPower -> xPower + yPower))
				.filter(n -> n <= bound).distinct().collect(Collectors.toList());
	}

	List<Integer> buildPowers(int base, int bound) {
		List<Integer> powers = new ArrayList<>();
		int power = 1;
		while (power < bound) {
			powers.add(power);

			if (base == 1) {
				break;
			}

			power *= base;
		}
		return powers;
	}
}
