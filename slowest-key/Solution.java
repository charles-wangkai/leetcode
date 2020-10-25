class Solution {
  public char slowestKey(int[] releaseTimes, String keysPressed) {
    int maxDuration = -1;
    char result = 0;
    int prevTime = 0;
    for (int i = 0; i < releaseTimes.length; ++i) {
      char key = keysPressed.charAt(i);
      int duration = releaseTimes[i] - prevTime;
      if (duration > maxDuration || (duration == maxDuration && key >= result)) {
        maxDuration = duration;
        result = key;
      }

      prevTime = releaseTimes[i];
    }

    return result;
  }
}
