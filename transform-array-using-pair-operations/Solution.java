import java.util.Arrays;

class Solution {
  public boolean canTransform(int[] source, int[] target) {
    return Arrays.stream(source).asLongStream().sum() == Arrays.stream(target).asLongStream().sum();
  }
}