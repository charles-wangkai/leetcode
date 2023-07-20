import java.util.ArrayList;
import java.util.List;

public class Solution {
  public List<String> removeComments(String[] source) {
    List<String> result = new ArrayList<>();
    boolean inBlockComment = false;
    for (String line : source) {
      if (inBlockComment) {
        int index = line.indexOf("*/");

        if (index < 0) {
          continue;
        }

        line = result.remove(result.size() - 1) + line.substring(index + 2);
        inBlockComment = false;
      }

      while (true) {
        int lineBeginIndex = line.indexOf("//");
        int blockBeginIndex = line.indexOf("/*");

        boolean lineOverBlock;
        if (lineBeginIndex >= 0) {
          if (blockBeginIndex >= 0) {
            lineOverBlock = lineBeginIndex < blockBeginIndex;
          } else {
            lineOverBlock = true;
          }
        } else {
          if (blockBeginIndex >= 0) {
            lineOverBlock = false;
          } else {
            break;
          }
        }

        if (lineOverBlock) {
          line = line.substring(0, lineBeginIndex);
          break;
        } else {
          int blockEndIndex = line.indexOf("*/", blockBeginIndex + 2);
          if (blockEndIndex >= 0) {
            line = line.substring(0, blockBeginIndex) + line.substring(blockEndIndex + 2);
          } else {
            line = line.substring(0, blockBeginIndex);
            inBlockComment = true;
            break;
          }
        }
      }

      if (!line.isEmpty()) {
        result.add(line);
      }
    }
    return result;
  }
}
