import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Definition for a Node.
class Node {
  public int val;
  public Node left;
  public Node right;
  public Node parent;
}

class Solution {
  public Node lowestCommonAncestor(Node p, Node q) {
    List<Node> pPath = buildPath(p);
    List<Node> qPath = buildPath(q);

    int index = 0;
    while (index + 1 != pPath.size()
        && index + 1 != qPath.size()
        && pPath.get(index + 1) == qPath.get(index + 1)) {
      ++index;
    }

    return pPath.get(index);
  }

  List<Node> buildPath(Node node) {
    List<Node> result = new ArrayList<>();
    while (node != null) {
      result.add(node);
      node = node.parent;
    }
    Collections.reverse(result);

    return result;
  }
}
