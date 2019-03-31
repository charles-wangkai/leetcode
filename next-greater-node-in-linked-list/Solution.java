import java.util.Stack;

// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class Solution {
	public int[] nextLargerNodes(ListNode head) {
		Stack<Integer> values = new Stack<>();
		for (ListNode node = head; node != null; node = node.next) {
			values.push(node.val);
		}

		int[] result = new int[values.size()];
		Stack<Integer> largers = new Stack<>();
		for (int i = result.length - 1; i >= 0; i--) {
			int value = values.pop();

			while (!largers.empty() && largers.peek() <= value) {
				largers.pop();
			}

			result[i] = largers.empty() ? 0 : largers.peek();

			largers.push(value);
		}
		return result;
	}
}
