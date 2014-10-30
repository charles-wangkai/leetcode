public class SearchForARange {
    public int[] searchRange(int[] A, int target) {
        int leftIndex = search(A, target, true);
        if (leftIndex < 0) {
            return new int[] { -1, -1 };
        }
        int rightIndex = search(A, target, false);
        return new int[] { leftIndex, rightIndex };
    }

    int search(int[] A, int target, boolean leftOrRight) {
        int lower = 0;
        int upper = A.length - 1;
        int index = -1;
        while (lower <= upper) {
            int middle = (lower + upper) / 2;
            if (A[middle] > target) {
                upper = middle - 1;
            } else if (A[middle] < target) {
                lower = middle + 1;
            } else {
                index = middle;
                if (leftOrRight) {
                    upper = middle - 1;
                } else {
                    lower = middle + 1;
                }
            }
        }
        return index;
    }
}
