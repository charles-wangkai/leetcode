import java.util.Arrays;

public class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        return Arrays.stream(nums).map(x -> (int) Arrays.stream(nums).filter(num -> num < x).count()).toArray();
    }
}