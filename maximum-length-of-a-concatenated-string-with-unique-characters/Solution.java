import java.util.List;

public class Solution {
	public int maxLength(List<String> arr) {
		return search(arr, new boolean[26], 0, 0);
	}

	int search(List<String> arr, boolean[] used, int index, int length) {
		if (index == arr.size()) {
			return length;
		}

		int result = search(arr, used, index + 1, length);

		String s = arr.get(index);
		if (s.chars().distinct().count() == s.length() && s.chars().allMatch(ch -> !used[ch - 'a'])) {
			for (char ch : s.toCharArray()) {
				used[ch - 'a'] = true;
			}

			result = Math.max(result, search(arr, used, index + 1, length + s.length()));

			for (char ch : s.toCharArray()) {
				used[ch - 'a'] = false;
			}
		}

		return result;
	}
}
