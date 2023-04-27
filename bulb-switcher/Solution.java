class Solution {
  public int bulbSwitch(int n) {
    int root = (int) Math.round(Math.sqrt(n));
    if (root * root > n) {
      --root;
    }

    return root;
  }
}
