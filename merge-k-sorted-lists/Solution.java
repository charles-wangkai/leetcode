import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class Solution {
	public ListNode mergeKLists(List<ListNode> lists) {
		ListNode head = null;
		ListNode tail = null;

		PriorityQueue<ListNode_Index> pq = new PriorityQueue<ListNode_Index>(1,
				new NodeComparator());
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i) != null) {
				moveListToPriorityQueue(pq, lists, i);
			}
		}

		while (!pq.isEmpty()) {
			ListNode_Index li = pq.poll();

			if (head == null) {
				head = li.node;
			} else {
				tail.next = li.node;
			}
			tail = li.node;

			if (lists.get(li.index) != null) {
				moveListToPriorityQueue(pq, lists, li.index);
			}
		}

		return head;
	}

	void moveListToPriorityQueue(PriorityQueue<ListNode_Index> pq,
			List<ListNode> lists, int index) {
		ListNode node = lists.get(index);
		pq.offer(new ListNode_Index(node, index));
		lists.set(index, node.next);
		node.next = null;
	}
}

class ListNode_Index {
	ListNode node;
	int index;

	public ListNode_Index(ListNode node, int index) {
		this.node = node;
		this.index = index;
	}
}

class NodeComparator implements Comparator<ListNode_Index> {
	@Override
	public int compare(ListNode_Index li1, ListNode_Index li2) {
		return li1.node.val - li2.node.val;
	}
}