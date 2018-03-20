public class BricksFallingWhenHit {
	static final Node TOP_NODE = new Node(-1, -1, null, 0);

	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int[] hitBricks(int[][] grid, int[][] hits) {
		int row = grid.length;
		int col = grid[0].length;

		boolean[][] hitGrid = new boolean[row][col];
		for (int[] hit : hits) {
			hitGrid[hit[0]][hit[1]] = true;
		}

		Node[][] nodes = new Node[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (grid[r][c] == 1 && !hitGrid[r][c]) {
					addNode(nodes, r, c);
				}
			}
		}

		int[] dropNums = new int[hits.length];
		for (int i = hits.length - 1; i >= 0; i--) {
			int hitR = hits[i][0];
			int hitC = hits[i][1];

			int dropNum;
			if (grid[hitR][hitC] == 1) {
				dropNum = addNode(nodes, hitR, hitC);
			} else {
				dropNum = 0;
			}

			dropNums[i] = dropNum;
		}
		return dropNums;
	}

	int addNode(Node[][] nodes, int r, int c) {
		int row = nodes.length;
		int col = nodes[0].length;

		int dropNum;
		if (r == 0) {
			nodes[r][c] = new Node(r, c, TOP_NODE, 1);
			dropNum = 0;
		} else {
			nodes[r][c] = new Node(r, c, null, 1);
			dropNum = -1;
		}

		for (int j = 0; j < R_OFFSETS.length; j++) {
			int adjR = r + R_OFFSETS[j];
			int adjC = c + C_OFFSETS[j];

			if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && nodes[adjR][adjC] != null) {
				Node rootHit = findRoot(nodes[r][c]);
				Node rootAdj = findRoot(nodes[adjR][adjC]);

				if (!rootHit.equals(rootAdj)) {
					if (rootHit.equals(TOP_NODE)) {
						rootAdj.parent = rootHit;

						dropNum += rootAdj.size;
					} else {
						rootHit.parent = rootAdj;
						rootAdj.size += rootHit.size;

						if (rootAdj.equals(TOP_NODE)) {
							dropNum += rootHit.size;
						}
					}
				}
			}
		}
		return Math.max(0, dropNum);
	}

	Node findRoot(Node node) {
		Node root = node;
		while (root.parent != null) {
			root = root.parent;
		}
		return root;
	}
}

class Node {
	int r;
	int c;
	Node parent;
	int size;

	Node(int r, int c, Node parent, int size) {
		this.r = r;
		this.c = c;
		this.parent = parent;
		this.size = size;
	}

	@Override
	public int hashCode() {
		return r * c;
	}

	@Override
	public boolean equals(Object obj) {
		Node other = (Node) obj;
		return r == other.r && c == other.c;
	}
}