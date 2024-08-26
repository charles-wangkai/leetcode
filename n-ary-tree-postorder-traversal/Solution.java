import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// Definition for a Node.
class Node {
  public int val;
  public List<Node> children;

  public Node() {}

  public Node(int _val, List<Node> _children) {
    val = _val;
    children = _children;
  }
}

class Solution {
  public List<Integer> postorder(Node root) {
    if (root == null) {
      return List.of();
    }

    List<Integer> result = new ArrayList<>();
    Deque<Object> stack = new ArrayDeque<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      Object top = stack.pop();
      if (top instanceof Node node) {
        stack.push(node.val);
        for (int i = node.children.size() - 1; i >= 0; --i) {
          stack.push(node.children.get(i));
        }
      } else {
        result.add((int) top);
      }
    }

    return result;
  }
}
