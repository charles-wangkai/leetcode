public class LonelyPixel_I {
	public int findLonelyPixel(char[][] picture) {
		int row = picture.length;
		int col = picture[0].length;

		int[] blackInRows = new int[row];
		int[] blackInCols = new int[col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (picture[r][c] == 'B') {
					blackInRows[r]++;
					blackInCols[c]++;
				}
			}
		}

		int lonelyNum = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (picture[r][c] == 'B' && blackInRows[r] == 1 && blackInCols[c] == 1) {
					lonelyNum++;
				}
			}
		}
		return lonelyNum;
	}
}
