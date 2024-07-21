class Solution {
  public String losingPlayer(int x, int y) {
    boolean aliceOrBobTurn = true;
    while (x >= 1 && y >= 4) {
      x -= 1;
      y -= 4;
      aliceOrBobTurn ^= true;
    }

    return aliceOrBobTurn ? "Bob" : "Alice";
  }
}