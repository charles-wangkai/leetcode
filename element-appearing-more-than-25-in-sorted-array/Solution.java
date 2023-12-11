public class Solution {
  public int findSpecialInteger(int[] arr) {
    int result = -1;
    int maxCount = 0;
    int count = 0;
    for (int i = 0; i < arr.length; ++i) {
      if (i != 0 && arr[i] == arr[i - 1]) {
        ++count;
      } else {
        count = 1;
      }

      if (count > maxCount) {
        maxCount = count;
        result = arr[i];
      }
    }

    return result;
  }
}
