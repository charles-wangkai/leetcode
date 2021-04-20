import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Definition for a Node.
class Node {
  public int val;
  public List<Node> children;

  public Node() {}

  public Node(int _val) {
    val = _val;
  }

  public Node(int _val, List<Node> _children) {
    val = _val;
    children = _children;
  }
}

class Solution {
  public List<Integer> preorder(Node root) {
    if (root == null) {
      return List.of();
    }

    List<Integer> result = new ArrayList<>();
    Stack<Node> stack = new Stack<>();
    stack.push(root);
    while (!stack.empty()) {
      Node top = stack.pop();
      result.add(top.val);
      for (int i = top.children.size() - 1; i >= 0; --i) {
        stack.push(top.children.get(i));
      }
    }

    return result;
  }
}
