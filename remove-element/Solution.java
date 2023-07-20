public class Solution {
  public int removeElement(int[] A, int elem) {
    int endIndex = A.length - 1;
    int index = 0;
    while (index <= endIndex) {
      if (A[index] == elem) {
        A[index] = A[endIndex];
        A[endIndex] = elem;
        endIndex--;
      } else {
        ++index;
      }
    }
    return endIndex + 1;
  }
}
