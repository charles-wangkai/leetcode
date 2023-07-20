import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public String simplifyPath(String path) {
    String[] parts = path.split("/");
    Deque<String> directories = new ArrayDeque<>();
    for (String part : parts) {
      if (part.equals("..")) {
        if (!directories.isEmpty()) {
          directories.pop();
        }
      } else if (!part.equals(".") && !part.isEmpty()) {
        directories.push(part);
      }
    }

    return String.format("/%s", String.join("/", directories));
  }
}
