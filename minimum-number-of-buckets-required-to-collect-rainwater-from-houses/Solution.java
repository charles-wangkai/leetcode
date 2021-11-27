class Solution {
  public int minimumBuckets(String street) {
    int result = 0;
    char[] spaces = street.toCharArray();
    for (int i = 0; i < spaces.length; ++i) {
      if (spaces[i] == 'H' && !(i != 0 && spaces[i - 1] == 'B')) {
        if (i != spaces.length - 1 && spaces[i + 1] == '.') {
          spaces[i + 1] = 'B';
          ++result;
        } else if (i != 0 && spaces[i - 1] == '.') {
          spaces[i - 1] = 'B';
          ++result;
        } else {
          return -1;
        }
      }
    }

    return result;
  }
}