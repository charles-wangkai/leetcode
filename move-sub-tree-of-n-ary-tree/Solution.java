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
    public Node moveSubTree(Node root, Node p, Node q) {
        if (q.children.stream().anyMatch(child -> child == p)) {
            return root;
        }

        List<Node> pathP = findPath(root, p);
        List<Node> pathQ = findPath(root, q);
        Node parentP = (pathP.size() == 1) ? null : pathP.get(pathP.size() - 2);
        if (isInPath(pathQ, p)) {
            pathQ.get(pathQ.size() - 2).children.remove(q);

            if (parentP != null) {
                parentP.children.set(parentP.children.indexOf(p), q);
            }
        } else {
            parentP.children.remove(p);
        }
        q.children.add(p);

        return (root == p) ? q : root;
    }

    List<Node> findPath(Node root, Node node) {
        List<Node> path = new ArrayList<>();
        search(path, node, root);

        return path;
    }

    void search(List<Node> path, Node node, Node current) {
        path.add(current);

        for (Node child : current.children) {
            search(path, node, child);
        }

        if (path.get(path.size() - 1) != node) {
            path.remove(path.size() - 1);
        }
    }

    boolean isInPath(List<Node> path, Node node) {
        return path.stream().anyMatch(x -> x == node);
    }
}