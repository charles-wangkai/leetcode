import java.util.stream.IntStream;

class Solution {
    public boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] nextMoves = IntStream.range(0, 26).toArray();
        for (int i = 0; i < s.length(); ++i) {
            int distance = (t.charAt(i) - s.charAt(i) + 26) % 26;
            if (distance != 0) {
                if (nextMoves[distance] > k) {
                    return false;
                }

                nextMoves[distance] += 26;
            }
        }

        return true;
    }
}