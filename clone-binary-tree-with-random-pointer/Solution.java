import java.util.HashMap;
import java.util.Map;

// Definition for Node.
class Node {
    int val;
    Node left;
    Node right;
    Node random;

    Node() {
    }

    Node(int val) {
        this.val = val;
    }

    Node(int val, Node left, Node right, Node random) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.random = random;
    }
}

class NodeCopy {
    int val;
    NodeCopy left;
    NodeCopy right;
    NodeCopy random;

    NodeCopy() {
    }

    NodeCopy(int val) {
        this.val = val;
    }

    NodeCopy(int val, NodeCopy left, NodeCopy right, NodeCopy random) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.random = random;
    }
}

class Solution {
    public NodeCopy copyRandomBinaryTree(Node root) {
        Map<Node, NodeCopy> nodeToCopy = new HashMap<>();
        NodeCopy copy = search(nodeToCopy, root);

        setRandom(nodeToCopy, root, copy);

        return copy;
    }

    NodeCopy search(Map<Node, NodeCopy> nodeToCopy, Node node) {
        if (node == null) {
            return null;
        }

        NodeCopy copy = new NodeCopy(node.val);
        copy.left = search(nodeToCopy, node.left);
        copy.right = search(nodeToCopy, node.right);

        nodeToCopy.put(node, copy);

        return copy;
    }

    void setRandom(Map<Node, NodeCopy> nodeToCopy, Node node, NodeCopy copy) {
        if (node == null) {
            return;
        }

        copy.random = nodeToCopy.get(node.random);

        setRandom(nodeToCopy, node.left, copy.left);
        setRandom(nodeToCopy, node.right, copy.right);
    }
}