import java.util.stream.IntStream;

public class Solution {
    public int minSteps(String s, String t) {
        int[] sCounts = buildCounts(s);
        int[] tCounts = buildCounts(t);

        return IntStream.range(0, sCounts.length).map(i -> Math.abs(sCounts[i] - tCounts[i])).sum() / 2;
    }

    int[] buildCounts(String str) {
        int[] counts = new int[26];
        for (char ch : str.toCharArray()) {
            ++counts[ch - 'a'];
        }

        return counts;
    }
}