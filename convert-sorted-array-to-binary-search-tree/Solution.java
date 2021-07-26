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
  public TreeNode sortedArrayToBST(int[] nums) {
    return buildBST(nums, 0, nums.length - 1);
  }

  TreeNode buildBST(int[] nums, int beginIndex, int endIndex) {
    if (beginIndex > endIndex) {
      return null;
    }

    int middleIndex = (beginIndex + endIndex) / 2;

    return new TreeNode(
        nums[middleIndex],
        buildBST(nums, beginIndex, middleIndex - 1),
        buildBST(nums, middleIndex + 1, endIndex));
  }
}
