import java.util.HashSet;
import java.util.Set;

class Solution {
    public int maxUniqueSplit(String s) {
        int result = -1;
        for (int code = 1 << (s.length() - 1); code < (1 << s.length()); ++code) {
            if (check(s, code)) {
                result = Math.max(result, Integer.bitCount(code));
            }
        }

        return result;
    }

    boolean check(String s, int code) {
        Set<String> parts = new HashSet<>();
        StringBuilder part = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            part.append(s.charAt(i));
            if ((code & (1 << i)) != 0) {
                if (parts.contains(part.toString())) {
                    return false;
                }

                parts.add(part.toString());
                part = new StringBuilder();
            }
        }

        return true;
    }
}