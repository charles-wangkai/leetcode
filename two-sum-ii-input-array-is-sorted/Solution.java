class Solution {
  public int[] twoSum(int[] numbers, int target) {
    int leftIndex = 0;
    int rightIndex = numbers.length - 1;
    while (true) {
      int sum = numbers[leftIndex] + numbers[rightIndex];
      if (sum == target) {
        break;
      }

      if (sum < target) {
        ++leftIndex;
      } else {
        --rightIndex;
      }
    }

    return new int[] {leftIndex + 1, rightIndex + 1};
  }
}
