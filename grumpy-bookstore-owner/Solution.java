import java.util.stream.IntStream;

class Solution {
  public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
    int satisfiedNum =
        IntStream.range(0, customers.length)
            .filter(i -> i < minutes || grumpy[i] == 0)
            .map(i -> customers[i])
            .sum();

    int result = satisfiedNum;
    for (int endIndex = minutes; endIndex < customers.length; ++endIndex) {
      if (grumpy[endIndex] == 1) {
        satisfiedNum += customers[endIndex];
      }
      if (grumpy[endIndex - minutes] == 1) {
        satisfiedNum -= customers[endIndex - minutes];
      }

      result = Math.max(result, satisfiedNum);
    }

    return result;
  }
}
