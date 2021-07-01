import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<Integer> grayCode(int n) {
    List<Integer> sequence = new ArrayList<>();
    search(sequence, new int[n], 0);

    return sequence;
  }

  void search(List<Integer> sequence, int[] bits, int index) {
    if (index == bits.length) {
      sequence.add(convertToInt(bits));

      return;
    }

    search(sequence, bits, index + 1);
    bits[index] = 1 - bits[index];
    search(sequence, bits, index + 1);
  }

  int convertToInt(int[] bits) {
    int result = 0;
    for (int bit : bits) {
      result = result * 2 + bit;
    }

    return result;
  }
}
