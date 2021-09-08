import java.util.Arrays;

class Solution {
  static final int MODULUS = 26;

  public String shiftingLetters(String s, int[] shifts) {
    int currentShift = Arrays.stream(shifts).reduce(this::addMod).getAsInt();

    char[] result = new char[s.length()];
    for (int i = 0; i < result.length; ++i) {
      result[i] = (char) (addMod(s.charAt(i) - 'a', currentShift) + 'a');
      currentShift = addMod(currentShift, -shifts[i]);
    }

    return new String(result);
  }

  int addMod(int x, int y) {
    return (x + y % MODULUS + MODULUS) % MODULUS;
  }
}
