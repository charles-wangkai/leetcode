import java.util.List;

class Solution {
  public int maxLength(List<String> arr) {
    return search(arr, new boolean[26], 0, 0);
  }

  int search(List<String> arr, boolean[] used, int index, int length) {
    if (index == arr.size()) {
      return length;
    }

    int result = search(arr, used, index + 1, length);

    String s = arr.get(index);
    if (s.chars().distinct().count() == s.length() && s.chars().allMatch(c -> !used[c - 'a'])) {
      for (char c : s.toCharArray()) {
        used[c - 'a'] = true;
      }

      result = Math.max(result, search(arr, used, index + 1, length + s.length()));

      for (char c : s.toCharArray()) {
        used[c - 'a'] = false;
      }
    }

    return result;
  }
}
