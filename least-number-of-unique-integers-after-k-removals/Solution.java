import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> valueToCount = new HashMap<>();
        for (int value : arr) {
            valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
        }

        return (int) Arrays.stream(arr).boxed()
                .sorted((v1, v2) -> (!valueToCount.get(v1).equals(valueToCount.get(v2)))
                        ? Integer.compare(valueToCount.get(v1), valueToCount.get(v2))
                        : Integer.compare(v1, v2))
                .skip(k).distinct().count();
    }
}