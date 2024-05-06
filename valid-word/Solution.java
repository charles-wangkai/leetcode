class Solution {
  public boolean isValid(String word) {
    return word.length() >= 3
        && word.chars().allMatch(Character::isLetterOrDigit)
        && word.toLowerCase().chars().anyMatch(c -> "aeiou".indexOf(c) != -1)
        && word.toLowerCase()
            .chars()
            .anyMatch(c -> Character.isLetter(c) && "aeiou".indexOf(c) == -1);
  }
}