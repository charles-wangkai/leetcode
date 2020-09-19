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

class BSTIterator {
    List<Integer> values = new ArrayList<>();
    int index = -1;

    public BSTIterator(TreeNode root) {
        inorder(root);
    }

    private void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.left);
            values.add(node.val);
            inorder(node.right);
        }
    }

    public boolean hasNext() {
        return index + 1 < values.size();
    }

    public int next() {
        ++index;

        return values.get(index);
    }

    public boolean hasPrev() {
        return index - 1 >= 0;
    }

    public int prev() {
        --index;

        return values.get(index);
    }
}

// Your BSTIterator object will be instantiated and called as such:
// BSTIterator obj = new BSTIterator(root);
// boolean param_1 = obj.hasNext();
// int param_2 = obj.next();
// boolean param_3 = obj.hasPrev();
// int param_4 = obj.prev();
