class Solution {
  public String reversePrefix(String word, char ch) {
    int index = word.indexOf(ch);

    return (index == -1)
        ? word
        : (new StringBuilder(word.substring(0, index + 1)).reverse().toString()
            + word.substring(index + 1));
  }
}
