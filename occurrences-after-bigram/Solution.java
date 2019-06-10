import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
	public String[] findOcurrences(String text, String first, String second) {
		Matcher m = Pattern.compile(String.format("%s %s (\\w+)", first, second)).matcher(text);

		List<String> result = new ArrayList<>();
		if (m.find()) {
			do {
				result.add(m.group(1));
			} while (m.find(m.start(1)));
		}
		return result.toArray(new String[0]);
	}
}
