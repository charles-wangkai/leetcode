import java.util.stream.IntStream;

class Solution {
    static final String SEQUENCE = "croak";

    public int minNumberOfFrogs(String croakOfFrogs) {
        int[] counts = new int[SEQUENCE.length()];
        int frogCount = 0;
        int result = 0;
        for (char ch : croakOfFrogs.toCharArray()) {
            int index = SEQUENCE.indexOf(ch);
            if (index == 0) {
                ++counts[0];

                ++frogCount;
                result = Math.max(result, frogCount);
            } else {
                if (index != 0) {
                    if (counts[index - 1] == 0) {
                        return -1;
                    }

                    --counts[index - 1];
                }

                ++counts[index];

                if (index == counts.length - 1) {
                    --frogCount;
                }
            }
        }

        if (IntStream.range(0, counts.length - 1).anyMatch(i -> counts[i] != 0)) {
            return -1;
        }

        return result;
    }
}