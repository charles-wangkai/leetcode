class Solution {
  public String decodeCiphertext(String encodedText, int rows) {
    int cols = encodedText.length() / rows;
    StringBuilder original = new StringBuilder();
    for (int beginC = 0; beginC < cols; ++beginC) {
      for (int r = 0; r < rows && beginC + r < cols; ++r) {
        original.append(encodedText.charAt(r * cols + beginC + r));
      }
    }

    return original.toString().stripTrailing();
  }
}
