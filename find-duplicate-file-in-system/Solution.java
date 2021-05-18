import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public List<List<String>> findDuplicate(String[] paths) {
    Map<String, List<String>> contentToPaths = new HashMap<>();
    for (String path : paths) {
      String[] fields = path.split(" ");
      String directory = fields[0];
      for (int i = 1; i < fields.length; ++i) {
        int startIndex = fields[i].indexOf('(');
        String fileName = fields[i].substring(0, startIndex);
        String content = fields[i].substring(startIndex + 1, fields[i].length() - 1);

        if (!contentToPaths.containsKey(content)) {
          contentToPaths.put(content, new ArrayList<>());
        }
        contentToPaths.get(content).add(String.format("%s/%s", directory, fileName));
      }
    }

    return contentToPaths.values().stream()
        .filter(ps -> ps.size() >= 2)
        .collect(Collectors.toList());
  }
}
