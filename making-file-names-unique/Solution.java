import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public String[] getFolderNames(String[] names) {
        String[] result = new String[names.length];
        Set<String> seen = new HashSet<>();
        Map<String, Integer> prefixToCount = new HashMap<>();
        for (int i = 0; i < result.length; ++i) {
            for (int j = prefixToCount.getOrDefault(names[i], 0);; ++j) {
                String name;
                if (j == 0) {
                    name = names[i];
                } else {
                    name = String.format("%s(%d)", names[i], j);
                }

                if (!seen.contains(name)) {
                    result[i] = name;
                    seen.add(name);
                    prefixToCount.put(names[i], j + 1);

                    break;
                }
            }
        }

        return result;
    }
}