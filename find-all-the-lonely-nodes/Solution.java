import java.util.ArrayList;
import java.util.List;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<Integer> getLonelyNodes(TreeNode root) {
        List<Integer> lonelyValues = new ArrayList<>();
        search(lonelyValues, root);

        return lonelyValues;
    }

    void search(List<Integer> lonelyValues, TreeNode node) {
        if (node.left != null) {
            if (node.right == null) {
                lonelyValues.add(node.left.val);
            }

            search(lonelyValues, node.left);
        }

        if (node.right != null) {
            if (node.left == null) {
                lonelyValues.add(node.right.val);
            }

            search(lonelyValues, node.right);
        }
    }
}