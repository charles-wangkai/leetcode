import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
  public boolean canRemoveAll(int[] types, int[] colors, int[] groupSize) {
    Deque<Segment> stack = new ArrayDeque<>();
    for (int i = 0; i < types.length; ++i) {
      if (!stack.isEmpty() && types[i] == stack.peek().type && colors[i] == stack.peek().color) {
        ++stack.peek().length;
      } else {
        stack.push(new Segment(types[i], colors[i], 1));
      }

      if (stack.peek().length == groupSize[stack.peek().type]) {
        stack.pop();
      }
    }

    return stack.isEmpty();
  }
}

class Segment {
  int type;
  int color;
  int length;

  Segment(int type, int color, int length) {
    this.type = type;
    this.color = color;
    this.length = length;
  }
}