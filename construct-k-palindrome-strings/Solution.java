import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean canConstruct(String s, int k) {
        Map<Character, Integer> chToCount = new HashMap<>();
        for (char ch : s.toCharArray()) {
            chToCount.put(ch, chToCount.getOrDefault(ch, 0) + 1);
        }

        int oddCount = (int) chToCount.values().stream().filter(count -> count % 2 != 0).count();

        return oddCount <= k && s.length() >= k;
    }
}