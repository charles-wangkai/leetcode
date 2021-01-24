import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minCharacters(String a, String b) {
    int[] aLetterCounts = buildLetterCounts(a);
    int[] bLetterCounts = buildLetterCounts(b);

    return Math.min(
        Math.min(
            computeOperNumForLess(aLetterCounts, bLetterCounts),
            computeOperNumForLess(bLetterCounts, aLetterCounts)),
        computeOperNumForEqual(aLetterCounts, bLetterCounts));
  }

  int[] buildLetterCounts(String s) {
    int[] letterCounts = new int[26];
    for (char ch : s.toCharArray()) {
      ++letterCounts[ch - 'a'];
    }

    return letterCounts;
  }

  int computeOperNumForLess(int[] letterCounts1, int[] letterCounts2) {
    int length1 = Arrays.stream(letterCounts1).sum();
    int length2 = Arrays.stream(letterCounts2).sum();

    int result = Integer.MAX_VALUE;
    int sum1 = 0;
    int sum2 = length2;
    for (int i = 0; i < letterCounts1.length - 1; ++i) {
      sum1 += letterCounts1[i];
      sum2 -= letterCounts2[i];

      result = Math.min(result, (length1 - sum1) + (length2 - sum2));
    }

    return result;
  }

  int computeOperNumForEqual(int[] letterCounts1, int[] letterCounts2) {
    int length1 = Arrays.stream(letterCounts1).sum();
    int length2 = Arrays.stream(letterCounts2).sum();

    return IntStream.range(0, letterCounts1.length)
        .map(i -> (length1 - letterCounts1[i]) + (length2 - letterCounts2[i]))
        .min()
        .getAsInt();
  }
}
