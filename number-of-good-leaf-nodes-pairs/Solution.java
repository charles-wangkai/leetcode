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
    int pairNum;

    public int countPairs(TreeNode root, int distance) {
        if (distance == 1) {
            return 0;
        }

        pairNum = 0;
        search(distance, root);

        return pairNum;
    }

    int[] search(int distance, TreeNode node) {
        int[] leafCounts = new int[distance - 1];

        if (node != null) {
            int[] leftSubResult = search(distance, node.left);
            int[] rightSubResult = search(distance, node.right);

            for (int i = 0; i < leftSubResult.length; ++i) {
                for (int j = 0; j < rightSubResult.length; ++j) {
                    if (i + j + 2 <= distance) {
                        pairNum += leftSubResult[i] * rightSubResult[j];
                    }
                }
            }

            if (node.left == null && node.right == null) {
                ++leafCounts[0];
            }

            for (int i = 1; i < leafCounts.length; ++i) {
                leafCounts[i] = leftSubResult[i - 1] + rightSubResult[i - 1];
            }
        }

        return leafCounts;
    }
}