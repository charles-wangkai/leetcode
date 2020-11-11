class Solution {
  public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
    return isSquare(p1, p2, p3, p4) || isSquare(p1, p2, p4, p3) || isSquare(p1, p3, p2, p4);
  }

  boolean isSquare(int[] pa, int[] pb, int[] pc, int[] pd) {
    int lengthSquare = computeLengthSquare(pa, pb);

    return lengthSquare != 0
        && computeLengthSquare(pb, pc) == lengthSquare
        && computeLengthSquare(pc, pd) == lengthSquare
        && computeLengthSquare(pd, pa) == lengthSquare
        && computeLengthSquare(pa, pc) == computeLengthSquare(pb, pd);
  }

  int computeLengthSquare(int[] ps, int[] pt) {
    return (ps[0] - pt[0]) * (ps[0] - pt[0]) + (ps[1] - pt[1]) * (ps[1] - pt[1]);
  }
}
