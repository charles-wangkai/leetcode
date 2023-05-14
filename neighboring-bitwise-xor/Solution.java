import java.util.Arrays;

class Solution {
  public boolean doesValidArrayExist(int[] derived) {
    return Arrays.stream(derived).reduce((acc, x) -> acc ^ x).getAsInt() == 0;
  }
}
