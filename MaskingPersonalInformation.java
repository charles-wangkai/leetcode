import java.util.stream.Collectors;

public class MaskingPersonalInformation {
	public String maskPII(String S) {
		if (isEmail(S)) {
			return maskEmail(S);
		} else {
			return maskPhone(S);
		}
	}

	boolean isEmail(String S) {
		return S.contains("@");
	}

	String maskEmail(String S) {
		String lowerS = S.toLowerCase();
		int atIndex = lowerS.indexOf('@');

		return String.format("%c*****%c%s", lowerS.charAt(0), lowerS.charAt(atIndex - 1), lowerS.substring(atIndex));
	}

	String maskPhone(String S) {
		String digits = S.chars().filter(Character::isDigit).mapToObj(digit -> String.valueOf((char) digit))
				.collect(Collectors.joining());

		String result = String.format("***-***-%s", digits.substring(digits.length() - 4, digits.length()));

		int countryCodeLength = digits.length() - 10;
		if (countryCodeLength > 0) {
			result = '-' + result;
			for (int i = 0; i < countryCodeLength; i++) {
				result = '*' + result;
			}
			result = '+' + result;
		}

		return result;
	}
}
