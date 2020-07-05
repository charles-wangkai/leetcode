class Solution {
    public String minInteger(String num, int k) {
        char[] digits = num.toCharArray();

        for (int i = 0; i < digits.length; ++i) {
            int bestIndex = i;
            for (int j = i + 1; j < digits.length && j - i <= k; ++j) {
                if (digits[j] < digits[bestIndex]) {
                    bestIndex = j;
                }
            }

            char minDigit = digits[bestIndex];
            for (int j = bestIndex; j - 1 >= i; --j) {
                digits[j] = digits[j - 1];
            }
            digits[i] = minDigit;

            k -= bestIndex - i;
        }

        return new String(digits);
    }
}