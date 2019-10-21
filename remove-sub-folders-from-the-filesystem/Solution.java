import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public List<String> removeSubfolders(String[] folder) {
		Arrays.sort(folder);

		List<String> result = new ArrayList<>();
		for (String f : folder) {
			if (result.isEmpty() || !(f + "/").startsWith(result.get(result.size() - 1) + "/")) {
				result.add(f);
			}
		}

		return result;
	}
}
