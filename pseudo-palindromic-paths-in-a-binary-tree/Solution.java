import java.util.HashSet;
import java.util.Set;

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
    public int pseudoPalindromicPaths(TreeNode root) {
        return search(root, new HashSet<>());
    }

    int search(TreeNode node, Set<Integer> odds) {
        if (node == null) {
            return 0;
        }

        updateOdds(odds, node.val);

        int result;
        if (node.left == null && node.right == null) {
            result = (odds.size() <= 1) ? 1 : 0;
        } else {
            result = search(node.left, odds) + search(node.right, odds);
        }

        updateOdds(odds, node.val);

        return result;
    }

    void updateOdds(Set<Integer> odds, int value) {
        if (odds.contains(value)) {
            odds.remove(value);
        } else {
            odds.add(value);
        }
    }
}