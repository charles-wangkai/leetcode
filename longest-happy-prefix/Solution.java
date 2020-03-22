class Solution {
    public String longestPrefix(String s) {
        int[] nexts = new int[s.length()];
        int k = 0;
        for (int i = 1; i < nexts.length; i++) {
            while (k > 0 && s.charAt(k) != s.charAt(i)) {
                k = nexts[k - 1];
            }

            if (s.charAt(k) == s.charAt(i)) {
                ++k;
            }

            nexts[i] = k;
        }

        return s.substring(0, nexts[nexts.length - 1]);
    }
}