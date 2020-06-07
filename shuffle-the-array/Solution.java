import java.util.stream.IntStream;

class Solution {
    public int[] shuffle(int[] nums, int n) {
        return IntStream.range(0, n * 2).map(i -> nums[i / 2 + ((i % 2 != 0) ? n : 0)]).toArray();
    }
}