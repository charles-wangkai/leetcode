public class MaxChunksToMakeSorted {
	public int maxChunksToSorted(int[] arr) {
		int chunkNum = 0;
		int maxNumber = -1;
		for (int i = 0; i < arr.length; i++) {
			maxNumber = Math.max(maxNumber, arr[i]);

			if (maxNumber == i) {
				chunkNum++;
			}
		}
		return chunkNum;
	}
}
