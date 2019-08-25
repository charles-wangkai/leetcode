import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	public ListNode removeZeroSumSublists(ListNode head) {
		List<Integer> sums = new ArrayList<>();
		sums.add(0);

		Map<Integer, Integer> sumToLength = new HashMap<>();
		sumToLength.put(0, 0);

		Stack<ListNode> stack = new Stack<>();
		int sum = 0;

		for (ListNode node = head; node != null; node = node.next) {
			sum += node.val;
			if (sumToLength.containsKey(sum)) {
				int length = sumToLength.get(sum);
				while (length != sums.size() - 1) {
					stack.pop();

					sumToLength.remove(sums.get(sums.size() - 1));
					sums.remove(sums.size() - 1);
				}
			} else {
				stack.push(node);

				sums.add(sum);
				sumToLength.put(sum, sumToLength.size());
			}
		}

		ListNode result = null;
		while (!stack.empty()) {
			ListNode node = stack.pop();
			node.next = result;
			result = node;
		}

		return result;
	}
}
