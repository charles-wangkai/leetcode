import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class FindSumPairs {
  int[] nums1;
  int[] nums2;
  Map<Integer, Integer> valueToCount = new HashMap<>();

  public FindSumPairs(int[] nums1, int[] nums2) {
    this.nums1 = nums1;
    this.nums2 = nums2;

    for (int num2 : nums2) {
      updateMap(valueToCount, num2, 1);
    }
  }

  public void add(int index, int val) {
    updateMap(valueToCount, nums2[index], -1);
    nums2[index] += val;
    updateMap(valueToCount, nums2[index], 1);
  }

  public int count(int tot) {
    return Arrays.stream(nums1).map(num1 -> valueToCount.getOrDefault(tot - num1, 0)).sum();
  }

  void updateMap(Map<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}

// Your FindSumPairs object will be instantiated and called as such:
// FindSumPairs obj = new FindSumPairs(nums1, nums2);
// obj.add(index,val);
// int param_2 = obj.count(tot);
