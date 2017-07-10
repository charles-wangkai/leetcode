import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class AverageOfLevelsInBinaryTree {
	public List<Double> averageOfLevels(TreeNode root) {
		List<Long> sums = new ArrayList<Long>();
		List<Integer> counts = new ArrayList<Integer>();

		search(root, sums, counts, 0);

		return IntStream.range(0, sums.size()).mapToObj(i -> (double) sums.get(i) / counts.get(i))
				.collect(Collectors.toList());
	}

	void search(TreeNode node, List<Long> sums, List<Integer> counts, int index) {
		if (node == null) {
			return;
		}

		if (index == sums.size()) {
			sums.add(0L);
			counts.add(0);
		}

		sums.set(index, sums.get(index) + node.val);
		counts.set(index, counts.get(index) + 1);

		search(node.left, sums, counts, index + 1);
		search(node.right, sums, counts, index + 1);
	}
}
