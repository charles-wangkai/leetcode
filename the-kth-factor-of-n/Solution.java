import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int kthFactor(int n, int k) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 1; i * i <= n; ++i) {
            if (n % i == 0) {
                factors.add(i);

                if (n / i != i) {
                    factors.add(n / i);
                }
            }
        }

        Collections.sort(factors);

        return (factors.size() < k) ? -1 : factors.get(k - 1);
    }
}