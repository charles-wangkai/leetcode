import java.util.ArrayList;
import java.util.List;

class Solution {
    public int findMinFibonacciNumbers(int k) {
        List<Integer> fibos = new ArrayList<>();
        fibos.add(1);
        fibos.add(1);
        while (fibos.get(fibos.size() - 1) < k) {
            fibos.add(fibos.get(fibos.size() - 2) + fibos.get(fibos.size() - 1));
        }

        int result = 0;
        for (int i = fibos.size() - 1; i >= 0; --i) {
            if (k >= fibos.get(i)) {
                k -= fibos.get(i);
                ++result;
            }
        }

        return result;
    }
}