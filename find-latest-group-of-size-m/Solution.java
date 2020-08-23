import java.util.NavigableSet;
import java.util.TreeSet;

class Solution {
    public int findLatestStep(int[] arr, int m) {
        if (m == arr.length) {
            return arr.length;
        }

        NavigableSet<Range> ranges = new TreeSet<>((r1, r2) -> Integer.compare(r1.begin, r2.begin));
        ranges.add(new Range(1, arr.length));

        for (int i = arr.length - 1; i >= 0; --i) {
            Range range = ranges.floor(new Range(arr[i], arr[i]));

            int leftBegin = range.begin;
            int leftEnd = arr[i] - 1;
            int rightBegin = arr[i] + 1;
            int rightEnd = range.end;

            if (leftEnd - leftBegin + 1 == m || rightEnd - rightBegin + 1 == m) {
                return i;
            }

            ranges.remove(range);
            if (leftBegin <= leftEnd) {
                ranges.add(new Range(leftBegin, leftEnd));
            }
            if (rightBegin <= rightEnd) {
                ranges.add(new Range(rightBegin, rightEnd));
            }
        }

        return -1;
    }
}

class Range {
    int begin;
    int end;

    Range(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }
}