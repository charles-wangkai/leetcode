import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findLucky(int[] arr) {
        Map<Integer, Integer> valueToCount = new HashMap<>();
        for (int value : arr) {
            valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
        }

        return valueToCount.keySet().stream().filter(value -> value == valueToCount.get(value)).mapToInt(x -> x).max()
                .orElse(-1);
    }
}