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
  public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
    ListNode prev1 = findAt(list1, a - 1);
    ListNode next1 = findAt(list1, b + 1);
    ListNode head2 = findAt(list2, 0);
    ListNode tail2 = findAt(list2, getLength(list2) - 1);

    prev1.next = head2;
    tail2.next = next1;

    return list1;
  }

  ListNode findAt(ListNode list, int index) {
    ListNode result = list;
    for (int i = 0; i < index; ++i) {
      result = result.next;
    }

    return result;
  }

  int getLength(ListNode list) {
    int result = 0;
    for (ListNode node = list; node != null; node = node.next) {
      ++result;
    }

    return result;
  }
}
