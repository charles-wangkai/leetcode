import java.util.HashMap;
import java.util.Map;

public class Solution {
	public String fractionToDecimal(int numerator, int denominator) {
		StringBuilder result = new StringBuilder();
		if ((long) numerator * denominator < 0) {
			result.append("-");
		}
		long numeratorLong = Math.abs((long) numerator);
		long denominatorLong = Math.abs((long) denominator);
		result.append(numeratorLong / denominatorLong);
		numeratorLong %= denominatorLong;
		if (numeratorLong != 0) {
			Map<Long, Integer> remainder2index = new HashMap<Long, Integer>();
			StringBuilder fraction = new StringBuilder();
			for (int i = 1;; i++) {
				if (numeratorLong == 0) {
					break;
				}
				if (remainder2index.containsKey(numeratorLong)) {
					fraction.insert(remainder2index.get(numeratorLong) - 1, "(");
					fraction.append(")");
					break;
				}
				remainder2index.put(numeratorLong, i);

				numeratorLong *= 10;
				fraction.append(numeratorLong / denominatorLong);
				numeratorLong %= denominatorLong;
			}
			result.append(".");
			result.append(fraction);
		}
		return result.toString();
	}
}
