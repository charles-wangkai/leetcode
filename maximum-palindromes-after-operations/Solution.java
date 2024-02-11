import java.util.Arrays;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public int maxPalindromesAfterOperations(String[] words) {
    int[] counts = new int[ALPHABET_SIZE];
    for (String word : words) {
      for (char c : word.toCharArray()) {
        ++counts[c - 'a'];
      }
    }

    int pairNum = Arrays.stream(counts).map(count -> count / 2).sum();
    int singleNum = Arrays.stream(counts).map(count -> count % 2).sum();

    int result = 0;
    int[] sortedLengths = Arrays.stream(words).mapToInt(String::length).sorted().toArray();
    for (int length : sortedLengths) {
      if (pairNum >= length / 2) {
        pairNum -= length / 2;
        if (length % 2 == 1) {
          if (singleNum != 0) {
            --singleNum;
          } else {
            --pairNum;
            ++singleNum;
          }
        }

        ++result;
      }
    }

    return result;
  }
}