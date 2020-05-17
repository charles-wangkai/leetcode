import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public List<String> simplifiedFractions(int n) {
        return IntStream.rangeClosed(2, n).boxed().flatMap(denom -> IntStream.range(1, denom)
                .filter(numer -> gcd(numer, denom) == 1).mapToObj(numer -> String.format("%d/%d", numer, denom)))
                .collect(Collectors.toList());
    }

    int gcd(int x, int y) {
        return (y == 0) ? x : gcd(y, x % y);
    }
}