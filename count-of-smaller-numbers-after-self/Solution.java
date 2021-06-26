import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> countSmaller(int[] nums) {
    List<Integer> smallerNums =
        IntStream.range(0, nums.length).map(i -> 0).boxed().collect(Collectors.toList());

    Element[] elements =
        IntStream.range(0, nums.length)
            .mapToObj(i -> new Element(i, nums[i]))
            .toArray(Element[]::new);

    sort(smallerNums, elements, 0, elements.length - 1);

    return smallerNums;
  }

  void sort(List<Integer> smallerNums, Element[] elements, int beginIndex, int endIndex) {
    if (beginIndex >= endIndex) {
      return;
    }

    int middleIndex = (beginIndex + endIndex) / 2;

    sort(smallerNums, elements, beginIndex, middleIndex);
    sort(smallerNums, elements, middleIndex + 1, endIndex);

    List<Element> merged = new ArrayList<>();
    int leftIndex = beginIndex;
    int rightIndex = middleIndex + 1;
    while (leftIndex != middleIndex + 1 || rightIndex != endIndex + 1) {
      if (rightIndex == endIndex + 1
          || (leftIndex != middleIndex + 1
              && elements[leftIndex].value > elements[rightIndex].value)) {
        smallerNums.set(
            elements[leftIndex].index,
            smallerNums.get(elements[leftIndex].index) + (endIndex + 1 - rightIndex));

        merged.add(elements[leftIndex]);
        ++leftIndex;
      } else {
        merged.add(elements[rightIndex]);
        ++rightIndex;
      }
    }

    for (int i = beginIndex; i <= endIndex; ++i) {
      elements[i] = merged.get(i - beginIndex);
    }
  }
}

class Element {
  int index;
  int value;

  Element(int index, int value) {
    this.index = index;
    this.value = value;
  }
}
