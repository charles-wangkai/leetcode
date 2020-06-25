import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {
        children = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
}

class Solution {
    public Node cloneTree(Node root) {
        if (root == null) {
            return null;
        }

        Map<Node, Node> nodeToClone = new HashMap<>();
        clone(nodeToClone, root);

        return nodeToClone.get(root);
    }

    void clone(Map<Node, Node> nodeToClone, Node node) {
        Node clone = new Node(node.val);
        nodeToClone.put(node, clone);
        for (Node child : node.children) {
            clone(nodeToClone, child);
            clone.children.add(nodeToClone.get(child));
        }
    }
}