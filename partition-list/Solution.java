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
  public ListNode partition(ListNode head, int x) {
    ListNode tempLeftHead = new ListNode();
    ListNode tempRightHead = new ListNode();

    while (head != null) {
      ListNode next = head.next;

      ListNode targetHead = (head.val < x) ? tempLeftHead : tempRightHead;
      head.next = targetHead.next;
      targetHead.next = head;

      head = next;
    }

    ListNode tempHead = new ListNode();
    for (ListNode halfHead : new ListNode[] {tempRightHead, tempLeftHead}) {
      while (halfHead.next != null) {
        ListNode node = halfHead.next;
        halfHead.next = node.next;

        node.next = tempHead.next;
        tempHead.next = node;
      }
    }

    return tempHead.next;
  }
}
