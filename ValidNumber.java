public class ValidNumber {
	public boolean isNumber(String s) {
		s = s.trim();

		int indexE = s.indexOf('e');
		if (indexE >= 0) {
			return isDecimal(s.substring(0, indexE))
					&& isInteger(s.substring(indexE + 1));
		} else {
			return isDecimal(s);
		}
	}

	boolean isDecimal(String s) {
		s = removeSign(s);

		int indexPoint = s.indexOf('.');
		if (indexPoint >= 0) {
			if (s.length() == 1) {
				return false;
			}
			return (indexPoint == 0 || isInteger(s.substring(0, indexPoint)))
					&& (indexPoint == s.length() - 1 || isIntegerWithoutSign(s
							.substring(indexPoint + 1)));
		} else {
			return isInteger(s);
		}
	}

	boolean isInteger(String s) {
		return isIntegerWithoutSign(removeSign(s));
	}

	String removeSign(String s) {
		if (s.startsWith("+") || s.startsWith("-")) {
			s = s.substring(1);
		}
		return s;
	}

	boolean isIntegerWithoutSign(String s) {
		if (s.isEmpty()) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
