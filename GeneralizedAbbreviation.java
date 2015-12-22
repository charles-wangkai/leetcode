import java.util.ArrayList;
import java.util.List;

public class GeneralizedAbbreviation {
	public List<String> generateAbbreviations(String word) {
		List<String> result = new ArrayList<String>();
		if (word.isEmpty()) {
			result.add("");
		} else {
			for (String abbreviation : generateAbbreviations(word.substring(1))) {
				result.add(word.charAt(0) + abbreviation);

				int leadingDigitLength = searchLeadingDigitLength(abbreviation);
				if (leadingDigitLength == 0) {
					result.add("1" + abbreviation);
				} else {
					result.add((1 + Integer.parseInt(abbreviation.substring(0, leadingDigitLength)))
							+ abbreviation.substring(leadingDigitLength));
				}
			}
		}
		return result;
	}

	int searchLeadingDigitLength(String str) {
		int leadingDigitLength = 0;
		while (leadingDigitLength < str.length() && Character.isDigit(str.charAt(leadingDigitLength))) {
			leadingDigitLength++;
		}
		return leadingDigitLength;
	}
}
