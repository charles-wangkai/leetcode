import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
  public int findBlackPixel(char[][] picture, int N) {
    List<String> lines = new ArrayList<>();
    Map<String, Integer> line2index = new HashMap<>();
    Map<String, Integer> line2blackNum = new HashMap<>();

    int row = picture.length;
    int col = picture[0].length;

    for (int r = 0; r < row; r++) {
      String line = new String(picture[r]);

      lines.add(line);

      if (!line2index.containsKey(line)) {
        line2index.put(line, line2index.size());

        line2blackNum.put(line, (int) line.chars().filter(c -> c == 'B').count());
      }
    }

    int result = 0;
    for (int c = 0; c < col; c++) {
      boolean valid = true;
      int index = -1;
      int blackNumInRow = -1;
      int blackNumInColumn = 0;
      for (int r = 0; r < row; r++) {
        if (picture[r][c] == 'B') {
          if (index < 0) {
            index = line2index.get(lines.get(r));
            blackNumInRow = line2blackNum.get(lines.get(r));

            if (blackNumInRow != N) {
              valid = false;
              break;
            }
          } else if (line2index.get(lines.get(r)) != index) {
            valid = false;
            break;
          }

          blackNumInColumn++;
        }
      }

      if (valid && blackNumInColumn == blackNumInRow) {
        result += blackNumInColumn;
      }
    }
    return result;
  }
}
