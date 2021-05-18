class Solution {
  public String sortSentence(String s) {
    String[] parts = s.split(" ");
    String[] words = new String[parts.length];
    for (String part : parts) {
      String word = part.substring(0, part.length() - 1);
      int index = part.charAt(part.length() - 1) - '1';
      words[index] = word;
    }

    return String.join(" ", words);
  }
}
