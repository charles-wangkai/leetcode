import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int LIMIT = 100000;

  static int[] factorials;
  static int[] factorialInvs;

  static {
    factorials = new int[LIMIT + 1];
    factorials[0] = 1;
    for (int i = 1; i < factorials.length; ++i) {
      factorials[i] = multiplyMod(factorials[i - 1], i);
    }

    factorialInvs =
        Arrays.stream(factorials)
            .map(x -> BigInteger.valueOf(x).modInverse(BigInteger.valueOf(MODULUS)).intValue())
            .toArray();
  }

  public int waysToBuildRooms(int[] prevRoom) {
    int n = prevRoom.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] childrens = new List[n];
    for (int i = 0; i < childrens.length; ++i) {
      childrens[i] = new ArrayList<>();
    }

    for (int i = 1; i < prevRoom.length; ++i) {
      childrens[prevRoom[i]].add(i);
    }

    int[] sizes = new int[n];
    int[] orderNums = new int[n];
    search(childrens, sizes, orderNums, 0);

    return orderNums[0];
  }

  void search(List<Integer>[] childrens, int[] sizes, int[] orderNums, int v) {
    sizes[v] = 1;
    orderNums[v] = 1;

    if (!childrens[v].isEmpty()) {
      for (int child : childrens[v]) {
        search(childrens, sizes, orderNums, child);
        sizes[v] += sizes[child];
      }

      int remain = sizes[v] - 1;
      for (int child : childrens[v]) {
        orderNums[v] =
            multiplyMod(orderNums[v], multiplyMod(C(remain, sizes[child]), orderNums[child]));
        remain -= sizes[child];
      }
    }
  }

  static int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }

  int C(int n, int r) {
    return multiplyMod(factorials[n], multiplyMod(factorialInvs[r], factorialInvs[n - r]));
  }
}
