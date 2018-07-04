import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

// Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class Solution {
	public int numComponents(ListNode head, int[] G) {
		Set<Integer> gSet = Arrays.stream(G).boxed().collect(Collectors.toSet());

		int componentNum = 0;
		boolean inG = false;
		for (ListNode node = head; node != null; node = node.next) {
			if (gSet.contains(node.val)) {
				if (!inG) {
					componentNum++;
				}

				inG = true;
			} else {
				inG = false;
			}
		}
		return componentNum;
	}
}
