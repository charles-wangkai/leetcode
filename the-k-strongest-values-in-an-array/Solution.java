import java.util.Arrays;

class Solution {
    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);
        int m = arr[(arr.length - 1) / 2];

        return Arrays.stream(arr).boxed().sorted((x1, x2) -> {
            int diff1 = Math.abs(x1 - m);
            int diff2 = Math.abs(x2 - m);
            if (diff1 != diff2) {
                return Integer.compare(diff1, diff2);
            }

            return Integer.compare(x1, x2);
        }).skip(arr.length - k).mapToInt(x -> x).toArray();
    }
}