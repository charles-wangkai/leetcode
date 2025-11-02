import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public String lexPalindromicPermutation(String s, String target) {
    int n = s.length();

    int[] counts = new int[26];
    for (char c : s.toCharArray()) {
      ++counts[c - 'a'];
    }

    char[] letters = new char[n];

    int[] oddCountIndices =
        IntStream.range(0, counts.length).filter(i -> counts[i] % 2 == 1).toArray();
    if (n % 2 == 1) {
      if (oddCountIndices.length != 1) {
        return "";
      }

      letters[n / 2] = (char) (oddCountIndices[0] + 'a');
      --counts[oddCountIndices[0]];
    } else if (oddCountIndices.length != 0) {
      return "";
    }

    List<String> candidates = new ArrayList<>();
    search(candidates, target, letters, counts, 0, letters.length - 1);

    return candidates.stream()
        .filter(candidate -> candidate.compareTo(target) > 0)
        .min(Comparator.naturalOrder())
        .orElse("");
  }

  void search(
      List<String> candidates,
      String target,
      char[] letters,
      int[] counts,
      int leftIndex,
      int rightIndex) {
    if (leftIndex >= rightIndex) {
      candidates.add(new String(letters));

      return;
    }

    for (int i = target.charAt(leftIndex) - 'a'; i < counts.length; ++i) {
      if (counts[i] != 0) {
        letters[leftIndex] = (char) (i + 'a');
        letters[rightIndex] = (char) (i + 'a');
        counts[i] -= 2;

        if (i == target.charAt(leftIndex) - 'a') {
          search(candidates, target, letters, counts, leftIndex + 1, rightIndex - 1);
        } else {
          int[] rests = counts.clone();
          int index = 0;
          for (int l = leftIndex + 1, r = rightIndex - 1; l < r; ++l, --r) {
            while (rests[index] == 0) {
              ++index;
            }

            letters[l] = (char) (index + 'a');
            letters[r] = (char) (index + 'a');
            rests[index] -= 2;
          }

          candidates.add(new String(letters));
        }

        counts[i] += 2;

        if (i != target.charAt(leftIndex) - 'a') {
          break;
        }
      }
    }
  }
}