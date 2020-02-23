public class Solution {
    public int[] closestDivisors(int num) {
        int[] divisors1 = findDivisors(num + 1);
        int[] divisors2 = findDivisors(num + 2);

        return (divisors1[1] - divisors1[0] <= divisors2[1] - divisors2[0]) ? divisors1 : divisors2;
    }

    int[] findDivisors(int product) {
        int firstDivisor = 1;
        for (int i = 2; i * i <= product; ++i) {
            if (product % i == 0) {
                firstDivisor = i;
            }
        }

        return new int[] { firstDivisor, product / firstDivisor };
    }
}