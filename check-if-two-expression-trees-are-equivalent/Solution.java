import java.util.HashMap;
import java.util.Map;

// Definition for a binary tree node.
class Node {
  char val;
  Node left;
  Node right;

  Node() {
    this.val = ' ';
  }

  Node(char val) {
    this.val = val;
  }

  Node(char val, Node left, Node right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}

class Solution {
  public boolean checkEquivalence(Node root1, Node root2) {
    Map<Character, Integer> variableToCount1 = new HashMap<>();
    search(variableToCount1, root1);

    Map<Character, Integer> variableToCount2 = new HashMap<>();
    search(variableToCount2, root2);

    return variableToCount1.equals(variableToCount2);
  }

  void search(Map<Character, Integer> variableToCount, Node node) {
    if (node.val == '+') {
      search(variableToCount, node.left);
      search(variableToCount, node.right);
    } else {
      variableToCount.put(node.val, variableToCount.getOrDefault(node.val, 0) + 1);
    }
  }
}
