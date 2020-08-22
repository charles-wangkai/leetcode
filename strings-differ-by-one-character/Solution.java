import java.util.HashSet;
import java.util.Set;

class Solution {
    public boolean differByOne(String[] dict) {
        Set<String> patterns = new HashSet<>();
        for (String s : dict) {
            for (int i = 0; i < s.length(); ++i) {
                String pattern = String.format("%s*%s", s.substring(0, i), s.substring(i + 1));
                if (patterns.contains(pattern)) {
                    return true;
                }

                patterns.add(pattern);
            }
        }

        return false;
    }
}