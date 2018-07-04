import java.util.Random;

class ListNode {
	int val;
	ListNode next;
}

public class Solution {
	private Random random = new Random();
	private ListNode head;

	/**
	 * @param head
	 *            The linked list's head. Note that the head is guanranteed to
	 *            be not null, so it contains at least one node.
	 */
	public Solution(ListNode head) {
		this.head = head;
	}

	/** Returns a random node's value. */
	public int getRandom() {
		int chosen = 0;
		int size = 0;
		ListNode node = head;
		while (node != null) {
			size++;
			if (random.nextInt(size) == 0) {
				chosen = node.val;
			}

			node = node.next;
		}
		return chosen;
	}
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(head); int param_1 = obj.getRandom();
 */