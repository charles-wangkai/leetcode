class Solution {
  public char nextGreatestLetter(char[] letters, char target) {
    int index = -1;
    int lower = 0;
    int upper = letters.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (letters[middle] > target) {
        index = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return (index == -1) ? letters[0] : letters[index];
  }
}
