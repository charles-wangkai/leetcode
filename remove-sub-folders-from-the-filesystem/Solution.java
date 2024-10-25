import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public List<String> removeSubfolders(String[] folder) {
    Arrays.sort(folder);

    List<String> result = new ArrayList<>();
    for (String f : folder) {
      if (result.isEmpty() || !f.startsWith(result.getLast() + "/")) {
        result.add(f);
      }
    }

    return result;
  }
}
