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
    int maxLength;

    public int longestZigZag(TreeNode root) {
        maxLength = 0;
        search(root);

        return maxLength;
    }

    Element search(TreeNode node) {
        int leftLength = (node.left != null) ? (1 + search(node.left).rightLength) : 0;
        int rightLength = (node.right != null) ? (1 + search(node.right).leftLength) : 0;
        maxLength = Math.max(maxLength, Math.max(leftLength, rightLength));

        return new Element(leftLength, rightLength);
    }
}

class Element {
    int leftLength;
    int rightLength;

    Element(int leftLength, int rightLength) {
        this.leftLength = leftLength;
        this.rightLength = rightLength;
    }
}