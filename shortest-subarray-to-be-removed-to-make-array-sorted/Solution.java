import java.util.stream.IntStream;

class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        if (IntStream.range(0, arr.length - 1).allMatch(i -> arr[i] <= arr[i + 1])) {
            return 0;
        }

        int leftEndIndex = 0;
        while (arr[leftEndIndex] <= arr[leftEndIndex + 1]) {
            ++leftEndIndex;
        }

        int rightBeginIndex = arr.length - 1;
        while (arr[rightBeginIndex - 1] <= arr[rightBeginIndex]) {
            --rightBeginIndex;
        }

        int result = rightBeginIndex;
        int rightIndex = arr.length - 1;
        for (int i = leftEndIndex; i >= 0; --i) {
            while (rightIndex >= rightBeginIndex && arr[rightIndex] >= arr[i]) {
                --rightIndex;
            }

            result = Math.min(result, rightIndex - i);
        }

        return result;
    }
}