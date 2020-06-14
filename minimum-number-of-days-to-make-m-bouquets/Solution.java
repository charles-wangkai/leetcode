import java.util.Arrays;

class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int result = -1;
        int lower = 1;
        int upper = Arrays.stream(bloomDay).max().getAsInt();
        while (lower <= upper) {
            int middle = (lower + upper) / 2;

            if (check(bloomDay, m, k, middle)) {
                result = middle;
                upper = middle - 1;
            } else {
                lower = middle + 1;
            }
        }

        return result;
    }

    boolean check(int[] bloomDay, int m, int k, int waitDay) {
        int made = 0;
        int count = 0;
        for (int i = 0; i < bloomDay.length; ++i) {
            if (bloomDay[i] <= waitDay) {
                ++count;
                if (count == k) {
                    ++made;
                    count = 0;
                }
            } else {
                count = 0;
            }
        }

        return made >= m;
    }
}