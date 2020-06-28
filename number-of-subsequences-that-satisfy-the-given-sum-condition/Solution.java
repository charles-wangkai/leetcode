import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    static final int MODULUS = 1_000_000_007;

    public int numSubseq(int[] nums, int target) {
        int[] twoPowers = new int[nums.length + 1];
        twoPowers[0] = 1;
        for (int i = 1; i < nums.length; ++i) {
            twoPowers[i] = multiplyMod(twoPowers[i - 1], 2);
        }

        Map<Integer, Integer> valueToCount = new HashMap<>();
        for (int value : nums) {
            valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
        }

        nums = Arrays.stream(nums).boxed().sorted().mapToInt(x -> x).toArray();

        int result = 0;
        for (int i = 0; i < nums.length && nums[i] * 2 <= target; ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                result = addMod(result, multiplyMod(subtractMod(twoPowers[valueToCount.get(nums[i])], 1),
                        twoPowers[computeExtraNum(nums, target - nums[i], i + valueToCount.get(nums[i]))]));
            }
        }

        return result;
    }

    static int computeExtraNum(int[] nums, int limit, int beginIndex) {
        int index = beginIndex - 1;
        int lower = beginIndex;
        int upper = nums.length - 1;
        while (lower <= upper) {
            int middle = (lower + upper) / 2;

            if (nums[middle] <= limit) {
                index = middle;
                lower = middle + 1;
            } else {
                upper = middle - 1;
            }
        }

        return index - beginIndex + 1;
    }

    static int addMod(int x, int y) {
        return (x + y) % MODULUS;
    }

    static int subtractMod(int x, int y) {
        return (x - y + MODULUS) % MODULUS;
    }

    static int multiplyMod(int x, int y) {
        return (int) ((long) x * y % MODULUS);
    }
}