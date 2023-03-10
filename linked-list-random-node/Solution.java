import java.util.Random;

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
  private Random random = new Random();
  private ListNode head;

  public Solution(ListNode head) {
    this.head = head;
  }

  public int getRandom() {
    int result = Integer.MIN_VALUE;
    int size = 0;
    for (ListNode node = head; node != null; node = node.next) {
      ++size;
      if (random.nextInt(size) == 0) {
        result = node.val;
      }
    }

    return result;
  }
}

// Your Solution object will be instantiated and called as such:
// Solution obj = new Solution(head);
// int param_1 = obj.getRandom();
