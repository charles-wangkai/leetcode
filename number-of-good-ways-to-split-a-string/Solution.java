import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numSplits(String s) {
        Map<Character, Integer> rightLetterToCount = new HashMap<>();
        for (char letter : s.toCharArray()) {
            updateLetterToCount(rightLetterToCount, letter, 1);
        }

        Map<Character, Integer> leftLetterToCount = new HashMap<>();
        int result = 0;
        for (char letter : s.toCharArray()) {
            updateLetterToCount(leftLetterToCount, letter, 1);
            updateLetterToCount(rightLetterToCount, letter, -1);

            if (leftLetterToCount.size() == rightLetterToCount.size()) {
                ++result;
            }
        }

        return result;
    }

    void updateLetterToCount(Map<Character, Integer> letterToCount, char letter, int delta) {
        letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + delta);
        letterToCount.remove(letter, 0);
    }
}