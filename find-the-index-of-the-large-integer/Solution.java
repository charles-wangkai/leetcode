// This is ArrayReader's API interface.
// You should not implement it, or speculate about its implementation
interface ArrayReader {
    // Compares the sum of arr[l..r] with the sum of arr[x..y]
    // return 1 if sum(arr[l..r]) > sum(arr[x..y])
    // return 0 if sum(arr[l..r]) == sum(arr[x..y])
    // return -1 if sum(arr[l..r]) < sum(arr[x..y])
    public int compareSub(int l, int r, int x, int y);

    // Returns the length of the array
    public int length();
}

class Solution {
    public int getIndex(ArrayReader reader) {
        return search(reader, 0, reader.length() - 1);
    }

    int search(ArrayReader reader, int beginIndex, int endIndex) {
        if (beginIndex == endIndex) {
            return beginIndex;
        }

        int leftIndex;
        int rightIndex;
        if ((endIndex - beginIndex + 1) % 2 == 0) {
            leftIndex = (beginIndex + endIndex) / 2;
            rightIndex = leftIndex + 1;
        } else {
            leftIndex = (beginIndex + endIndex) / 2 - 1;
            rightIndex = leftIndex + 2;
        }

        int cmp = reader.compareSub(beginIndex, leftIndex, rightIndex, endIndex);
        if (cmp == -1) {
            return search(reader, rightIndex, endIndex);
        } else if (cmp == 1) {
            return search(reader, beginIndex, leftIndex);
        } else {
            return leftIndex + 1;
        }
    }
}