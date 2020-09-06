class Solution {
    static final int MODULUS = 1_000_000_007;

    public int numWays(String s) {
        int oneCount = (int) s.chars().filter(ch -> ch == '1').count();
        if (oneCount % 3 != 0) {
            return 0;
        }

        int partCount = oneCount / 3;

        if (partCount == 0) {
            return (int) ((s.length() - 1L) * (s.length() - 2) / 2 % MODULUS);
        }

        int leftOneCount = 0;
        int leftCut = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '1') {
                ++leftOneCount;
            }
            if (leftOneCount == partCount) {
                ++leftCut;
            }
        }

        int rightOneCount = 0;
        int rightCut = 0;
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == '1') {
                ++rightOneCount;
            }
            if (rightOneCount == partCount) {
                ++rightCut;
            }
        }

        return (int) ((long) leftCut * rightCut % MODULUS);
    }
}