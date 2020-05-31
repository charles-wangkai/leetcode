import java.util.Arrays;

class Solution {
    public int maxProduct(int[] nums) {
        return Arrays.stream(nums).sorted().skip(nums.length - 2).map(x -> x - 1).reduce((x, y) -> x * y).getAsInt();
    }
}