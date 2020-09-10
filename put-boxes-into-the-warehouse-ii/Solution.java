import java.util.Arrays;
import java.util.Collections;

class Solution {
    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
        boxes = Arrays.stream(boxes).boxed().sorted(Collections.reverseOrder()).mapToInt(x -> x).toArray();

        int result = 0;
        int boxIndex = 0;
        int leftIndex = 0;
        int rightIndex = warehouse.length - 1;
        int leftHeightLimit = warehouse[0];
        int rightHeightLimit = warehouse[warehouse.length - 1];
        while (boxIndex != boxes.length && leftIndex <= rightIndex) {
            if (boxes[boxIndex] <= leftHeightLimit) {
                ++result;
                ++leftIndex;
                if (leftIndex != warehouse.length) {
                    leftHeightLimit = Math.min(leftHeightLimit, warehouse[leftIndex]);
                }
            } else if (boxes[boxIndex] <= rightHeightLimit) {
                ++result;
                --rightIndex;
                if (rightIndex != -1) {
                    rightHeightLimit = Math.min(rightHeightLimit, warehouse[rightIndex]);
                }
            }

            ++boxIndex;
        }

        return result;
    }
}