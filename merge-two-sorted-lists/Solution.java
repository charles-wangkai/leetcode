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
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    ListNode mergedHead = null;
    ListNode mergedTail = null;
    while (list1 != null || list2 != null) {
      ListNode node;
      if (list1 != null && (list2 == null || list1.val < list2.val)) {
        node = list1;
        list1 = list1.next;
      } else {
        node = list2;
        list2 = list2.next;
      }

      node.next = null;
      if (mergedHead == null) {
        mergedHead = node;
      } else {
        mergedTail.next = node;
      }
      mergedTail = node;
    }

    return mergedHead;
  }
}
