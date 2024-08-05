import java.util.Arrays;

class Solution {
  public String kthDistinct(String[] arr, int k) {
    return Arrays.stream(arr)
        .filter(s -> Arrays.stream(arr).filter(s::equals).count() == 1)
        .skip(k - 1)
        .findFirst()
        .orElse("");
  }
}
