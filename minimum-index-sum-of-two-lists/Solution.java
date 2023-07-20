import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
  public String[] findRestaurant(String[] list1, String[] list2) {
    List<String> list2List = Arrays.asList(list2);

    int minIndexSum = Integer.MAX_VALUE;
    List<String> result = null;
    for (int i = 0; i < list1.length; i++) {
      int j = list2List.indexOf(list1[i]);
      if (j >= 0) {
        int indexSum = i + j;
        if (indexSum < minIndexSum) {
          minIndexSum = indexSum;

          result = new ArrayList<>();
          result.add(list1[i]);
        } else if (indexSum == minIndexSum) {
          result.add(list1[i]);
        }
      }
    }
    return result.toArray(new String[0]);
  }
}
