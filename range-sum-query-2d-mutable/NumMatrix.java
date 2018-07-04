public class NumMatrix {
	SegmentNode root;
	SegmentNode[][] leaves;

	public NumMatrix(int[][] matrix) {
		int row = matrix.length;
		if (row == 0) {
			return;
		}
		int col = matrix[0].length;
		if (col == 0) {
			return;
		}

		leaves = new SegmentNode[row][col];

		root = buildSegmentNode(0, 0, row - 1, col - 1, null);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				update(i, j, matrix[i][j]);
			}
		}
	}

	private SegmentNode buildSegmentNode(int minRow, int minCol, int maxRow, int maxCol, SegmentNode parent) {
		SegmentNode node = new SegmentNode();

		node.minRow = minRow;
		node.minCol = minCol;
		node.maxRow = maxRow;
		node.maxCol = maxCol;
		node.parent = parent;

		if (minRow <= maxRow && minCol <= maxCol) {
			if (minRow == maxRow && minCol == maxCol) {
				leaves[minRow][minCol] = node;
			} else {
				int middleRow = (minRow + maxRow) / 2;
				int middleCol = (minCol + maxCol) / 2;
				node.upLeftChild = buildSegmentNode(minRow, minCol, middleRow, middleCol, node);
				node.upRightChild = buildSegmentNode(minRow, middleCol + 1, middleRow, maxCol, node);
				node.downLeftChild = buildSegmentNode(middleRow + 1, minCol, maxRow, middleCol, node);
				node.downRightChild = buildSegmentNode(middleRow + 1, middleCol + 1, maxRow, maxCol, node);
			}
		}

		return node;
	}

	public void update(int row, int col, int val) {
		int delta = val - leaves[row][col].sum;

		SegmentNode node = leaves[row][col];
		while (node != null) {
			node.sum += delta;
			node = node.parent;
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		return computeSum(root, row1, col1, row2, col2);
	}

	private int computeSum(SegmentNode node, int minRow, int minCol, int maxRow, int maxCol) {
		int mergedMinRow = Math.max(minRow, node.minRow);
		int mergedMinCol = Math.max(minCol, node.minCol);
		int mergedMaxRow = Math.min(maxRow, node.maxRow);
		int mergedMaxCol = Math.min(maxCol, node.maxCol);

		if (mergedMinRow > mergedMaxRow || mergedMinCol > mergedMaxCol) {
			return 0;
		} else if (mergedMinRow == node.minRow && mergedMinCol == node.minCol && mergedMaxRow == node.maxRow
				&& mergedMaxCol == node.maxCol) {
			return node.sum;
		} else {
			return computeSum(node.upLeftChild, mergedMinRow, mergedMinCol, mergedMaxRow, mergedMaxCol)
					+ computeSum(node.upRightChild, mergedMinRow, mergedMinCol, mergedMaxRow, mergedMaxCol)
					+ computeSum(node.downLeftChild, mergedMinRow, mergedMinCol, mergedMaxRow, mergedMaxCol)
					+ computeSum(node.downRightChild, mergedMinRow, mergedMinCol, mergedMaxRow, mergedMaxCol);
		}
	}
}

class SegmentNode {
	int minRow;
	int minCol;
	int maxRow;
	int maxCol;
	int sum;
	SegmentNode parent;
	SegmentNode upLeftChild;
	SegmentNode upRightChild;
	SegmentNode downLeftChild;
	SegmentNode downRightChild;
}

// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);