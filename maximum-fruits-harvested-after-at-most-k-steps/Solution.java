import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int maxTotalFruits(int[][] fruits, int startPos, int k) {
    Element[] lefts =
        Arrays.stream(fruits)
            .filter(fruit -> fruit[0] <= startPos)
            .map(fruit -> new Element(startPos - fruit[0], fruit[1]))
            .sorted(Comparator.comparing(Element::distance))
            .toArray(Element[]::new);
    Element[] rights =
        Arrays.stream(fruits)
            .filter(fruit -> fruit[0] > startPos)
            .map(fruit -> new Element(fruit[0] - startPos, fruit[1]))
            .sorted(Comparator.comparing(Element::distance))
            .toArray(Element[]::new);

    return Math.max(computeFruitNum(k, lefts, rights), computeFruitNum(k, rights, lefts));
  }

  int computeFruitNum(int k, Element[] elements1, Element[] elements2) {
    int distanceSum = 0;
    int amountSum = 0;
    int index2 = -1;
    while (index2 + 1 != elements2.length
        && distanceSum
                + (elements2[index2 + 1].distance()
                    - ((index2 == -1) ? 0 : elements2[index2].distance()))
            <= k) {
      distanceSum +=
          elements2[index2 + 1].distance() - ((index2 == -1) ? 0 : elements2[index2].distance());
      amountSum += elements2[index2 + 1].amount();

      ++index2;
    }

    int result = amountSum;
    for (int i = 0; i < elements1.length; ++i) {
      distanceSum += 2 * (elements1[i].distance() - ((i == 0) ? 0 : elements1[i - 1].distance()));
      amountSum += elements1[i].amount();

      while (distanceSum > k && index2 >= 1) {
        distanceSum -= elements2[index2].distance() - elements2[index2 - 1].distance();
        amountSum -= elements2[index2].amount();

        --index2;
      }
      if (distanceSum > k) {
        break;
      }

      result = Math.max(result, amountSum);
    }

    return result;
  }
}

record Element(int distance, int amount) {}
