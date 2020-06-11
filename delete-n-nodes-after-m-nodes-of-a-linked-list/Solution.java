// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode tempHead = new ListNode(0, head);
        ListNode p = tempHead;
        while (true) {
            for (int i = 0; i < m; ++i) {
                if (p == null) {
                    return tempHead.next;
                }

                p = p.next;
            }

            for (int i = 0; i < n; ++i) {
                if (p == null || p.next == null) {
                    return tempHead.next;
                }

                p.next = p.next.next;
            }
        }
    }
}