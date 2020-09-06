class Solution {
    public String modifyString(String s) {
        char[] letters = s.toCharArray();
        for (int i = 0; i < letters.length; ++i) {
            if (letters[i] == '?') {
                for (char ch = 'a';; ++ch) {
                    if ((i == 0 || ch != letters[i - 1]) && (i == s.length() - 1 || ch != letters[i + 1])) {
                        letters[i] = ch;

                        break;
                    }
                }
            }
        }

        return new String(letters);
    }
}