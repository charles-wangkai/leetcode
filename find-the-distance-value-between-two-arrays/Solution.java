import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        return (int) Arrays.stream(arr1).filter(x1 -> !Arrays.stream(arr2).anyMatch(x2 -> Math.abs(x1 - x2) <= d))
                .count();
    }
}