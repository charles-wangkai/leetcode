import java.util.ArrayList;
import java.util.List;

class Solution {
  static final char[] LETTERS = {'a', 'b', 'c'};

  public String getHappyString(int n, int k) {
    List<String> happys = new ArrayList<>();
    search(happys, new char[n], 0);

    return (k <= happys.size()) ? happys.get(k - 1) : "";
  }

  void search(List<String> happys, char[] letters, int index) {
    if (index == letters.length) {
      happys.add(new String(letters));

      return;
    }

    for (char letter : LETTERS) {
      if (index == 0 || letter != letters[index - 1]) {
        letters[index] = letter;
        search(happys, letters, index + 1);
      }
    }
  }
}