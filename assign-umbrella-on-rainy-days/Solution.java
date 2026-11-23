class Solution {
  public String[] assignUmbrella(int[] rain) {
    String[] result = new String[rain.length];
    int aliceCount = 0;
    int bobCount = 0;
    for (int i = 0; i < result.length; ++i) {
      boolean aliceNeeded = i != rain.length - 1 && rain[i] == 1 && rain[i + 1] == 1;
      boolean bobNeeded = rain[i] == 1;
      if (aliceNeeded && (!bobNeeded || aliceCount <= bobCount)) {
        result[i] = "Alice";
        ++aliceCount;
      } else if (bobNeeded) {
        result[i] = "Bob";
        ++bobCount;
      } else {
        result[i] = "None";
      }
    }

    return result;
  }
}