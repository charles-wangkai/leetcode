import java.util.Stack;

class Solution {
  public String simplifyPath(String path) {
    String[] parts = path.split("/");
    Stack<String> directories = new Stack<>();
    for (String part : parts) {
      if (part.equals("..")) {
        if (!directories.empty()) {
          directories.pop();
        }
      } else if (!part.equals(".") && !part.isEmpty()) {
        directories.push(part);
      }
    }

    return String.format("/%s", String.join("/", directories));
  }
}
