class Solution {
    public int minInsertions(String s) {
        int result = 0;
        int leftCount = 0;
        for (int i = 0; i < s.length(); ++i) {
            char current = s.charAt(i);

            if (current == '(') {
                ++leftCount;
            } else {
                if (leftCount == 0) {
                    ++result;
                } else {
                    --leftCount;
                }

                if (i + 1 != s.length() && s.charAt(i + 1) == ')') {
                    ++i;
                } else {
                    ++result;
                }
            }
        }
        result += leftCount * 2;

        return result;
    }
}