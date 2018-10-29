import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solution {
	public int numUniqueEmails(String[] emails) {
		return Arrays.stream(emails).map(this::simplify).collect(Collectors.toSet()).size();
	}

	String simplify(String email) {
		String[] parts = email.split("@");
		return processPeriod(processPlus(parts[0])) + "@" + parts[1];
	}

	String processPlus(String localName) {
		int index = localName.indexOf('+');
		return (index >= 0) ? localName.substring(0, index) : localName;
	}

	String processPeriod(String localName) {
		return localName.replaceAll(Pattern.quote("."), "");
	}
}
