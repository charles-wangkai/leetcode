import java.util.stream.IntStream;

// This is the ArrayReader's API interface.
// You should not implement it, or speculate about its implementation
interface ArrayReader {
    // Compares 4 different elements in the array
    // return 4 if the values of the 4 elements are the same (0 or 1).
    // return 2 if three elements have a value equal to 0 and one element has value
    // equal to 1 or vice versa.
    // return 0 : if two element have a value equal to 0 and two elements have a
    // value equal to 1.
    public int query(int a, int b, int c, int d);

    // Returns the length of the array
    public int length();
}

class Solution {
    public int guessMajority(ArrayReader reader) {
        int length = reader.length();

        int sameAsFirstCount = 1;
        int diffAsFirstCount = 0;
        int diffAsFirstIndex = -1;

        for (int i = 1; i < length; ++i) {
            int x;
            int y;
            int z;
            if (i == 1) {
                x = 2;
                y = 3;
                z = 4;
            } else if (i == 2) {
                x = 1;
                y = 3;
                z = 4;
            } else if (i == 3) {
                x = 1;
                y = 2;
                z = 4;
            } else {
                x = 1;
                y = 2;
                z = 3;
            }

            if (performQuery(reader, x, y, z, 0) == performQuery(reader, x, y, z, i)) {
                ++sameAsFirstCount;
            } else {
                ++diffAsFirstCount;
                diffAsFirstIndex = i;
            }
        }

        if (sameAsFirstCount < diffAsFirstCount) {
            return diffAsFirstIndex;
        } else if (sameAsFirstCount > diffAsFirstCount) {
            return 0;
        } else {
            return -1;
        }
    }

    int performQuery(ArrayReader reader, int x, int y, int z, int index) {
        int[] params = IntStream.of(x, y, z, index).sorted().toArray();

        return reader.query(params[0], params[1], params[2], params[3]);
    }
}