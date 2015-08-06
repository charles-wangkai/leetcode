import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StrobogrammaticNumber_II {
	public List<String> findStrobogrammatic(int n) {
		return searchStrobogrammatic(n)
				.stream()
				.filter(oneResult -> oneResult.equals("0")
						|| !oneResult.startsWith("0"))
				.collect(Collectors.toList());
	}

	public List<String> searchStrobogrammatic(int n) {
		List<String> result = new ArrayList<String>();
		if (n == 0) {
			result.add("");
		} else if (n == 1) {
			result.add("0");
			result.add("1");
			result.add("8");
		} else {
			List<String> subResult = searchStrobogrammatic(n - 2);
			for (String oneSubResult : subResult) {
				result.add("0" + oneSubResult + "0");
				result.add("1" + oneSubResult + "1");
				result.add("8" + oneSubResult + "8");
				result.add("6" + oneSubResult + "9");
				result.add("9" + oneSubResult + "6");
			}
		}
		return result;
	}
}
