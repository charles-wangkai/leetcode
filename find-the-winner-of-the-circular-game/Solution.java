import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int findTheWinner(int n, int k) {
    List<Integer> friends = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
    int index = 0;
    while (friends.size() != 1) {
      index = (index + k - 1) % friends.size();
      friends.remove(index);
    }

    return friends.get(0);
  }
}
