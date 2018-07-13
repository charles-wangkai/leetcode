// Definition for a QuadTree node.
class Node {
	public boolean val;
	public boolean isLeaf;
	public Node topLeft;
	public Node topRight;
	public Node bottomLeft;
	public Node bottomRight;

	public Node() {
	}

	public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
		val = _val;
		isLeaf = _isLeaf;
		topLeft = _topLeft;
		topRight = _topRight;
		bottomLeft = _bottomLeft;
		bottomRight = _bottomRight;
	}
}

public class Solution {
	public Node construct(int[][] grid) {
		if (grid.length == 0) {
			return null;
		}

		return buildNode(grid, 0, 0, grid.length);
	}

	Node buildNode(int[][] grid, int beginR, int beginC, int size) {
		Boolean sameValue = getSameValue(grid, beginR, beginC, size);
		if (sameValue != null) {
			return new Node(sameValue, true, null, null, null, null);
		} else {
			Node topLeft = buildNode(grid, beginR, beginC, size / 2);
			Node topRight = buildNode(grid, beginR, beginC + size / 2, size / 2);
			Node bottomLeft = buildNode(grid, beginR + size / 2, beginC, size / 2);
			Node bottomRight = buildNode(grid, beginR + size / 2, beginC + size / 2, size / 2);
			return new Node(true, false, topLeft, topRight, bottomLeft, bottomRight);
		}
	}

	Boolean getSameValue(int[][] grid, int beginR, int beginC, int size) {
		int sameValue = grid[beginR][beginC];
		for (int r = beginR; r < beginR + size; r++) {
			for (int c = beginC; c < beginC + size; c++) {
				if (grid[r][c] != sameValue) {
					return null;
				}
			}
		}
		return sameValue == 1;
	}
}
