class Solution {
    static final int MODULUS = 1_000_000_007;

    public int numberOfArrays(String s, int k) {
        int kLength = String.valueOf(k).length();

        int[] wayNums = new int[s.length() + 1];
        wayNums[0] = 1;
        for (int i = 1; i < wayNums.length; ++i) {
            for (int j = 1; j <= Math.min(i, kLength); ++j) {
                String part = s.substring(i - j, i);
                if (part.charAt(0) != '0' && Long.parseLong(part) <= k) {
                    wayNums[i] = addMod(wayNums[i], wayNums[i - j]);
                }
            }
        }

        return wayNums[wayNums.length - 1];
    }

    int addMod(int x, int y) {
        return (x + y) % MODULUS;
    }
}