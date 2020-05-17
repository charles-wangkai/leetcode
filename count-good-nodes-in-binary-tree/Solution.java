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
    public int goodNodes(TreeNode root) {
        return search(root, Integer.MIN_VALUE);
    }

    int search(TreeNode node, int maxValue) {
        if (node == null) {
            return 0;
        }

        return ((node.val >= maxValue) ? 1 : 0) + search(node.left, Math.max(maxValue, node.val))
                + search(node.right, Math.max(maxValue, node.val));
    }
}