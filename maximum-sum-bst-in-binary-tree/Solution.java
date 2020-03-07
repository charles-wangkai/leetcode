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
    int maxSum;

    public int maxSumBST(TreeNode root) {
        maxSum = 0;
        search(root);

        return maxSum;
    }

    Element search(TreeNode node) {
        if (node == null) {
            return new Element(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        Element leftSubResult = search(node.left);
        Element rightSubResult = search(node.right);

        boolean bst = leftSubResult.bst && rightSubResult.bst && node.val > leftSubResult.maxValue
                && node.val < rightSubResult.minValue;
        int minValue = Math.min(node.val, leftSubResult.minValue);
        int maxValue = Math.max(node.val, rightSubResult.maxValue);
        int sum = node.val + leftSubResult.sum + rightSubResult.sum;

        if (bst) {
            maxSum = Math.max(maxSum, sum);
        }

        return new Element(bst, minValue, maxValue, sum);
    }
}

class Element {
    boolean bst;
    int minValue;
    int maxValue;
    int sum;

    Element(boolean bst, int minValue, int maxValue, int sum) {
        this.bst = bst;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.sum = sum;
    }
}