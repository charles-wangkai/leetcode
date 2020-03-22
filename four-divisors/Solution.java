import java.util.Arrays;

class Solution {
    public int sumFourDivisors(int[] nums) {
        return Arrays.stream(nums).map(this::computeSumForFourDivisors).sum();
    }

    int computeSumForFourDivisors(int n) {
        int sum = 0;
        int divisorCount = 0;
        for (int i = 1; i * i <= n; ++i) {
            if (n % i == 0) {
                ++divisorCount;
                sum += i;

                if (n / i != i) {
                    ++divisorCount;
                    sum += n / i;
                }
            }
        }

        return (divisorCount == 4) ? sum : 0;
    }
}