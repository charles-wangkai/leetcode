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
  public ListNode mergeNodes(ListNode head) {
    ListNode tempHead = new ListNode();
    ListNode tail = tempHead;

    int sum = 0;
    for (ListNode p = head; p != null; p = p.next) {
      if (p.val == 0) {
        if (sum != 0) {
          tail.next = new ListNode(sum);
          tail = tail.next;
        }
        sum = 0;
      } else {
        sum += p.val;
      }
    }

    return tempHead.next;
  }
}