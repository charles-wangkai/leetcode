// This is the FontInfo's API interface.
// You should not implement it, or speculate about its implementation
interface FontInfo {
  // Return the width of char ch when fontSize is used.
  public int getWidth(int fontSize, char ch);
  // Return Height of any char when fontSize is used.
  public int getHeight(int fontSize);
}

class Solution {
  public int maxFont(String text, int w, int h, int[] fonts, FontInfo fontInfo) {
    int result = -1;
    int lowerIndex = 0;
    int upperIndex = fonts.length - 1;
    while (lowerIndex <= upperIndex) {
      int middleIndex = (lowerIndex + upperIndex) / 2;

      if (check(text, w, h, fonts[middleIndex], fontInfo)) {
        result = fonts[middleIndex];
        lowerIndex = middleIndex + 1;
      } else {
        upperIndex = middleIndex - 1;
      }
    }

    return result;
  }

  boolean check(String text, int w, int h, int fontSize, FontInfo fontInfo) {
    return text.chars().map(ch -> fontInfo.getWidth(fontSize, (char) ch)).asLongStream().sum() <= w
        && fontInfo.getHeight(fontSize) <= h;
  }
}
