import java.util.Comparator;
import java.util.PriorityQueue;

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
  public ListNode mergeKLists(ListNode[] lists) {
    ListNode tempHead = new ListNode();
    ListNode tail = tempHead;

    PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparing(i -> lists[i].val));
    for (int i = 0; i < lists.length; ++i) {
      if (lists[i] != null) {
        pq.offer(i);
      }
    }

    while (!pq.isEmpty()) {
      int head = pq.poll();

      tail.next = lists[head];
      lists[head] = lists[head].next;
      tail = tail.next;
      tail.next = null;

      if (lists[head] != null) {
        pq.offer(head);
      }
    }

    return tempHead.next;
  }
}
