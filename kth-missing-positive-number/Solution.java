class Solution {
    public int findKthPositive(int[] arr, int k) {
        int index = 0;
        int result = 0;
        for (int i = 0; i < k; ++i) {
            ++result;
            while (index != arr.length && arr[index] == result) {
                ++index;
                ++result;
            }
        }

        return result;
    }
}