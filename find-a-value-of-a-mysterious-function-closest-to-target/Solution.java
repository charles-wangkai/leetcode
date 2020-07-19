import java.util.ArrayList;
import java.util.List;

class Solution {
    static final int SIZE = 20;

    public int closestToTarget(int[] arr, int target) {
        @SuppressWarnings("unchecked")
        List<Integer>[] indexLists = new List[SIZE];
        for (int i = 0; i < indexLists.length; ++i) {
            indexLists[i] = new ArrayList<>();
        }

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                if ((arr[i] & (1 << j)) == 0) {
                    indexLists[j].add(i);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; ++i) {
            int currentIndex = i;
            int ans = arr[i];
            result = Math.min(result, Math.abs(ans - target));

            while (true) {
                int minIndex = Integer.MAX_VALUE;
                for (int j = 1; j < SIZE; ++j) {
                    if ((ans & (1 << j)) != 0) {
                        minIndex = Math.min(minIndex, findIndex(indexLists[j], currentIndex));
                    }
                }

                if (minIndex == Integer.MAX_VALUE) {
                    break;
                }

                currentIndex = minIndex;
                ans &= arr[currentIndex];
                result = Math.min(result, Math.abs(ans - target));
            }
        }

        return result;
    }

    int findIndex(List<Integer> indexList, int currentIndex) {
        if (indexList.isEmpty() || indexList.get(indexList.size() - 1) <= currentIndex) {
            return Integer.MAX_VALUE;
        }

        int result = -1;
        int lower = 0;
        int upper = indexList.size() - 1;
        while (lower <= upper) {
            int middle = (lower + upper) / 2;
            if (indexList.get(middle) > currentIndex) {
                result = indexList.get(middle);
                upper = middle - 1;
            } else {
                lower = middle + 1;
            }
        }

        return result;
    }
}