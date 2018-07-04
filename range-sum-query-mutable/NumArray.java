public class NumArray {
	SegmentNode root;
	SegmentNode[] leaves;

	public NumArray(int[] nums) {
		if (nums.length == 0) {
			return;
		}

		leaves = new SegmentNode[nums.length];

		root = buildSegmentNode(0, nums.length - 1, null);

		for (int i = 0; i < leaves.length; i++) {
			update(i, nums[i]);
		}
	}

	private SegmentNode buildSegmentNode(int beginIndex, int endIndex, SegmentNode parent) {
		SegmentNode node = new SegmentNode();

		node.beginIndex = beginIndex;
		node.endIndex = endIndex;
		node.parent = parent;

		if (beginIndex == endIndex) {
			leaves[beginIndex] = node;
		} else {
			int middleIndex = (beginIndex + endIndex) / 2;
			node.leftChild = buildSegmentNode(beginIndex, middleIndex, node);
			node.rightChild = buildSegmentNode(middleIndex + 1, endIndex, node);
		}

		return node;
	}

	void update(int i, int val) {
		int delta = val - leaves[i].sum;

		SegmentNode node = leaves[i];
		while (node != null) {
			node.sum += delta;
			node = node.parent;
		}
	}

	public int sumRange(int i, int j) {
		return computeSum(root, i, j);
	}

	private int computeSum(SegmentNode node, int beginIndex, int endIndex) {
		int mergedBeginIndex = Math.max(beginIndex, node.beginIndex);
		int mergedEndIndex = Math.min(endIndex, node.endIndex);

		if (mergedBeginIndex > mergedEndIndex) {
			return 0;
		} else if (mergedBeginIndex == node.beginIndex && mergedEndIndex == node.endIndex) {
			return node.sum;
		} else {
			return computeSum(node.leftChild, mergedBeginIndex, mergedEndIndex)
					+ computeSum(node.rightChild, mergedBeginIndex, mergedEndIndex);
		}
	}
}

class SegmentNode {
	int beginIndex;
	int endIndex;
	int sum;
	SegmentNode parent;
	SegmentNode leftChild;
	SegmentNode rightChild;
}

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);