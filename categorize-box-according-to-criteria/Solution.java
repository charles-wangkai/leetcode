class Solution {
  public String categorizeBox(int length, int width, int height, int mass) {
    boolean bulky =
        length >= 10000
            || width >= 10000
            || height >= 10000
            || (long) length * width * height >= 1_000_000_000;
    boolean heavy = mass >= 100;

    return bulky ? (heavy ? "Both" : "Bulky") : (heavy ? "Heavy" : "Neither");
  }
}
