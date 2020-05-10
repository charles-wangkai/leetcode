class Solution {
    public int countTriplets(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; ++i) {
            int a = 0;
            for (int j = i + 1; j < arr.length; ++j) {
                a ^= arr[j - 1];

                int b = 0;
                for (int k = j; k < arr.length; ++k) {
                    b ^= arr[k];

                    if (a == b) {
                        ++result;
                    }
                }
            }
        }

        return result;
    }
}