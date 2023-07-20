import java.util.ArrayList;
import java.util.List;

class Solution {
  static final String NULL = "#";

  public boolean isValidSerialization(String preorder) {
    List<String> values = new ArrayList<>();
    for (String value : preorder.split(",")) {
      values.add(value);

      while (canReduce(values)) {
        reduce(values);
      }
    }

    return values.size() == 1 && values.get(values.size() - 1).equals(NULL);
  }

  boolean canReduce(List<String> stack) {
    int size = stack.size();
    if (size < 3) {
      return false;
    }

    return !stack.get(size - 3).equals(NULL)
        && stack.get(size - 2).equals(NULL)
        && stack.get(size - 1).equals(NULL);
  }

  void reduce(List<String> values) {
    values.remove(values.size() - 1);
    values.remove(values.size() - 1);
    values.remove(values.size() - 1);
    values.add(NULL);
  }
}
