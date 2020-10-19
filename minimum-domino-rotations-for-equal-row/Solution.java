class Solution {
  public int minDominoRotations(int[] A, int[] B) {
    int result = Integer.MAX_VALUE;
    for (int target = 1; target <= 6; ++target) {
      result =
          Math.min(result, Math.min(findRotationNum(A, B, target), findRotationNum(B, A, target)));
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }

  int findRotationNum(int[] primary, int[] secondary, int target) {
    int rotationNum = 0;
    for (int i = 0; i < primary.length; ++i) {
      if (primary[i] != target) {
        if (secondary[i] == target) {
          ++rotationNum;
        } else {
          return Integer.MAX_VALUE;
        }
      }
    }

    return rotationNum;
  }
}
