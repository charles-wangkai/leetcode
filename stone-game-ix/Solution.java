import java.util.Arrays;

class Solution {
  public boolean stoneGameIX(int[] stones) {
    int[] counts = new int[3];
    for (int stone : stones) {
      ++counts[stone % 3];
    }

    return isWinByFirstOne(counts) || isWinByFirstTwo(counts);
  }

  boolean isWinByFirstOne(int[] counts) {
    int[] rests = Arrays.copyOf(counts, counts.length);
    if (rests[1] == 0) {
      return false;
    }
    --rests[1];

    boolean turn = rests[0] % 2 != 0;
    int total = rests[1] + rests[2];
    for (int i = 0; i < total; ++i) {
      if (i % 2 == 0) {
        if (rests[1] == 0) {
          return !turn;
        }

        --rests[1];
      } else {
        if (rests[2] == 0) {
          return !turn;
        }

        --rests[2];
      }

      turn = !turn;
    }

    return false;
  }

  boolean isWinByFirstTwo(int[] counts) {
    int[] rests = Arrays.copyOf(counts, counts.length);
    if (rests[2] == 0) {
      return false;
    }
    --rests[2];

    boolean turn = rests[0] % 2 != 0;
    int total = rests[1] + rests[2];
    for (int i = 0; i < total; ++i) {
      if (i % 2 == 0) {
        if (rests[2] == 0) {
          return !turn;
        }

        --rests[2];
      } else {
        if (rests[1] == 0) {
          return !turn;
        }

        --rests[1];
      }

      turn = !turn;
    }

    return false;
  }
}
