import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int[] recoverOrder(int[] order, int[] friends) {
    Set<Integer> friendSet = Arrays.stream(friends).boxed().collect(Collectors.toSet());

    return Arrays.stream(order).filter(friendSet::contains).toArray();
  }
}