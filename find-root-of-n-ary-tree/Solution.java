import java.util.ArrayList;
import java.util.List;

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
    public Node findRoot(List<Node> tree) {
        int rootValue = 0;
        for (Node node : tree) {
            rootValue ^= node.val;

            for (Node child : node.children) {
                rootValue ^= child.val;
            }
        }

        int rootValue_ = rootValue;
        return tree.stream().filter(node -> node.val == rootValue_).findAny().get();
    }
}