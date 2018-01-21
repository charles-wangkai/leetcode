public class CouplesHoldingHands {
	public int minSwapsCouples(int[] row) {
		int[] pos = new int[row.length];
		for (int i = 0; i < pos.length; i++) {
			pos[row[i]] = i;
		}

		int swapNum = 0;
		for (int i = 0; i < row.length; i += 2) {
			int another = (row[i] % 2 == 0) ? (row[i] + 1) : (row[i] - 1);
			if (row[i + 1] != another) {
				swap(row, pos, i + 1, pos[another]);
				swapNum++;
			}
		}
		return swapNum;
	}

	void swap(int[] row, int[] pos, int index1, int index2) {
		int temp = row[index1];
		row[index1] = row[index2];
		row[index2] = temp;

		pos[row[index1]] = index1;
		pos[row[index2]] = index2;
	}
}
