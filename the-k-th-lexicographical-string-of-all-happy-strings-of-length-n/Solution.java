import java.util.ArrayList;
import java.util.List;

class Solution {
    static final char[] ALPHABET = { 'a', 'b', 'c' };

    public String getHappyString(int n, int k) {
        List<String> happys = new ArrayList<>();
        search(happys, new char[n], 0);

        return (happys.size() >= k) ? happys.get(k - 1) : "";
    }

    void search(List<String> happys, char[] letters, int index) {
        if (index == letters.length) {
            happys.add(new String(letters));

            return;
        }

        for (char letter : ALPHABET) {
            if (index == 0 || letters[index - 1] != letter) {
                letters[index] = letter;
                search(happys, letters, index + 1);
            }
        }
    }
}