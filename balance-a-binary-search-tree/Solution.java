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
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        inorder(values, root);

        return buildBalancedBST(values, 0, values.size() - 1);
    }

    void inorder(List<Integer> values, TreeNode node) {
        if (node == null) {
            return;
        }

        inorder(values, node.left);
        values.add(node.val);
        inorder(values, node.right);
    }

    TreeNode buildBalancedBST(List<Integer> values, int beginIndex, int endIndex) {
        if (beginIndex > endIndex) {
            return null;
        }

        int middleIndex = (beginIndex + endIndex) / 2;
        TreeNode node = new TreeNode(values.get(middleIndex));
        node.left = buildBalancedBST(values, beginIndex, middleIndex - 1);
        node.right = buildBalancedBST(values, middleIndex + 1, endIndex);

        return node;
    }
}