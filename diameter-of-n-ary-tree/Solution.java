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
    int maxDistance;

    public int diameter(Node root) {
        maxDistance = 0;
        search(root, 0);

        return maxDistance;
    }

    int search(Node node, int rootDistance) {
        maxDistance = Math.max(maxDistance, rootDistance);

        int maxChildDepth = 1;
        int nextRootDistance = rootDistance + 1;
        for (Node child : node.children) {
            int subResult = search(child, nextRootDistance);

            maxChildDepth = Math.max(maxChildDepth, 1 + subResult);
            nextRootDistance = Math.max(nextRootDistance, maxChildDepth);
        }

        return maxChildDepth;
    }
}