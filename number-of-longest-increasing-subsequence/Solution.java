import java.util.Arrays;

class Solution {
  public int findNumberOfLIS(int[] nums) {
    Element[] elements = new Element[nums.length];
    for (int i = 0; i < elements.length; ++i) {
      elements[i] = new Element(1, 1);
      for (int j = 0; j < i; ++j) {
        if (nums[i] > nums[j]) {
          int length = elements[j].length + 1;
          if (length > elements[i].length) {
            elements[i] = new Element(length, elements[j].count);
          } else if (length == elements[i].length) {
            elements[i].count += elements[j].count;
          }
        }
      }
    }

    int maxLength = Arrays.stream(elements).mapToInt(element -> element.length).max().getAsInt();

    return Arrays.stream(elements)
        .filter(element -> element.length == maxLength)
        .mapToInt(element -> element.count)
        .sum();
  }
}

class Element {
  int length;
  int count;

  Element(int length, int count) {
    this.length = length;
    this.count = count;
  }
}
