import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class StrobogrammaticNumber_III {
	Map<Integer, BigInteger> length2countCache = new HashMap<Integer, BigInteger>();

	public int strobogrammaticInRange(String low, String high) {
		return Math.max(
				0,
				countStrobogrammatic(high, false, false)
						.subtract(
								countStrobogrammatic(decrease(low, false),
										false, false)).intValue());
	}

	BigInteger countStrobogrammatic(String limit, boolean maintainLength,
			boolean allowLeadingZero) {
		if (limit.isEmpty()) {
			return BigInteger.ONE;
		}

		BigInteger result = BigInteger.ZERO;

		if (new BigInteger(limit).compareTo(BigInteger.ZERO) >= 0) {
			int length = limit.length();

			if (!maintainLength) {
				for (int i = 1; i < length; i++) {
					result = result.add(countStrobogrammatic(i, false));
				}
			}

			if (length == 1) {
				result = result.add(countUpTo(limit, '0'));
				result = result.add(countUpTo(limit, '1'));
				result = result.add(countUpTo(limit, '8'));
			} else {
				if (allowLeadingZero) {
					result = result.add(countUpTo(limit, '0', '0'));
				}
				result = result.add(countUpTo(limit, '1', '1'));
				result = result.add(countUpTo(limit, '8', '8'));
				result = result.add(countUpTo(limit, '6', '9'));
				result = result.add(countUpTo(limit, '9', '6'));
			}
		}

		return result;
	}

	BigInteger countUpTo(String limit, char digit) {
		return (Integer.parseInt(limit) >= digit - '0') ? BigInteger.ONE
				: BigInteger.ZERO;
	}

	BigInteger countUpTo(String limit, char head, char tail) {
		char first = limit.charAt(0);
		if (first > head) {
			return countStrobogrammatic(limit.length() - 2, true);
		} else if (first == head) {
			String bound = limit;
			while (bound != null && !bound.endsWith(tail + "")) {
				bound = decrease(bound, true);
			}

			if (bound != null && bound.charAt(0) == head) {
				return countStrobogrammatic(
						bound.substring(1, bound.length() - 1), true, true);
			}
		}
		return BigInteger.ZERO;
	}

	BigInteger countStrobogrammatic(int length, boolean allowLeadingZero) {
		if (allowLeadingZero && length2countCache.containsKey(length)) {
			return length2countCache.get(length);
		}

		BigInteger count;
		if (length == 0) {
			count = BigInteger.ONE;
		} else if (length == 1) {
			count = new BigInteger("3");
		} else {
			count = countStrobogrammatic(length - 2, true).multiply(
					new BigInteger(allowLeadingZero ? "5" : "4"));
		}

		if (allowLeadingZero) {
			length2countCache.put(length, count);
		}
		return count;
	}

	String decrease(String number, boolean maintainLength) {
		BigInteger result = new BigInteger(number).subtract(BigInteger.ONE);
		if (maintainLength) {
			if (result.compareTo(BigInteger.ZERO) < 0) {
				return null;
			} else {
				return String.format("%0" + number.length() + "d", result);
			}
		} else {
			return result.toString();
		}
	}
}
