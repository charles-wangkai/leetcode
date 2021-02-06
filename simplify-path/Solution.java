import java.util.Stack;

class Solution {
  public String simplifyPath(String path) {
    path += "/";
    Stack<String> directories = new Stack<>();
    int prevSlashIndex = -1;
    for (int i = 0; i < path.length(); ++i) {
      if (path.charAt(i) == '/') {
        String directory = path.substring(prevSlashIndex + 1, i);
        if (directory.equals("..")) {
          if (!directories.isEmpty()) {
            directories.pop();
          }
        } else if (!directory.equals(".") && !directory.isEmpty()) {
          directories.push(directory);
        }
        prevSlashIndex = i;
      }
    }

    return "/" + String.join("/", directories);
  }
}
