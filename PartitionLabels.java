import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartitionLabels {
	public List<Integer> partitionLabels(String S) {
		Map<Character, Integer> ch2lastIndex = new HashMap<Character, Integer>();
		for (int i = 0; i < S.length(); i++) {
			ch2lastIndex.put(S.charAt(i), i);
		}

		List<Integer> sizes = new ArrayList<Integer>();
		int beginIndex = 0;
		int endIndex = -1;
		for (int i = 0; i < S.length(); i++) {
			endIndex = Math.max(endIndex, ch2lastIndex.get(S.charAt(i)));

			if (endIndex == i) {
				sizes.add(endIndex - beginIndex + 1);
				beginIndex = endIndex + 1;
			}
		}
		return sizes;
	}
}
