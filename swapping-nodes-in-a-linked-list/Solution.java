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
  public ListNode swapNodes(ListNode head, int k) {
    int length = 0;
    for (ListNode n = head; n != null; n = n.next) {
      ++length;
    }

    ListNode n1 = findNode(head, k - 1);
    ListNode n2 = findNode(head, length - k);
    int temp = n1.val;
    n1.val = n2.val;
    n2.val = temp;

    return head;
  }

  ListNode findNode(ListNode head, int index) {
    ListNode result = head;
    for (int i = 0; i < index; ++i) {
      result = result.next;
    }

    return result;
  }
}
