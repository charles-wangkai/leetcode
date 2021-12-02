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
  public ListNode oddEvenList(ListNode head) {
    ListNode tempOddHead = new ListNode(0, head);
    ListNode tempEvenHead = new ListNode();
    ListNode evenTail = tempEvenHead;

    ListNode node = tempOddHead;
    boolean oddOrEven = true;
    while (node.next != null) {
      if (oddOrEven) {
        node = node.next;
      } else {
        evenTail.next = node.next;
        node.next = node.next.next;
        evenTail.next.next = null;
        evenTail = evenTail.next;
      }

      oddOrEven = !oddOrEven;
    }

    node.next = tempEvenHead.next;

    return tempOddHead.next;
  }
}
