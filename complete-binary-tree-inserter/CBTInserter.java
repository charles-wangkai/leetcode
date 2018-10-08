import java.util.LinkedList;
import java.util.Queue;

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class CBTInserter {
	TreeNode root;
	Queue<TreeNode> bottomNodes = new LinkedList<>();

	public CBTInserter(TreeNode root) {
		this.root = root;

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode head = queue.poll();

			if (head.right == null) {
				bottomNodes.offer(head);
			}

			if (head.left != null) {
				queue.offer(head.left);
			}
			if (head.right != null) {
				queue.offer(head.right);
			}
		}
	}

	public int insert(int v) {
		TreeNode parent = bottomNodes.peek();

		TreeNode node = new TreeNode(v);
		if (parent.left == null) {
			parent.left = node;
		} else {
			bottomNodes.poll();

			parent.right = node;
		}
		bottomNodes.offer(node);

		return parent.val;
	}

	public TreeNode get_root() {
		return root;
	}
}

// Your CBTInserter object will be instantiated and called as such:
// CBTInserter obj = new CBTInserter(root);
// int param_1 = obj.insert(v);
// TreeNode param_2 = obj.get_root();