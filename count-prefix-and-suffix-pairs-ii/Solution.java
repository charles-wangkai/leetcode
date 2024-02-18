import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int BASE_1 = 31;
  static final int BASE_2 = 37;
  static final int MODULUS = 1_000_000_007;

  public long countPrefixSuffixPairs(String[] words) {
    long result = 0;
    Map<State, Integer> stateToCount = new HashMap<>();
    for (int i = words.length - 1; i >= 0; --i) {
      int hash1 = 0;
      int hash2 = 0;
      for (char c : words[i].toCharArray()) {
        hash1 = addMod(multiplyMod(hash1, BASE_1), c - 'a');
        hash2 = addMod(multiplyMod(hash2, BASE_2), c - 'a');
      }

      result += stateToCount.getOrDefault(new State(words[i].length(), hash1, hash2), 0);

      int prefixHash1 = 0;
      int prefixHash2 = 0;
      int suffixHash1 = 0;
      int suffixHash2 = 0;
      int placeValue1 = 1;
      int placeValue2 = 1;
      for (int length = 1; length <= words[i].length(); ++length) {
        prefixHash1 = addMod(multiplyMod(prefixHash1, BASE_1), (words[i].charAt(length - 1) - 'a'));
        prefixHash2 = addMod(multiplyMod(prefixHash2, BASE_2), (words[i].charAt(length - 1) - 'a'));

        suffixHash1 =
            addMod(
                suffixHash1,
                multiplyMod(placeValue1, words[i].charAt(words[i].length() - length) - 'a'));
        suffixHash2 =
            addMod(
                suffixHash2,
                multiplyMod(placeValue2, words[i].charAt(words[i].length() - length) - 'a'));

        if (prefixHash1 == suffixHash1 && prefixHash2 == suffixHash2) {
          State state = new State(length, prefixHash1, prefixHash2);
          stateToCount.put(state, stateToCount.getOrDefault(state, 0) + 1);
        }

        placeValue1 = multiplyMod(placeValue1, BASE_1);
        placeValue2 = multiplyMod(placeValue2, BASE_2);
      }
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}

record State(int length, int hash1, int hash2) {}
