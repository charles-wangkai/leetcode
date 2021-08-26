import java.util.Stack;

class Solution {
  static final String NULL = "#";

  public boolean isValidSerialization(String preorder) {
    Stack<String> stack = new Stack<>();
    for (String value : preorder.split(",")) {
      stack.push(value);

      while (canReduce(stack)) {
        reduce(stack);
      }
    }

    return stack.size() == 1 && stack.peek().equals(NULL);
  }

  boolean canReduce(Stack<String> stack) {
    int size = stack.size();
    if (size < 3) {
      return false;
    }

    return !stack.get(size - 3).equals(NULL)
        && stack.get(size - 2).equals(NULL)
        && stack.get(size - 1).equals(NULL);
  }

  void reduce(Stack<String> stack) {
    stack.pop();
    stack.pop();
    stack.pop();
    stack.push(NULL);
  }
}
