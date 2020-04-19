import java.util.ArrayList;
import java.util.List;

class Solution {
    public String reformat(String s) {
        List<Character> letters = new ArrayList<>();
        List<Character> digits = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            if (Character.isLetter(ch)) {
                letters.add(ch);
            } else {
                digits.add(ch);
            }
        }

        if (Math.abs(letters.size() - digits.size()) >= 2) {
            return "";
        }

        boolean letterOrDigit = letters.size() >= digits.size();
        int letterIndex = 0;
        int digitIndex = 0;
        char[] result = new char[s.length()];
        for (int i = 0; i < result.length; ++i) {
            if (letterOrDigit) {
                result[i] = letters.get(letterIndex);
                ++letterIndex;
            } else {
                result[i] = digits.get(digitIndex);
                ++digitIndex;
            }

            letterOrDigit = !letterOrDigit;
        }

        return new String(result);
    }
}