import java.util.stream.IntStream;

class Solution {
    public boolean containsPattern(int[] arr, int m, int k) {
        for (int i = 0; i + m * k <= arr.length; ++i) {
            int i_ = i;
            if (IntStream.range(0, m * k).allMatch(j -> arr[i_ + j] == arr[i_ + j % m])) {
                return true;
            }
        }

        return false;
    }
}