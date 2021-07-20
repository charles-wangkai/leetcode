import java.util.Arrays;
import java.util.Random;

class Solution {
  Random random = new Random();
  int[] nums;

  public Solution(int[] nums) {
    this.nums = nums;
  }

  /** Resets the array to its original configuration and return it. */
  public int[] reset() {
    return nums;
  }

  /** Returns a random shuffling of the array. */
  public int[] shuffle() {
    int[] shuffled = Arrays.copyOf(nums, nums.length);
    permute(shuffled, 0);

    return shuffled;
  }

  void permute(int[] shuffled, int index) {
    if (index == shuffled.length) {
      return;
    }

    swap(shuffled, index, randomChoose(index, shuffled.length - 1));
    permute(shuffled, index + 1);
  }

  void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }

  int randomChoose(int begin, int end) {
    return random.nextInt(end - begin + 1) + begin;
  }
}

// Your Solution object will be instantiated and called as such:
// Solution obj = new Solution(nums);
// int[] param_1 = obj.reset();
// int[] param_2 = obj.shuffle();
