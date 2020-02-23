import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] counts = new int[n];
        IntStream.concat(Arrays.stream(leftChild), Arrays.stream(rightChild)).filter(child -> child != -1)
                .forEach(child -> ++counts[child]);

        return Arrays.stream(counts).filter(count -> count == 0).count() == 1
                && Arrays.stream(counts).filter(count -> count == 1).count() == n - 1;
    }
}