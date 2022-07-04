import java.util.Arrays;

// Definition for singly-linked list.
class ListNode {
  int val;
  ListNode next;

  ListNode() {}

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

class Solution {
  static final int[] R_OFFSETS = {0, 1, 0, -1};
  static final int[] C_OFFSETS = {1, 0, -1, 0};

  public int[][] spiralMatrix(int m, int n, ListNode head) {
    int[][] result = new int[m][n];
    for (int r = 0; r < result.length; ++r) {
      Arrays.fill(result[r], -1);
    }

    int r = 0;
    int c = -1;
    int direction = 0;
    for (ListNode p = head; p != null; p = p.next) {
      r += R_OFFSETS[direction];
      c += C_OFFSETS[direction];
      if (!(r >= 0 && r < m && c >= 0 && c < n && result[r][c] == -1)) {
        r -= R_OFFSETS[direction];
        c -= C_OFFSETS[direction];

        direction = (direction + 1) % R_OFFSETS.length;

        r += R_OFFSETS[direction];
        c += C_OFFSETS[direction];
      }

      result[r][c] = p.val;
    }

    return result;
  }
}