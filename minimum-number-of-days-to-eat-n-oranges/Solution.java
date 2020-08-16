import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Integer, Integer> cache = new HashMap<>();

    public int minDays(int n) {
        if (n <= 1) {
            return n;
        }

        if (!cache.containsKey(n)) {
            cache.put(n, 1 + Math.min(n % 3 + minDays(n / 3), n % 2 + minDays(n / 2)));
        }

        return cache.get(n);
    }
}