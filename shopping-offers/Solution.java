import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Solution {
	static final int PLACE_VALUE = 7;

	Map<Integer, Integer> code2money;

	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
		code2money = new HashMap<Integer, Integer>();
		return search(price, special, needs, 0);
	}

	int search(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int specialIndex) {
		int code = encode(needs);

		if (code == 0) {
			return 0;
		}
		if (code2money.containsKey(code)) {
			return code2money.get(code);
		}

		int money;
		if (specialIndex == special.size()) {
			money = 0;
			for (int i = 0; i < needs.size(); i++) {
				money += price.get(i) * needs.get(i);
			}
		} else {
			money = Integer.MAX_VALUE;

			List<Integer> oneSpecial = special.get(specialIndex);

			int specialNum = 0;
			while (true) {
				money = Math.min(money, oneSpecial.get(oneSpecial.size() - 1) * specialNum
						+ search(price, special, needs, specialIndex + 1));

				if (!IntStream.range(0, needs.size()).allMatch(i -> oneSpecial.get(i) <= needs.get(i))) {
					break;
				}

				IntStream.range(0, needs.size()).forEach(i -> needs.set(i, needs.get(i) - oneSpecial.get(i)));
				specialNum++;
			}

			final int specialNumFinal = specialNum;
			IntStream.range(0, needs.size())
					.forEach(i -> needs.set(i, needs.get(i) + oneSpecial.get(i) * specialNumFinal));
		}

		code2money.put(code, money);
		return money;
	}

	int encode(List<Integer> needs) {
		int code = 0;
		for (int need : needs) {
			code = code * PLACE_VALUE + need;
		}
		return code;
	}
}
