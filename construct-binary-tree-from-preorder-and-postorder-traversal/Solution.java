// Definition for a binary tree node.
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {}

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
  public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
    return build(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
  }

  TreeNode build(
      int[] preorder,
      int preBeginIndex,
      int preEndIndex,
      int[] postorder,
      int postBeginIndex,
      int postEndIndex) {
    if (preBeginIndex > preEndIndex) {
      return null;
    }

    TreeNode node = new TreeNode(preorder[preBeginIndex]);
    if (preBeginIndex < preEndIndex) {
      int leftPreBeginIndex = preBeginIndex + 1;

      int pos = find(postorder, postBeginIndex, preorder[leftPreBeginIndex]);
      int leftLength = pos - postBeginIndex + 1;

      node.left =
          build(
              preorder,
              leftPreBeginIndex,
              leftPreBeginIndex + leftLength - 1,
              postorder,
              postBeginIndex,
              pos);
      node.right =
          build(
              preorder,
              leftPreBeginIndex + leftLength,
              preEndIndex,
              postorder,
              pos + 1,
              postEndIndex - 1);
    }

    return node;
  }

  int find(int[] a, int beginIndex, int target) {
    for (int i = beginIndex; ; ++i) {
      if (a[i] == target) {
        return i;
      }
    }
  }
}
