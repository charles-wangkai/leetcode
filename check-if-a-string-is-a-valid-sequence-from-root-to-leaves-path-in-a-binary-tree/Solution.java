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
    public boolean isValidSequence(TreeNode root, int[] arr) {
        return search(root, arr, 0);
    }

    boolean search(TreeNode node, int[] arr, int index) {
        if (node == null || index == arr.length || arr[index] != node.val) {
            return false;
        }

        if (node.left == null && node.right == null) {
            return index == arr.length - 1;
        }

        return search(node.left, arr, index + 1) || search(node.right, arr, index + 1);
    }
}