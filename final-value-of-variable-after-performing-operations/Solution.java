class Solution {
  public int finalValueAfterOperations(String[] operations) {
    int result = 0;
    for (String operation : operations) {
      if (operation.contains("++")) {
        ++result;
      } else {
        --result;
      }
    }

    return result;
  }
}
