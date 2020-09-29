import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
	public boolean wordBreak(String s, List<String> wordDict) {
		Set<String> words = new HashSet<>(wordDict);

		boolean[] separables = new boolean[s.length() + 1];
		separables[0] = true;
		for (int i = 0; i < s.length(); ++i) {
			int i_ = i;
			separables[i + 1] = IntStream.rangeClosed(0, i)
					.anyMatch(j -> separables[j] && words.contains(s.substring(j, i_ + 1)));
		}

		return separables[s.length()];
	}
}
