import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> result = new ArrayList<>();
        int current = rounds[0];
        while (true) {
            result.add(current);
            if (current == rounds[rounds.length - 1]) {
                break;
            }

            current = current % n + 1;
        }

        Collections.sort(result);

        return result;
    }
}