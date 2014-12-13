import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
	public List<List<String>> partition(String s) {
		@SuppressWarnings("unchecked")
		List<List<String>>[] partitions = new List[s.length() + 1];
		for (int i = 0; i < partitions.length; i++) {
			partitions[i] = new ArrayList<List<String>>();
			if (i == 0) {
				partitions[i].add(new ArrayList<String>());
				continue;
			}
			for (int j = 1; j <= i; j++) {
				String last = s.substring(i - j, i);
				if (isPalindrome(last)) {
					for (List<String> p : partitions[i - j]) {
						List<String> part = new ArrayList<String>(p);
						part.add(last);
						partitions[i].add(part);
					}
				}
			}
		}
		return partitions[s.length()];
	}

	boolean isPalindrome(String s) {
		for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
		}
		return true;
	}
}
