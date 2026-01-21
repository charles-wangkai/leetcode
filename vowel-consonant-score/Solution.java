class Solution {
  public int vowelConsonantScore(String s) {
    int vowelCount =
        (int) s.chars().filter(c -> Character.isLetter(c) && isVowel((char) c)).count();
    int consonantCount =
        (int) s.chars().filter(c -> Character.isLetter(c) && !isVowel((char) c)).count();

    return (consonantCount == 0) ? 0 : (vowelCount / consonantCount);
  }

  boolean isVowel(char letter) {
    return "aeiou".indexOf(letter) != -1;
  }
}