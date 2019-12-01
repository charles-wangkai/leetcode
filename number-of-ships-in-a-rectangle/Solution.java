// This is Sea's API interface.
// You should not implement it, or speculate about its implementation
interface Sea {
	public boolean hasShips(int[] topRight, int[] bottomLeft);
}

public class Solution {
	public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
		if (topRight[0] < bottomLeft[0] || topRight[1] < bottomLeft[1]) {
			return 0;
		}

		boolean existShips = sea.hasShips(topRight, bottomLeft);
		if (!existShips) {
			return 0;
		}

		if (topRight[0] == bottomLeft[0] && topRight[1] == bottomLeft[1]) {
			return 1;
		}

		int middleX = (topRight[0] + bottomLeft[0]) / 2;
		int middleY = (topRight[1] + bottomLeft[1]) / 2;

		return countShips(sea, new int[] { topRight[0], topRight[1] }, new int[] { middleX + 1, middleY + 1 })
				+ countShips(sea, new int[] { topRight[0], middleY }, new int[] { middleX + 1, bottomLeft[1] })
				+ countShips(sea, new int[] { middleX, topRight[1] }, new int[] { bottomLeft[0], middleY + 1 })
				+ countShips(sea, new int[] { middleX, middleY }, new int[] { bottomLeft[0], bottomLeft[1] });
	}
}
