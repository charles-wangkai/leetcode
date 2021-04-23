import java.util.stream.IntStream;

class Solution {
  public int badSensor(int[] sensor1, int[] sensor2) {
    int diffIndex = 0;
    while (diffIndex != sensor1.length && sensor1[diffIndex] == sensor2[diffIndex]) {
      ++diffIndex;
    }

    boolean defect1 = isDefect(sensor1, sensor2, diffIndex);
    boolean defect2 = isDefect(sensor2, sensor1, diffIndex);
    if (defect1 == defect2) {
      return -1;
    }

    return defect1 ? 1 : 2;
  }

  boolean isDefect(int[] defective, int[] correct, int diffIndex) {
    return diffIndex == defective.length
        || IntStream.range(diffIndex, defective.length - 1)
            .allMatch(i -> defective[i] == correct[i + 1]);
  }
}
