import java.util.HashMap;
import java.util.Map;

// Definition for singly-linked list with a random pointer.
class RandomListNode {
	int label;
	RandomListNode next, random;

	RandomListNode(int x) {
		this.label = x;
	}
}

public class CopyListWithRandomPointer {
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null) {
			return null;
		}

		Map<RandomListNode, RandomListNode> cloneMap = new HashMap<RandomListNode, RandomListNode>();

		RandomListNode clonedHead = new RandomListNode(head.label);
		cloneMap.put(head, clonedHead);
		for (RandomListNode node = head, clonedNode = clonedHead; node.next != null; node = node.next, clonedNode = clonedNode.next) {
			clonedNode.next = new RandomListNode(node.next.label);
			cloneMap.put(node.next, clonedNode.next);
		}

		for (RandomListNode node = head, clonedNode = clonedHead; node != null; node = node.next, clonedNode = clonedNode.next) {
			clonedNode.random = cloneMap.get(node.random);
		}

		return clonedHead;
	}
}
