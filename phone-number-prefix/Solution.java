class Solution {
  public boolean phonePrefix(String[] numbers) {
    for (int i = 0; i < numbers.length; ++i) {
      for (int j = i + 1; j < numbers.length; ++j) {
        if (numbers[i].startsWith(numbers[j]) || numbers[j].startsWith(numbers[i])) {
          return false;
        }
      }
    }

    return true;
  }
}