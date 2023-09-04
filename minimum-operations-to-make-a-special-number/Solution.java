class Solution {
  public int minimumOperations(String num) {
    num = "00" + num;

    int maxIndex = -1;
    for (String target : new String[] {"00", "25", "50", "75"}) {
      int index1 = num.lastIndexOf(target.charAt(1));
      if (index1 != -1) {
        maxIndex = Math.max(maxIndex, num.lastIndexOf(target.charAt(0), index1 - 1));
      }
    }

    return num.length() - maxIndex - 2;
  }
}
