import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  static final int ALPHABET_SIZE = 26;
  static final int MODULUS = 1_000_000_007;

  public String longestDupSubstring(String s) {
    String result = "";
    int lower = 1;
    int upper = s.length();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      String found = find(s, middle);
      if (found != null) {
        result = found;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  static String find(String s, int length) {
    int hash = 0;
    for (int i = 0; i < length; ++i) {
      hash = addMod(multiplyMod(hash, ALPHABET_SIZE), s.charAt(i) - 'a');
    }

    Map<Integer, List<Integer>> hashToBeginIndices = new HashMap<>();
    putValue(hashToBeginIndices, hash, 0);

    int highestPlaceValue = 1;
    for (int i = 0; i < length - 1; ++i) {
      highestPlaceValue = multiplyMod(highestPlaceValue, ALPHABET_SIZE);
    }

    for (int beginIndex = 1; beginIndex + length <= s.length(); ++beginIndex) {
      hash =
          addMod(
              multiplyMod(
                  subtractMod(hash, multiplyMod(s.charAt(beginIndex - 1) - 'a', highestPlaceValue)),
                  ALPHABET_SIZE),
              s.charAt(beginIndex + length - 1) - 'a');

      if (hashToBeginIndices.containsKey(hash)) {
        String found = s.substring(beginIndex, beginIndex + length);
        if (hashToBeginIndices.get(hash).stream()
            .anyMatch(bi -> s.substring(bi, bi + length).equals(found))) {
          return found;
        }
      }

      putValue(hashToBeginIndices, hash, beginIndex);
    }

    return null;
  }

  static void putValue(Map<Integer, List<Integer>> hashToBeginIndices, int hash, int beginIndex) {
    if (!hashToBeginIndices.containsKey(hash)) {
      hashToBeginIndices.put(hash, new ArrayList<>());
    }

    hashToBeginIndices.get(hash).add(beginIndex);
  }

  static int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  static int subtractMod(int x, int y) {
    return (x - y + MODULUS) % MODULUS;
  }

  static int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }
}
