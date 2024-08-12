import java.util.List;

class Solution {
  public int finalPositionOfSnake(int n, List<String> commands) {
    int result = 0;
    for (String command : commands) {
      if (command.equals("UP")) {
        result -= n;
      } else if (command.equals("RIGHT")) {
        ++result;
      } else if (command.equals("DOWN")) {
        result += n;
      } else {
        --result;
      }
    }

    return result;
  }
}