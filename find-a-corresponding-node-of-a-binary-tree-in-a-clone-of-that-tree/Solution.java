import java.util.ArrayList;
import java.util.List;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class Solution {
    List<Boolean> leftOrRightPath;

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        leftOrRightPath = null;
        search(target, original, new ArrayList<Boolean>());

        return find(cloned, 0);
    }

    void search(TreeNode target, TreeNode node, List<Boolean> path) {
        if (node == null) {
            return;
        }

        if (node == target) {
            leftOrRightPath = new ArrayList<>(path);
        }

        path.add(true);
        search(target, node.left, path);
        path.remove(path.size() - 1);

        path.add(false);
        search(target, node.right, path);
        path.remove(path.size() - 1);
    }

    TreeNode find(TreeNode node, int index) {
        if (index == leftOrRightPath.size()) {
            return node;
        }

        if (leftOrRightPath.get(index)) {
            return find(node.left, index + 1);
        } else {
            return find(node.right, index + 1);
        }
    }
}