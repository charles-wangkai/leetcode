import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int findTheWinner(int n, int k) {
    List<Integer> rest = IntStream.rangeClosed(1, n).boxed().collect(Collectors.toList());
    int index = 0;
    while (rest.size() != 1) {
      index = (index + k - 1) % rest.size();
      rest.remove(index);
    }

    return rest.get(0);
  }
}
