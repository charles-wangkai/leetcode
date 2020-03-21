import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public int getKth(int lo, int hi, int k) {
        Map<Integer, Integer> numberToPower = IntStream.rangeClosed(lo, hi).boxed()
                .collect(Collectors.toMap(Function.identity(), this::computePower));

        return IntStream.rangeClosed(lo, hi).boxed().sorted((x1, x2) -> {
            int power1 = numberToPower.get(x1);
            int power2 = numberToPower.get(x2);
            if (power1 != power2) {
                return Integer.compare(power1, power2);
            }

            return Integer.compare(x1, x2);
        }).mapToInt(x -> x).toArray()[k - 1];
    }

    int computePower(int x) {
        if (x == 1) {
            return 0;
        }

        return 1 + computePower((x % 2 == 0) ? (x / 2) : (3 * x + 1));
    }
}