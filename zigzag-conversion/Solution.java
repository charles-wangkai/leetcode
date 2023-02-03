class Solution {
  public String convert(String s, int numRows) {
    if (numRows == 1) {
      return s;
    }

    String[] rows = new String[numRows];
    for (int i = 0; i < rows.length; ++i) {
      rows[i] = "";
    }
    int rowIndex = 0;
    int offset = 1;
    for (char c : s.toCharArray()) {
      rows[rowIndex] += c;
      rowIndex += offset;

      if (rowIndex == rows.length) {
        rowIndex = rows.length - 2;
        offset = -1;
      } else if (rowIndex == -1) {
        rowIndex = 1;
        offset = 1;
      }
    }

    return String.join("", rows);
  }
}
