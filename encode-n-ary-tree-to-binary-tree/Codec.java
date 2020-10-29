import java.util.LinkedList;
import java.util.List;

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

// Definition for a binary tree node.
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

class Codec {
  // Encodes an n-ary tree to a binary tree.
  public TreeNode encode(Node root) {
    if (root == null) {
      return null;
    }

    TreeNode rootTreeNode = new TreeNode(root.val);
    for (Node child : root.children) {
      TreeNode childTreeNode = encode(child);

      childTreeNode.right = rootTreeNode.left;
      rootTreeNode.left = childTreeNode;
    }
    return rootTreeNode;
  }

  // Decodes your binary tree to an n-ary tree.
  public Node decode(TreeNode root) {
    if (root == null) {
      return null;
    }

    Node rootNode = new Node(root.val, new LinkedList<>());
    for (TreeNode child = root.left; child != null; child = child.right) {
      rootNode.children.add(0, decode(child));
    }
    return rootNode;
  }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(root));
