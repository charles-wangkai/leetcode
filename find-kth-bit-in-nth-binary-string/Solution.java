class Solution {
    public char findKthBit(int n, int k) {
        if (n == 1) {
            return '0';
        }

        int length = (1 << n) - 1;
        int middle = (length + 1) / 2;
        if (k < middle) {
            return findKthBit(n - 1, k);
        } else if (k > middle) {
            int nextLength = (1 << (n - 1)) - 1;
            int nextK = nextLength + 1 - (k - middle);

            return (char) ('0' + '1' - findKthBit(n - 1, nextK));
        } else {
            return '1';
        }
    }
}